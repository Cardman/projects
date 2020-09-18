package code.expressionlanguage.analyze.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.*;
import code.util.*;

public final class ContextUtil {
    private ContextUtil() {
    }

    public static boolean canAccess(String _className, AccessibleBlock _block, ContextEl _context) {
        CodeAccess code_ = processBegin(_className, _block, _context.getAnalyzing());
        RootBlock root_ = code_.getRoot();
        if (root_ == null) {
            return access(code_.getCode());
        }
        String belongPkg_ = _block.getPackageName();
        String rootPkg_ = root_.getPackageName();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            return processNormalProtected(_block, root_, belongPkg_, rootPkg_, _context.getAnalyzing());
        }
        return processPackagePrivate(_block, root_, belongPkg_, rootPkg_);
    }

    private static boolean processNormalProtected(AccessibleBlock _block, RootBlock root_, String belongPkg_, String rootPkg_, AnalyzedPageEl _analyzing) {
        if (root_.isSubTypeOf(_block.getFullName(), _analyzing)) {
            return true;
        }
        return StringList.quickEq(belongPkg_, rootPkg_);
    }

    public static boolean canAccessType(String _className, AccessibleBlock _block, AnalyzedPageEl _analyzing) {
        CodeAccess code_ = processBegin(_className, _block, _analyzing);
        RootBlock root_ = code_.getRoot();
        if (root_ == null) {
            return access(code_.getCode());
        }
        String belongPkg_ = _block.getPackageName();
        String parName_ = _block.getParentFullName();
        String rootPkg_ = root_.getPackageName();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            if (!parName_.isEmpty()) {
                if (root_.isSubTypeOf(parName_, _analyzing)) {
                    return true;
                }
            }
            return processNormalProtected(_block, root_, belongPkg_, rootPkg_, _analyzing);
        }
        return processPackagePrivate(_block, root_, belongPkg_, rootPkg_);
    }
    private static CodeAccess processBegin(String _className, AccessibleBlock _block, AnalyzedPageEl _analyzing) {
        if (_block.getAccess() == AccessEnum.PUBLIC) {
            return new CodeAccess(2,null);
        }
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_className);
        RootBlock root_ = _analyzing.getAnaClassBody(baseClass_);
        if (root_ == null) {
            return new CodeAccess(0,null);
        }
        return new CodeAccess(1,root_);
    }
    private static boolean access(int _code) {
        return _code == 2;
    }
    private static boolean processPackagePrivate(AccessibleBlock _block, RootBlock root_, String belongPkg_, String rootPkg_) {
        if (_block.getAccess() == AccessEnum.PACKAGE) {
            return StringList.quickEq(belongPkg_, rootPkg_);
        }
        return StringList.quickEq(_block.getOuterFullName(), root_.getOuterFullName());
    }

    public static CustList<InfoBlock> getFieldBlocks(RootBlock _element){
        CustList<InfoBlock> methods_ = new CustList<InfoBlock>();
        for (Block b: ClassesUtil.getDirectChildren(_element)) {
            if (b instanceof InfoBlock) {
                methods_.add((InfoBlock) b);
            }
        }
        return methods_;
    }

    public static CustList<GeneConstructor> getConstructorBodies(AnaInheritedType _type) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        if (_type instanceof StandardType) {
            for (StandardConstructor s: ((StandardType)_type).getConstructors()) {
                methods_.add(s);
            }
        }
        if (_type instanceof Block){
            CustList<Block> bl_ = ClassesUtil.getDirectChildren((Block) _type);
            for (Block b: bl_) {
                if (!(b instanceof ConstructorBlock)) {
                    continue;
                }
                ConstructorBlock method_ = (ConstructorBlock) b;
                methods_.add(method_);
            }
        }
        return methods_;
    }

    public static int getCurrentChildTypeIndex(ContextEl _an, OperationNode _op, AnaGeneType _type, String _fieldName, String _realClassName) {
        if (isEnumType(_type)) {
            if (_fieldName.isEmpty()) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                String file_ = _an.getAnalyzing().getLocalizer().getCurrentFileName();
                int fileIndex_ = _an.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                call_.setFileName(file_);
                call_.setIndexFile(fileIndex_);
                //type len
                call_.buildError(_an.getAnalyzing().getAnalysisMessages().getIllegalCtorEnum());
                _an.getAnalyzing().getLocalizer().addError(call_);
                _op.setResultClass(new AnaClassArgumentMatching(_realClassName));
                _op.getErrs().add(call_.getBuiltError());
                return -2;
            }
            return _an.getAnalyzing().getIndexChildType();
        }
        return -1;
    }
    public static CustList<TypeVar> getParamTypesMapValues(AnaGeneType _type) {
        if (_type instanceof RootBlock) {
            return ((RootBlock)_type).getParamTypesMapValues();
        }
        return new CustList<TypeVar>();
    }
    public static boolean isFinalType(AnaGeneType _type) {
        if (_type instanceof StandardClass) {
            return ((StandardClass)_type).isFinalStdType();
        }
        if (_type instanceof ClassBlock) {
            return ((ClassBlock)_type).isFinalType();
        }
        return isEnumType(_type) || _type instanceof AnnotationBlock;
    }

    public static boolean isAbstractType(AnaGeneType _type) {
        if (_type instanceof StandardClass) {
            return ((StandardClass)_type).isAbstractStdType();
        }
        if (_type instanceof ClassBlock) {
            return ((ClassBlock)_type).isAbstractType();
        }
        return true;
    }

    public static boolean isEnumType(AnaGeneType _type) {
        return _type instanceof EnumBlock || _type instanceof InnerElementBlock;
    }

    public static boolean isExplicitFct(FunctionBlock _fct) {
        return _fct instanceof OverridableBlock
                && (((OverridableBlock) _fct).getKind() == MethodKind.EXPLICIT_CAST
        ||((OverridableBlock) _fct).getKind() == MethodKind.IMPLICIT_CAST
        ||((OverridableBlock) _fct).getKind() == MethodKind.TRUE_OPERATOR
                ||((OverridableBlock) _fct).getKind() == MethodKind.FALSE_OPERATOR);
    }

    public static boolean idDisjointToken(String _id, AnalyzedPageEl _analyzing) {
        return isNotVar(_id, _analyzing);
    }

    public static boolean isNotVar(String _id, AnalyzedPageEl _analyzing) {
        return !_analyzing.getInfosVars().contains(_id);
    }

    public static StringMap<StringList> getCurrentConstraints(AnalyzedPageEl _analyzing) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (EntryCust<String,TypeVar> e: getCurrentConstraintsFull(_analyzing).entryList()) {
            vars_.addEntry(e.getKey(), e.getValue().getConstraints());
        }
        return vars_;
    }

    public static void buildCurrentConstraintsFull(AnalyzedPageEl _page) {
        StringMap<TypeVar> vars_ = getCurrentConstraintsFull(_page);
        _page.getAvailableVariables().clear();
        for (EntryCust<String,TypeVar> e: vars_.entryList()) {
            _page.getAvailableVariables().addEntry(e.getKey(),e.getValue().getOffset());
        }
    }

    private static StringMap<TypeVar> getCurrentConstraintsFull(AnalyzedPageEl _page) {
        if (_page.isAnnotAnalysis()) {
            return new StringMap<TypeVar>();
        }
        Block bl_ = _page.getCurrentBlock();
        AccessedBlock r_ = _page.getImporting();
        StringMap<TypeVar> vars_ = new StringMap<TypeVar>();

        boolean static_;
        if (bl_ instanceof InfoBlock) {
            static_ = ((InfoBlock)bl_).isStaticField();
        } else {
            MemberCallingsBlock fct_ = _page.getCurrentFct();
            if (fct_ == null) {
                static_ = true;
            } else if (isExplicitFct(fct_)){
                static_ = false;
            } else {
                static_ = fct_.getStaticContext() == MethodAccessKind.STATIC;
            }
        }
        RootBlock root_ = null;
        if (r_ instanceof RootBlock) {
            root_ = (RootBlock) r_;
        }
        if (root_ != null && !static_) {
            for (TypeVar t: root_.getParamTypesMapValues()) {
                vars_.put(t.getName(), t);
            }
        }
        return vars_;
    }

    public static void appendParts(ContextEl _cont,int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        if (!_cont.getAnalyzing().isGettingParts()) {
            return;
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        AnaGeneType g_ = page_.getAnaGeneType(_in);
        if (!isFromCustFile(g_)) {
            return;
        }
        AccessedBlock r_ = page_.getImporting();
        int rc_ = _cont.getAnalyzing().getTraceIndex();
        String curr_ = ((Block)r_).getFile().getRenderFileName();
        String ref_ = ((RootBlock) g_).getFile().getRenderFileName();
        String rel_ = LinkageUtil.relativize(curr_,ref_);
        int id_ = ((RootBlock) g_).getIdRowCol();
        _parts.add(new PartOffset("<a title=\""+g_.getFullName()+"\" href=\""+rel_+"#m"+id_+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public static void appendTitleParts(ContextEl _cont, int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        if (!_cont.getAnalyzing().isGettingParts()) {
            return;
        }
        int rc_ = _cont.getAnalyzing().getTraceIndex();
        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(_in)+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public static boolean isFromCustFile(AnaGeneType _g) {
        if (!(_g instanceof RootBlock)) {
            return false;
        }
        return !((RootBlock)_g).getFile().isPredefined();
    }
    public static boolean isFinalField(ContextEl _cont, ClassField _classField) {
        String fullName_ = _classField.getClassName();
        String search_ = _classField.getFieldName();
        RootBlock cust_ = _cont.getAnalyzing().getAnaClassBody(fullName_);
        boolean finalField_ = false;
        for (Block b: ClassesUtil.getDirectChildren(cust_)) {
            if (!(b instanceof InfoBlock)) {
                continue;
            }
            InfoBlock i_ = (InfoBlock) b;
            int ind_ = StringList.indexOf(i_.getFieldName(), search_);
            if (ind_ < 0) {
                continue;
            }
            finalField_ = i_.isFinalField();
        }
        return finalField_;
    }
    public static FieldInfo getFieldInfo(ContextEl _cont, ClassField _classField) {
        String fullName_ = _classField.getClassName();
        String search_ = _classField.getFieldName();
        AnaGeneType cust_ = _cont.getAnalyzing().getAnaGeneType(fullName_);
        if (cust_ instanceof RootBlock) {
            RootBlock r_ = (RootBlock) cust_;
            for (Block b: ClassesUtil.getDirectChildren(r_)) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock) b;
                int ind_ = StringList.indexOf(i_.getFieldName(), search_);
                if (ind_ < 0) {
                    continue;
                }
                int valOffset_ = -1;
                if (i_ instanceof FieldBlock) {
                    valOffset_ = ((FieldBlock)i_).getValuesOffset().get(ind_);
                }
                if (i_ instanceof InnerTypeOrElement) {
                    valOffset_ = i_.getFieldNameOffset();
                }
                String type_ = i_.getImportedClassName();
                boolean final_ = i_.isFinalField();
                boolean static_ = i_.isStaticField();
                Accessed a_ = new Accessed(i_.getAccess(),cust_.getPackageName(),fullName_, r_.getOuterFullName());
                FieldInfo fieldInfo_ = FieldInfo.newFieldMetaInfo(search_, cust_.getFullName(), type_, static_, final_, a_, valOffset_);
                fieldInfo_.setFileName(b.getFile().getFileName());
                fieldInfo_.setMemberNumber(i_.getFieldNumber());
                fieldInfo_.setRootNumber(r_.getNumberAll());
                return fieldInfo_;
            }
            return null;
        }
        if (cust_ instanceof StandardType) {
            for (StandardField f: ((StandardType)cust_).getFields()) {
                if (!StringList.contains(f.getFieldName(), search_)) {
                    continue;
                }
                String type_ = f.getImportedClassName();
                boolean final_ = f.isFinalField();
                boolean static_ = f.isStaticField();
                Accessed a_ = new Accessed(AccessEnum.PUBLIC,"","","");
                return FieldInfo.newFieldMetaInfo(search_, cust_.getFullName(), type_, static_, final_, a_,-1);
            }
        }
        return null;
    }
}
