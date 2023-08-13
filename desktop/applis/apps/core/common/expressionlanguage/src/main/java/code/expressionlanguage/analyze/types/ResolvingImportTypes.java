package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ImportedField;
import code.expressionlanguage.analyze.ImportedMethod;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ResolvingImportTypes {
    private ResolvingImportTypes() {

    }

    public static ResolvedIdTypeContent lookupImportType(String _type, AccessedBlock _rooted, ReadyTypes _ready, AnalyzedPageEl _page) {
        return getRealSinglePrefixedMemberType(_type, _rooted,_ready, _page);
    }
    private static ResolvedIdTypeContent getRealSinglePrefixedMemberType(String _type, AccessedBlock _rooted, ReadyTypes _ready, AnalyzedPageEl _page) {
        String look_ = _type.trim();
        CustList<ResolvedIdTypeContent> types_ = new CustList<ResolvedIdTypeContent>();
        CustList<StringList> imports_ = _page.getImportingTypes();
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".") || !StringUtil.quickEq(StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.') + 1)), look_)) {
                    continue;
                }
                if (stopLookup(types_,tr_,look_,_ready, _page)) {
                    return null;
                }
            }
        }
        if (ResolvedIdType.onlyOneElt(types_)) {
            return types_.first();
        }
        if (!types_.isEmpty()) {
            return null;
        }
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            String type_ = StringExpUtil.removeDottedSpaces(StringUtil.concat(r_.getPackageName(),".",_type));
            RootBlock anaClassBody_ = _page.getAnaClassBody(type_);
            if (anaClassBody_ != null) {
                return new ResolvedIdTypeContent(type_,anaClassBody_);
            }
        }
        return importStarTypes(_type, _ready, _page, look_, types_);
    }

    private static ResolvedIdTypeContent importStarTypes(String _type, ReadyTypes _ready, AnalyzedPageEl _page, String _look, CustList<ResolvedIdTypeContent> _types) {
        CustList<StringList> imports_ = _page.getImportingTypes();
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".") || !StringUtil.quickEq(StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.') + 1)), "*")) {
                    continue;
                }
                if (stopLookup(_types,tr_,_look,_ready, _page)) {
                    return null;
                }
            }
        }
        if (ResolvedIdType.onlyOneElt(_types)) {
            return _types.first();
        }
        if (!_types.isEmpty()) {
            return null;
        }
        return defPkg(_type, _page);
    }

    private static ResolvedIdTypeContent defPkg(String _type, AnalyzedPageEl _page) {
        String defPkg_ = _page.getDefaultPkg();
        String type_ = StringExpUtil.removeDottedSpaces(StringUtil.concat(defPkg_,".",_type));
        AnaGeneType anaGeneType_ = _page.getAnaGeneType(type_);
        if (anaGeneType_ != null) {
            return new ResolvedIdTypeContent(type_,anaGeneType_);
        }
        return null;
    }

    private static boolean stopLookup(CustList<ResolvedIdTypeContent> _types, String _import, String _look, ReadyTypes _ready, AnalyzedPageEl _page) {
        String beginImp_ = StringExpUtil.removeDottedSpaces(_import.substring(0, _import.lastIndexOf('.')+1));
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        boolean stQualifier_ = false;
        if (StringExpUtil.startsWithKeyWord(beginImp_, keyWordStatic_)) {
            beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
            stQualifier_ = true;
        }
        String typeInner_ = StringUtil.concat(beginImp_, _look);
        StringList allInnerTypes_ = AnaInherits.getAllInnerTypes(typeInner_, _page);
        String owner_ = allInnerTypes_.first();
        AnaGeneType cl_ = _page.getAnaGeneType(owner_);
        ResolvedIdTypeContent res_ = new ResolvedIdTypeContent(owner_,cl_);
        if (cl_ != null) {
            int size_ = allInnerTypes_.size();
            for (int i = 2; i < size_; i+=2) {
                String i_ = allInnerTypes_.get(i).trim();
                if (!_ready.isReady(res_.getFullName())) {
                    return true;
                }
                CustList<ResolvedIdTypeContent> builtInners_ = AnaTypeUtil.getInners(res_.getFullName(), allInnerTypes_.get(i-1), i_, stQualifier_, _page);
                if (ResolvedIdType.onlyOneElt(builtInners_)) {
                    res_ = builtInners_.first();
                    continue;
                }
                return !builtInners_.isEmpty();
            }
            _types.add(res_);
        }
        return false;
    }

    public static CustList<CustList<ImportedMethod>> lookupImportStaticMethods(RootBlock _glClass, String _method, AnalyzedPageEl _page) {
        CustList<CustList<ImportedMethod>> methods_ = new CustList<CustList<ImportedMethod>>();
        CustList<StringList> imports_ = _page.getImportingTypes();
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (misMatchMethod(keyWordStatic_, _method.trim(), tr_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                staticMethods(_glClass, _method, _page, m_, st_);
            }
            methods_.add(m_);
        }
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (misMatchMethod(keyWordStatic_, "*", tr_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                staticMethods(_glClass, _method, _page, m_, st_);
            }
            methods_.add(m_);
        }
        return methods_;
    }

    private static void staticMethods(RootBlock _glClass, String _method, AnalyzedPageEl _page, CustList<ImportedMethod> _methods, String _typeName) {
        String typeLoc_ = StringExpUtil.removeDottedSpaces(_typeName.substring(0, _typeName.lastIndexOf('.')));
        ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
        fetchImportStaticMethods(_glClass, _method, _methods, foundCandidate_, MethodModifier.STATIC);
    }

    public static CustList<CustList<ImportedMethod>> lookupImportStaticCallMethods(RootBlock _glClass, String _method, AnalyzedPageEl _page) {
        CustList<CustList<ImportedMethod>> methods_ = new CustList<CustList<ImportedMethod>>();
        CustList<StringList> imports_ = _page.getImportingTypes();
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStaticCall();
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (misMatchMethod(keyWordStatic_, _method.trim(),tr_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                staticCallMethods(_glClass, _method, _page, m_, st_);
            }
            methods_.add(m_);
        }
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (misMatchMethod(keyWordStatic_, "*",tr_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                staticCallMethods(_glClass, _method, _page, m_, st_);
            }
            methods_.add(m_);
        }
        return methods_;
    }

    private static void staticCallMethods(RootBlock _glClass, String _method, AnalyzedPageEl _page, CustList<ImportedMethod> _methods, String _typeName) {
        String typeLoc_ = StringExpUtil.removeDottedSpaces(_typeName.substring(0, _typeName.lastIndexOf('.')));
        ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
        fetchImportStaticMethods(_glClass, _method, _methods, foundCandidate_, MethodModifier.STATIC_CALL);
        fetchImportStaticMethods(_glClass, _method, _methods, foundCandidate_, MethodModifier.STATIC);
    }

    private static void fetchImportStaticMethods(RootBlock _glClass, String _method, CustList<ImportedMethod> _methods, ResolvedIdTypeContent _typeLoc, MethodModifier _modif) {
        AnaGeneType root_ = _typeLoc.getGeneType();
        if (root_ instanceof RootBlock) {
            CustList<AnaFormattedRootBlock> allClasses_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock((RootBlock) root_));
            allClasses_.addAllElts(((RootBlock) root_).getAllGenericSuperTypesInfo());
            for (AnaFormattedRootBlock s: allClasses_) {
                RootBlock t_ = s.getRootBlock();
                for (NamedCalledFunctionBlock e: t_.getValidMethods()) {
                    if (filterStaticCustMethod(_glClass,_method, (RootBlock) root_, _modif,e,t_)) {
                        continue;
                    }
                    ClassMethodId clMet_ = new ClassMethodId(StringExpUtil.getIdFromAllTypes(s.getFormatted()), e.getId());
                    ImportedMethod value_ = new ImportedMethod(e.getImportedReturnType(), clMet_);
                    value_.setFileName(e.getFile().getFileName());
                    value_.memberId(t_.getNumberAll(),e.getNameOverrideNumber());
                    value_.setCustMethod(e);
                    value_.setType(t_);
                    value_.setOwner(t_);
                    addImportMethod(_methods, value_);
                }
            }
        }
        if (root_ instanceof StandardType) {
            CustList<StandardType> allClasses_ = new CustList<StandardType>((StandardType) root_);
            allClasses_.addAllElts(((StandardType) root_).getAllSuperStdTypes());
            for (StandardType s: allClasses_) {
                stdMethods(_method, _methods, _modif, s.getFullName(), s);
            }
        }
    }

    private static void stdMethods(String _method, CustList<ImportedMethod> _methods, MethodModifier _modif, String _s, StandardType _std) {
        for (StandardMethod e: _std.getMethods()) {
            if (e.getModifier() != _modif || !StringUtil.quickEq(_method.trim(), e.getId().getName())) {
                continue;
            }
            ClassMethodId clMet_ = new ClassMethodId(_s, e.getId());
            ImportedMethod value_ = new ImportedMethod(e.getImportedReturnType(), clMet_);
            value_.setStandardMethod(e);
            value_.setStd(_std);
            value_.setOwner(_std);
            addImportMethod(_methods, value_);
        }
    }

    private static boolean filterStaticCustMethod(RootBlock _glClass, String _method, RootBlock _typeLoc, MethodModifier _modif, NamedCalledFunctionBlock _e, RootBlock _t) {
        if (_e.getModifier() != _modif || !StringUtil.quickEq(_method.trim(), _e.getId().getName())) {
            return true;
        }
        String pkg_ = _t.getPackageName();
        Accessed a_ = new Accessed(_e.getAccess(), pkg_, _t);
        return !ContextUtil.canAccess(_typeLoc, a_) || !ContextUtil.canAccess(_glClass, a_);
    }
    private static void addImportMethod(CustList<ImportedMethod> _methods, ImportedMethod _value) {
        for (ImportedMethod e: _methods) {
            if (ClassMethodId.eq(e.getCustMethod(),e.getStandardMethod(), _value.getCustMethod(), _value.getStandardMethod())) {
                return;
            }
        }
        _methods.add(_value);
    }

    public static IdMap<AnaGeneType,ImportedField> lookupImportStaticFields(RootBlock _glClass, String _method, AnalyzedPageEl _page) {
        IdMap<AnaGeneType,ImportedField> methods_ = new IdMap<AnaGeneType,ImportedField>();
        int import_ = 1;
        CustList<StringList> imports_ = _page.getImportingTypes();
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (misMatchMethod(keyWordStatic_, _method.trim(),tr_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                staticFields(_glClass, _method, _page, methods_, import_, st_);
            }
            import_++;
        }
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (misMatchMethod(keyWordStatic_, "*",tr_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                staticFields(_glClass, _method, _page, methods_, import_, st_);
            }
            import_++;
        }
        return methods_;
    }

    private static void staticFields(RootBlock _glClass, String _method, AnalyzedPageEl _page, IdMap<AnaGeneType, ImportedField> _fields, int _nb, String _typeName) {
        String typeLoc_ = StringExpUtil.removeDottedSpaces(_typeName.substring(0, _typeName.lastIndexOf('.')));
        ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
        fetchImportStaticFields(_glClass, _method, _fields, _nb, foundCandidate_);
    }

    private static boolean misMatchMethod(String _keyWordStatic, String _criteria, String _tr) {
        if (!_tr.contains(".")) {
            return true;
        }
        if (!StringExpUtil.startsWithKeyWord(_tr, _keyWordStatic)) {
            return true;
        }
        String st_ = _tr.substring(_keyWordStatic.length()).trim();
        String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
        return !StringUtil.quickEq(end_, _criteria);
    }
    public static ResolvedIdTypeContent resolveCandidate(String _c, AnalyzedPageEl _page) {
        StringList allInnerTypes_ = AnaInherits.getAllInnerTypes(_c, _page);
        String owner_ = allInnerTypes_.first();
        AnaGeneType cl_ = _page.getAnaGeneType(owner_);
        ResolvedIdTypeContent res_ = new ResolvedIdTypeContent(owner_,cl_);
        if (cl_ != null) {
            int size_ = allInnerTypes_.size();
            for (int i = 2; i < size_; i+=2) {
                String i_ = allInnerTypes_.get(i).trim();
                String part_ = allInnerTypes_.get(i-1);
                CustList<ResolvedIdTypeContent> builtInners_ = AnaTypeUtil.getInners(res_.getFullName(),part_,i_,false, _page);
                if (!ResolvedIdType.onlyOneElt(builtInners_)) {
                    break;
                }
                res_ = builtInners_.first();
            }
        }
        return res_;
    }

    public static void fetchImportStaticFields(RootBlock _glClass, String _method, IdMap<AnaGeneType, ImportedField> _methods, int _import, ResolvedIdTypeContent _typeLoc) {
        AnaGeneType root_ = _typeLoc.getGeneType();
        if (root_ instanceof RootBlock) {
            CustList<AnaFormattedRootBlock> allClasses_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock((RootBlock) root_));
            allClasses_.addAllElts(((RootBlock) root_).getAllGenericSuperTypesInfo());
            for (AnaFormattedRootBlock s: allClasses_) {
                for (InfoBlock e: s.getRootBlock().getFieldsBlocks()) {
                    if (filterStaticCustField(_glClass,_method, (RootBlock) root_, s.getRootBlock(),e)) {
                        continue;
                    }
                    int v_ = AnaTypeUtil.getIndex(e,_method);
                    ImportedField value_ = new ImportedField(s.getRootBlock(),null,_import, e.getImportedClassName(), e.isFinalField(), v_,e.getFile().getFileName());
                    value_.memberId(s.getRootBlock().getNumberAll(),e.getElements().getFieldNumber());
                    addImport(_methods,s.getRootBlock(), value_);
                }
            }
        }
        if (root_ instanceof StandardType) {
            CustList<StandardType> allClasses_ = new CustList<StandardType>((StandardType) root_);
            allClasses_.addAllElts(((StandardType) root_).getAllSuperStdTypes());
            for (StandardType s: allClasses_) {
                stdFields(_method, _methods, _import, s);
            }
        }
    }

    private static void stdFields(String _method, IdMap<AnaGeneType, ImportedField> _methods, int _import, StandardType _std) {
        for (CstFieldInfo m: StandardClass.getCstFields(_std)) {
            if (!StringUtil.quickEq(m.getFieldName(), _method.trim())) {
                continue;
            }
            addImport(_methods, _std, new ImportedField(null,m,_import, m.getImportedClassName(),true,-1,""));
        }
    }

    private static boolean filterStaticCustField(RootBlock _glClass, String _method, RootBlock _typeLoc, RootBlock _cust, InfoBlock _e) {
        if (!_e.isStaticField()) {
            return true;
        }
        int v_ = AnaTypeUtil.getIndex(_e,_method);
        if (v_ < 0) {
            return true;
        }
        String pkg_ = _cust.getPackageName();
        Accessed a_ = new Accessed(_e.getAccess(),pkg_, _cust);
        return !ContextUtil.canAccess(_typeLoc, a_) || !ContextUtil.canAccess(_glClass, a_);
    }
    private static void addImport(IdMap<AnaGeneType,ImportedField> _methods, AnaGeneType _class, ImportedField _value) {
        for (EntryCust<AnaGeneType, ImportedField> e: _methods.entryList()) {
            if (e.getKey() == _class) {
                return;
            }
        }
        _methods.addEntry(_class,_value);
    }

}
