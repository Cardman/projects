package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.MethodAccessKind;
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
            if (parName_ != null && root_.isSubTypeOf(parName_)) {
                return true;
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

    public static CustList<StringList> getBoundAllAll(AnaGeneType _type) {
        if (_type instanceof RootBlock) {
            return ((RootBlock)_type).getBoundAllAll();
        }
        return new CustList<StringList>();
    }

    public static CustList<TypeVar> getParamTypesMapValues(AnaGeneType _type) {
        if (_type instanceof RootBlock) {
            return ((RootBlock)_type).getParamTypesMapValues();
        }
        return new CustList<TypeVar>();
    }
    public static boolean isFinalType(RootBlock _type) {
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

    private static boolean isExplicitFct(WithContext _fct) {
        return _fct instanceof NamedCalledFunctionBlock
                && (((NamedCalledFunctionBlock) _fct).getKind() == MethodKind.EXPLICIT_CAST
        ||((NamedCalledFunctionBlock) _fct).getKind() == MethodKind.IMPLICIT_CAST
        ||((NamedCalledFunctionBlock) _fct).getKind() == MethodKind.TRUE_OPERATOR
                ||((NamedCalledFunctionBlock) _fct).getKind() == MethodKind.FALSE_OPERATOR);
    }

    public static boolean idDisjointToken(String _id, AnalyzedPageEl _analyzing) {
        return isNotVar(_id, _analyzing);
    }

    public static boolean isNotVar(String _id, AnalyzedPageEl _analyzing) {
        return !_analyzing.getInfosVars().contains(_id);
    }

    public static StringMap<StringList> getCurrentConstraints(AnalyzedPageEl _analyzing) {
        StringMap<TypeVar> currentConstraintsFull_ = getCurrentConstraintsFull(_analyzing);
        return currentConstraints(currentConstraintsFull_);
    }

    public static StringMap<StringList> currentConstraints(StringMap<TypeVar> _currentConstraintsFull) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (EntryCust<String,TypeVar> e: _currentConstraintsFull.entryList()) {
            vars_.addEntry(e.getKey(), e.getValue().getConstraints());
        }
        return vars_;
    }

    public static void buildCurrentConstraintsFull(AnalyzedPageEl _page) {
        StringMap<TypeVar> vars_ = getCurrentConstraintsFull(_page);
        setupAvailableVariables(_page, vars_);
    }

    public static void setupAvailableVariables(AnalyzedPageEl _page, StringMap<TypeVar> _next) {
        _page.getAvailableVariables().clear();
        for (EntryCust<String,TypeVar> e: _next.entryList()) {
            _page.getAvailableVariables().addEntry(e.getKey(),e.getValue().getOffset());
        }
    }

    private static StringMap<TypeVar> getCurrentConstraintsFull(AnalyzedPageEl _page) {
        if (_page.isAnnotAnalysis()) {
            return new StringMap<TypeVar>();
        }
        AbsBk bl_ = _page.getCurrentBlock();
        AccessedBlock r_ = _page.getImporting();
        StringMap<TypeVar> vars_ = new StringMap<TypeVar>();

        boolean static_;
        if (bl_ instanceof InfoBlock) {
            static_ = ((InfoBlock)bl_).isStaticField();
        } else {
            WithContext fct_ = _page.getCurrentCtx();
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
        for (AbsBk b: ClassesUtil.getDirectChildren(cust_)) {
            if (!(b instanceof InfoBlock)) {
                continue;
            }
            InfoBlock i_ = (InfoBlock) b;
            int ind_ = StringUtil.indexOf(i_.getElements().getFieldName(), search_);
            if (ind_ >= 0) {
                finalField_ = i_.isFinalField();
            }
        }
        return finalField_;
    }
    public static FieldInfo getFieldInfo(AnaGeneType _anaGeneType, String _fullName, String _fieldName) {
        ClassField id_ = new ClassField(_fullName, _fieldName);
        if (_anaGeneType instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _anaGeneType;
            for (AbsBk b: ClassesUtil.getDirectChildren(r_)) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock) b;
                int valOffset_ = AnaTypeUtil.getIndex(i_, _fieldName);
                if (valOffset_ >= 0) {
                    String type_ = i_.getImportedClassName();
                    boolean final_ = i_.isFinalField();
                    boolean static_ = i_.isStaticField();
                    Accessed a_ = new Accessed(i_.getAccess(), _anaGeneType.getPackageName(), r_);
                    FieldInfo fieldInfo_ = FieldInfo.newFieldMetaInfo(id_, type_, static_, final_, a_, valOffset_, b.getFile().getFileName());
                    fieldInfo_.memberId(r_.getNumberAll(), i_.getElements().getFieldNumber());
                    return fieldInfo_;
                }
            }
            return null;
        }
        for (CstFieldInfo f: StandardClass.getCstFields((StandardType) _anaGeneType)) {
            if (!StringUtil.quickEq(f.getFieldName(), _fieldName)) {
                continue;
            }
            String type_ = f.getImportedClassName();
            Accessed a_ = new Accessed(AccessEnum.PUBLIC,"", null);
            return FieldInfo.newFieldMetaInfo(id_, type_, true, true, a_,-1,"");
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
            String type_ = AnaInherits.getGeneric(f,iterable_, mapping_, _page);
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
            String type_ = AnaInherits.getGeneric(f,iterable_, mapping_, _page);
            if (!type_.isEmpty()) {
                out_.add(type_);
            }
        }
        return new IterableAnalysisResult(out_);
    }
}
