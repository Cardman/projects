package code.expressionlanguage.analyze.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.*;
import code.util.*;

public final class ContextUtil {
    private ContextUtil() {
    }

    public static boolean canAccess(String _className, AccessibleBlock _block, ContextEl _context) {
        CodeAccess code_ = processBegin(_className, _block, _context);
        ExecRootBlock root_ = code_.getRoot();
        if (root_ == null) {
            return access(code_.getCode());
        }
        String belongPkg_ = _block.getPackageName();
        String rootPkg_ = root_.getPackageName();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            return processNormalProtected(_block, _context, root_, belongPkg_, rootPkg_);
        }
        return processPackagePrivate(_block, root_, belongPkg_, rootPkg_);
    }

    private static boolean processNormalProtected(AccessibleBlock _block, ContextEl _context, ExecRootBlock root_, String belongPkg_, String rootPkg_) {
        if (root_.isSubTypeOf(_block.getFullName(),_context)) {
            return true;
        }
        return StringList.quickEq(belongPkg_, rootPkg_);
    }

    public static boolean canAccessType(String _className, AccessibleBlock _block, ContextEl _context) {
        CodeAccess code_ = processBegin(_className, _block, _context);
        ExecRootBlock root_ = code_.getRoot();
        if (root_ == null) {
            return access(code_.getCode());
        }
        String belongPkg_ = _block.getPackageName();
        String parName_ = _block.getParentFullName();
        String rootPkg_ = root_.getPackageName();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            if (!parName_.isEmpty()) {
                if (root_.isSubTypeOf(parName_,_context)) {
                    return true;
                }
            }
            return processNormalProtected(_block, _context, root_, belongPkg_, rootPkg_);
        }
        return processPackagePrivate(_block, root_, belongPkg_, rootPkg_);
    }
    private static CodeAccess processBegin(String _className, AccessibleBlock _block, ContextEl _context) {
        if (_block.getAccess() == AccessEnum.PUBLIC) {
            return new CodeAccess(2,null);
        }
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock root_ = _context.getClasses().getClassBody(baseClass_);
        if (root_ == null) {
            return new CodeAccess(0,null);
        }
        return new CodeAccess(1,root_);
    }
    private static boolean access(int _code) {
        return _code == 2;
    }
    private static boolean processPackagePrivate(AccessibleBlock _block, ExecRootBlock root_, String belongPkg_, String rootPkg_) {
        if (_block.getAccess() == AccessEnum.PACKAGE) {
            return StringList.quickEq(belongPkg_, rootPkg_);
        }
        return StringList.quickEq(_block.getOuterFullName(), root_.getOuterFullName());
    }

    public static CustList<ExecInfoBlock> getFieldBlocks(ExecRootBlock _element){
        CustList<ExecInfoBlock> methods_ = new CustList<ExecInfoBlock>();
        for (ExecBlock b: ExecBlock.getDirectChildren(_element)) {
            if (b instanceof ExecInfoBlock) {
                methods_.add((ExecInfoBlock) b);
            }
        }
        return methods_;
    }

    public static CustList<GeneConstructor> getConstructorBodies(GeneType _type) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        if (_type instanceof StandardType) {
            for (StandardConstructor s: ((StandardType)_type).getConstructors()) {
                methods_.add(s);
            }
        } else {
            CustList<ExecBlock> bl_ = ExecBlock.getDirectChildren((ExecBlock) _type);
            for (ExecBlock b: bl_) {
                if (!(b instanceof ExecConstructorBlock)) {
                    continue;
                }
                ExecConstructorBlock method_ = (ExecConstructorBlock) b;
                methods_.add(method_);
            }
        }
        return methods_;
    }

    public static int getCurrentChildTypeIndex(ContextEl _an, OperationNode _op, GeneType _type, String _fieldName, String _realClassName) {
        if (isEnumType(_type)) {
            if (_fieldName.isEmpty()) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                String file_ = _an.getAnalyzing().getLocalizer().getCurrentFileName();
                int fileIndex_ = _an.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                call_.setFileName(file_);
                call_.setIndexFile(fileIndex_);
                //type len
                call_.buildError(_an.getAnalysisMessages().getIllegalCtorEnum());
                _an.getAnalyzing().getLocalizer().addError(call_);
                _op.setResultClass(new ClassArgumentMatching(_realClassName));
                _op.getErrs().add(call_.getBuiltError());
                return -2;
            }
            return _an.getAnalyzing().getIndexChildType();
        }
        return -1;
    }

    public static boolean isAbstractType(GeneType _type) {
        if (_type instanceof StandardInterface) {
            return true;
        }
        if (_type instanceof ExecRootBlock) {
            return ((ExecRootBlock)_type).isAbstractType();
        }
        return ((StandardClass)_type).isAbstractStdType();
    }

    public static boolean isEnumType(GeneType _type) {
        return _type instanceof ExecEnumBlock || _type instanceof ExecInnerElementBlock;
    }

    public static boolean isExplicitFct(FunctionBlock _fct) {
        return _fct instanceof OverridableBlock
                && (((OverridableBlock) _fct).getKind() == MethodKind.EXPLICIT_CAST
        ||((OverridableBlock) _fct).getKind() == MethodKind.IMPLICIT_CAST);
    }

    public static boolean idDisjointToken(ContextEl _cont, String _id) {
        return isNotVar(_cont,_id);
    }

    public static boolean isNotVar(ContextEl _cont, String _id) {
        if (_cont.getAnalyzing().containsLocalVar(_id)) {
            return false;
        }
        if (_cont.getAnalyzing().containsCatchVar(_id)) {
            return false;
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(_id)) {
            return false;
        }
        if (_cont.getAnalyzing().containsVar(_id)) {
            return false;
        }
        return !_cont.getAnalyzing().getParameters().contains(_id);
    }

    public static StringMap<StringList> getCurrentConstraints(ContextEl _cont) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (EntryCust<String,TypeVar> e: getCurrentConstraintsFull(_cont).entryList()) {
            vars_.addEntry(e.getKey(), e.getValue().getConstraints());
        }
        return vars_;
    }

    public static void buildCurrentConstraintsFull(ContextEl _cont) {
        StringMap<TypeVar> vars_ = getCurrentConstraintsFull(_cont);
        _cont.getAnalyzing().getAvailableVariables().clear();
        for (EntryCust<String,TypeVar> e: vars_.entryList()) {
            _cont.getAnalyzing().getAvailableVariables().addEntry(e.getKey(),e.getValue().getOffset());
        }
    }

    private static StringMap<TypeVar> getCurrentConstraintsFull(ContextEl _cont) {
        if (_cont.isAnnotAnalysis()) {
            return new StringMap<TypeVar>();
        }
        Block bl_ = _cont.getAnalyzing().getCurrentBlock();
        AccessedBlock r_ =_cont.getCurrentGlobalBlock();
        StringMap<TypeVar> vars_ = new StringMap<TypeVar>();

        boolean static_;
        if (bl_ instanceof InfoBlock) {
            static_ = ((InfoBlock)bl_).isStaticField();
        } else {
            MemberCallingsBlock fct_ = _cont.getAnalyzing().getCurrentFct();
            if (fct_ == null) {
                static_ = true;
            } else if (isExplicitFct(fct_)){
                static_ = false;
            } else {
                static_ = fct_.getStaticContext() == MethodAccessKind.STATIC;
            }
        }
        RootBlock root_ = null;
        if (r_ instanceof ExecRootBlock) {
            root_ = _cont.getAnalyzing().getAnaClassBody(((ExecRootBlock) r_).getFullName());
        }
        if (root_ != null && !static_) {
            for (TypeVar t: root_.getParamTypesMapValues()) {
                vars_.put(t.getName(), t);
            }
        }
        return vars_;
    }

    public static void appendParts(ContextEl _cont,int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        if (!_cont.isGettingParts()) {
            return;
        }
        GeneType g_ = _cont.getClassBody(_in);
        if (!LinkageUtil.isFromCustFile(g_)) {
            return;
        }
        AccessedBlock r_ = _cont.getCurrentGlobalBlock();
        int rc_ = _cont.getCurrentLocationIndex();
        String curr_ = ((ExecBlock)r_).getFile().getRenderFileName();
        String ref_ = ((ExecRootBlock) g_).getFile().getRenderFileName();
        String rel_ = LinkageUtil.relativize(curr_,ref_);
        int id_ = ((ExecRootBlock) g_).getIdRowCol();
        _parts.add(new PartOffset("<a title=\""+g_.getFullName()+"\" href=\""+rel_+"#m"+id_+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public static void appendTitleParts(ContextEl _cont, int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        if (!_cont.isGettingParts()) {
            return;
        }
        int rc_ = _cont.getCurrentLocationIndex();
        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(_in)+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public static boolean isFinalField(ContextEl _cont, ClassField _classField) {
        FieldInfo fieldInfo_ = getFieldInfo(_cont, _classField);
        if (fieldInfo_ == null) {
            return false;
        }
        return fieldInfo_.isFinalField();
    }
    public static FieldInfo getFieldInfo(ContextEl _cont, ClassField _classField) {
        String fullName_ = _classField.getClassName();
        String search_ = _classField.getFieldName();
        RootBlock cust_ = _cont.getAnalyzing().getAnaClassBody(fullName_);
        if (cust_ != null) {
            for (Block b: ClassesUtil.getDirectChildren(cust_)) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock) b;
                int ind_ = StringList.indexOf(i_.getFieldName(), search_);
                if (ind_ < 0) {
                    continue;
                }
                int valOffset_ = -1;
                Ints valueOffset_ = new Ints();
                if (i_ instanceof FieldBlock) {
                    valOffset_ = ((FieldBlock)i_).getValuesOffset().get(ind_);
                    valueOffset_ = ((FieldBlock)i_).getValuesOffset();
                }
                if (i_ instanceof InnerTypeOrElement) {
                    valOffset_ = i_.getFieldNameOffset();
                    valueOffset_ = new Ints(i_.getFieldNameOffset());
                }
                String type_ = i_.getImportedClassName();
                boolean final_ = i_.isFinalField();
                boolean static_ = i_.isStaticField();
                Accessed a_ = new Accessed(i_.getAccess(),cust_.getPackageName(),fullName_, cust_.getOuterFullName());
                return FieldInfo.newFieldMetaInfo(search_, cust_.getFullName(), type_, static_, final_, a_, valOffset_,valueOffset_);
            }
            return null;
        }
        GeneType g_ = _cont.getClassBody(fullName_);
        if (g_ instanceof StandardType) {
            for (EntryCust<String, StandardField> f: ((StandardType)g_).getFields().entryList()) {
                StandardField f_ = f.getValue();
                if (!StringList.contains(f_.getFieldName(), search_)) {
                    continue;
                }
                String type_ = f_.getImportedClassName();
                boolean final_ = f_.isFinalField();
                boolean static_ = f_.isStaticField();
                Accessed a_ = new Accessed(AccessEnum.PUBLIC,"","","");
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_, a_,-1,new Ints());
            }
        }
        return null;
    }
}
