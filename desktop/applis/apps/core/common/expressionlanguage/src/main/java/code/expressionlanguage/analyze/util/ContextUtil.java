package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.*;
import code.util.*;
import code.util.core.StringUtil;

public final class ContextUtil {
    private ContextUtil() {
    }
    public static boolean canAccess(String _className, Accessed _block, AnalyzedPageEl _page) {
        CodeAccess code_ = processBegin(_className, _block, _page);
        RootBlock root_ = code_.getRoot();
        if (root_ == null) {
            return access(code_.getCode());
        }
        String belongPkg_ = _block.getPackageName();
        String rootPkg_ = root_.getPackageName();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            return processNormalProtected(_block, root_, belongPkg_, rootPkg_);
        }
        RootBlock outer_ = code_.getOuter();
        return processPackagePrivate(_block, root_, belongPkg_, rootPkg_, outer_);
    }

    private static boolean processNormalProtected(Accessed _block, RootBlock _root, String _belongPkg, String _rootPkg) {
        if (_root.isSubTypeOf(_block.getType())) {
            return true;
        }
        return StringUtil.quickEq(_belongPkg, _rootPkg);
    }

    public static boolean canAccessType(String _className, Accessed _block, AnalyzedPageEl _analyzing) {
        CodeAccess code_ = processBegin(_className, _block, _analyzing);
        RootBlock root_ = code_.getRoot();
        if (root_ == null) {
            return access(code_.getCode());
        }
        String belongPkg_ = _block.getPackageName();
        RootBlock parName_ = _block.getParent();
        String rootPkg_ = root_.getPackageName();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            if (parName_ != null) {
                if (root_.isSubTypeOf(parName_)) {
                    return true;
                }
            }
            return processNormalProtected(_block, root_, belongPkg_, rootPkg_);
        }
        RootBlock outer_ = code_.getOuter();
        return processPackagePrivate(_block, root_, belongPkg_, rootPkg_, outer_);
    }
    private static CodeAccess processBegin(String _className, Accessed _block, AnalyzedPageEl _analyzing) {
        if (_block.getAccess() == AccessEnum.PUBLIC) {
            return new CodeAccess(2,null,null);
        }
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_className);
        RootBlock root_ = _analyzing.getAnaClassBody(baseClass_);
        RootBlock outer_ = _block.outerParent();
        if (root_ == null) {
            return new CodeAccess(0,outer_,null);
        }
        return new CodeAccess(1,outer_,root_);
    }
    private static boolean access(int _code) {
        return _code == 2;
    }
    private static boolean processPackagePrivate(Accessed _block, RootBlock _root, String _belongPkg, String _rootPkg, RootBlock _outer) {
        if (_block.getAccess() == AccessEnum.PACKAGE) {
            return StringUtil.quickEq(_belongPkg, _rootPkg);
        }
        return _outer == _root.getOuterParent();
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
        return isEnumType(_type) || _type instanceof AnnotationBlock || _type instanceof RecordBlock;
    }
    public static boolean isHyperAbstract(AnaGeneType _type) {
        if (_type instanceof StandardClass) {
            return ((StandardClass)_type).isHyperAbstractType();
        }
        if (_type instanceof ClassBlock) {
            return ((ClassBlock)_type).isAbstractType() && ((ClassBlock)_type).isFinalType();
        }
        return false;
    }

    public static boolean isAbstractType(AnaGeneType _type) {
        if (_type instanceof StandardClass) {
            return ((StandardClass)_type).isAbstractStdType();
        }
        if (_type instanceof ClassBlock) {
            return ((ClassBlock)_type).isAbstractType();
        }
        return !(_type instanceof RecordBlock);
    }

    public static boolean isEnumType(AnaGeneType _type) {
        return _type instanceof EnumBlock || _type instanceof InnerElementBlock;
    }

    private static boolean isExplicitFct(FunctionBlock _fct) {
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

    public static void appendParts(int _begin, int _end, String _in, CustList<PartOffset> _parts, AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        AnaGeneType g_ = _page.getAnaGeneType(_in);
        if (!isFromCustFile(g_)) {
            return;
        }
        AccessedBlock r_ = _page.getImporting();
        int rc_ = _page.getTraceIndex();
        String curr_ = ((Block)r_).getFile().getRenderFileName();
        String ref_ = ((RootBlock) g_).getFile().getRenderFileName();
        String rel_ = LinkageUtil.relativize(curr_,ref_);
        int id_ = ((RootBlock) g_).getIdRowCol();
        _parts.add(new PartOffset("<a title=\""+g_.getFullName()+"\" href=\""+rel_+"#m"+id_+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public static void appendTitleParts(int _begin, int _end, String _in, CustList<PartOffset> _parts, AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        int rc_ = _page.getTraceIndex();
        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(_in)+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public static boolean isFromCustFile(AnaGeneType _g) {
        if (!(_g instanceof RootBlock)) {
            return false;
        }
        return !((RootBlock)_g).getFile().isPredefined();
    }
    public static boolean isFinalField(ClassField _classField, AnalyzedPageEl _page) {
        String fullName_ = _classField.getClassName();
        String search_ = _classField.getFieldName();
        RootBlock cust_ = _page.getAnaClassBody(fullName_);
        boolean finalField_ = false;
        for (Block b: ClassesUtil.getDirectChildren(cust_)) {
            if (!(b instanceof InfoBlock)) {
                continue;
            }
            InfoBlock i_ = (InfoBlock) b;
            int ind_ = StringUtil.indexOf(i_.getFieldName(), search_);
            if (ind_ < 0) {
                continue;
            }
            finalField_ = i_.isFinalField();
        }
        return finalField_;
    }
    public static FieldInfo getFieldInfo(AnaGeneType _anaGeneType, String _fullName, String _fieldName) {
        if (_anaGeneType instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _anaGeneType;
            for (Block b: ClassesUtil.getDirectChildren(r_)) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock) b;
                int ind_ = StringUtil.indexOf(i_.getFieldName(), _fieldName);
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
                Accessed a_ = new Accessed(i_.getAccess(), _anaGeneType.getPackageName(), r_);
                FieldInfo fieldInfo_ = FieldInfo.newFieldMetaInfo(_fieldName, _fullName, type_, static_, final_, a_, valOffset_);
                fieldInfo_.setFileName(b.getFile().getFileName());
                fieldInfo_.memberId(r_.getNumberAll(),i_.getFieldNumber());
                fieldInfo_.setFieldType(r_);
                return fieldInfo_;
            }
            return null;
        }
        for (StandardField f: ((StandardType) _anaGeneType).getFields()) {
            if (!StringUtil.quickEq(f.getFieldName(), _fieldName)) {
                continue;
            }
            String type_ = f.getImportedClassName();
            Accessed a_ = new Accessed(AccessEnum.PUBLIC,"", null);
            return FieldInfo.newFieldMetaInfo(_fieldName, _fullName, type_, true, true, a_,-1);
        }
        return null;
    }

    public static IterableAnalysisResult getCustomTypeBase(StringList _names, AnalyzedPageEl _page) {
        StringList out_ = new StringList();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        for (String f: _names) {
            String iterable_ = _page.getAliasIterable();
            String type_ = AnaTemplates.getGeneric(f,iterable_, mapping_, _page);
            if (!type_.isEmpty()) {
                out_.add(type_);
            }
        }
        return new IterableAnalysisResult(out_);
    }

    public static IterableAnalysisResult getCustomTableType(StringList _names, AnalyzedPageEl _page) {
        StringList out_ = new StringList();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        for (String f: _names) {
            String iterable_ = _page.getAliasIterableTable();
            String type_ = AnaTemplates.getGeneric(f,iterable_, mapping_, _page);
            if (!type_.isEmpty()) {
                out_.add(type_);
            }
        }
        return new IterableAnalysisResult(out_);
    }
}
