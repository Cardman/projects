package code.expressionlanguage.analyze.util;

import code.expressionlanguage.ContextEl;
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
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ContextUtil {
    private ContextUtil() {
    }

    public static boolean canAccess(String _className, ExecBlock _block, ContextEl _context) {
        if (!(_block instanceof AccessibleBlock)) {
            return true;
        }
        return canAccess(_className,(AccessibleBlock)_block,_context);
    }
    public static boolean canAccess(String _className, AccessibleBlock _block, ContextEl _context) {
        if (_block.getAccess() == AccessEnum.PUBLIC) {
            return true;
        }
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock root_ = _context.getClasses().getClassBody(baseClass_);
        if (root_ == null) {
            return false;
        }
        String belongPkg_ = _block.getPackageName();
        ExecRootBlock parType_ = null;
        if (_block instanceof ExecRootBlock) {
            parType_ = ((ExecRootBlock) _block).getParentType();
        }
        String rootPkg_ = root_.getPackageName();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            if (parType_ != null) {
                if (root_.isSubTypeOf(parType_.getFullName(),_context)) {
                    return true;
                }
            }
            if (root_.isSubTypeOf(_block.getFullName(),_context)) {
                return true;
            }
            return StringList.quickEq(belongPkg_, rootPkg_);
        }
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

    public static boolean isValidSingleToken(ContextEl _cont,String _id) {
        if (!isValidToken(_cont,_id)) {
            return false;
        }
        return idDisjointToken(_cont,_id);
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

    public static boolean isValidToken(ContextEl _cont, String _id) {
        Block b_ = _cont.getAnalyzing().getCurrentBlock();
        boolean pred_ = b_.getFile().isPredefined();
        return isValidToken(_cont,_id, pred_);
    }

    public static boolean isValidToken(ContextEl _cont, String _id, boolean _pred) {
        if (_pred) {
            if (!StringList.isDollarWord(_id)) {
                return false;
            }
        } else {
            if (!StringList.isWord(_id)) {
                return false;
            }
        }
        if (PrimitiveTypeUtil.isPrimitive(_id, _cont)) {
            return false;
        }
        if (_cont.getKeyWords().isKeyWordNotVar(_id)) {
            return false;
        }
        if (StringList.quickEq(_id, _cont.getStandards().getAliasVoid())) {
            return false;
        }
        return !StringExpUtil.isDigit(_id.charAt(0));
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
        ExecAccessingImportingBlock r_ =_cont.getCurrentGlobalBlock();
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
        if (r_ instanceof ExecRootBlock && !static_) {
            for (TypeVar t: ((ExecRootBlock)r_).getParamTypesMapValues()) {
                vars_.put(t.getName(), t);
            }
        }
        return vars_;
    }

    public static void appendParts(ContextEl _cont,int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        if (!_cont.isCovering()) {
            return;
        }
        GeneType g_ = _cont.getClassBody(_in);
        if (!LinkageUtil.isFromCustFile(g_)) {
            return;
        }
        ExecAccessingImportingBlock r_ = _cont.getCurrentGlobalBlock();
        int rc_ = _cont.getCurrentLocationIndex();
        String curr_ = ((ExecBlock)r_).getFile().getRenderFileName();
        String ref_ = ((ExecRootBlock) g_).getFile().getRenderFileName();
        String rel_ = LinkageUtil.relativize(curr_,ref_);
        int id_ = ((ExecRootBlock) g_).getIdRowCol();
        _parts.add(new PartOffset("<a title=\""+g_.getFullName()+"\" href=\""+rel_+"#m"+id_+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public static void appendTitleParts(ContextEl _cont, int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        if (!_cont.isCovering()) {
            return;
        }
        int rc_ = _cont.getCurrentLocationIndex();
        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(_in)+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public static FieldInfo getFieldInfo(ContextEl _cont, ClassField _classField) {
        GeneType g_ = _cont.getClassBody(_classField.getClassName());
        String search_ = _classField.getFieldName();
        if (g_ instanceof ExecRootBlock) {
            for (ExecBlock b: ExecBlock.getDirectChildren((ExecBlock) g_)) {
                if (!(b instanceof ExecInfoBlock)) {
                    continue;
                }
                ExecInfoBlock i_ = (ExecInfoBlock) b;
                if (!StringList.contains(i_.getFieldName(), search_)) {
                    continue;
                }
                String type_ = i_.getImportedClassName();
                boolean final_ = i_.isFinalField();
                boolean static_ = i_.isStaticField();
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_,i_);
            }
            return null;
        }
        if (g_ instanceof StandardType) {
            for (EntryCust<String, StandardField> f: ((StandardType)g_).getFields().entryList()) {
                StandardField f_ = f.getValue();
                if (!StringList.contains(f_.getFieldName(), search_)) {
                    continue;
                }
                String type_ = f_.getImportedClassName();
                boolean final_ = f_.isFinalField();
                boolean static_ = f_.isStaticField();
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_, f_);
            }
        }
        return null;
    }
}
