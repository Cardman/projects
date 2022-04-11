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
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, look_)) {
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
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, "*")) {
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
        boolean addImport_ = true;
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
                if (!builtInners_.isEmpty()) {
                    return true;
                }
                addImport_ = false;
                break;
            }
        } else {
            addImport_ = false;
        }
        if (addImport_) {
            _types.add(res_);
        }
        return false;
    }

    public static CustList<CustList<ImportedMethod>> lookupImportStaticMethods(String _glClass, String _method, AnalyzedPageEl _page) {
        CustList<CustList<ImportedMethod>> methods_ = new CustList<CustList<ImportedMethod>>();
        CustList<StringList> imports_ = _page.getImportingTypes();
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, _method.trim())) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = foundCandidate_.getGeneType();
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_.getFullName());
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_glClass, _method, m_, foundCandidate_.getFullName(), typesLoc_, _page, MethodModifier.STATIC);
            }
            methods_.add(m_);
        }
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = foundCandidate_.getGeneType();
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_.getFullName());
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_glClass, _method, m_, foundCandidate_.getFullName(), typesLoc_, _page, MethodModifier.STATIC);
            }
            methods_.add(m_);
        }
        return methods_;
    }

    public static CustList<CustList<ImportedMethod>> lookupImportStaticCallMethods(String _glClass, String _method, AnalyzedPageEl _page) {
        CustList<CustList<ImportedMethod>> methods_ = new CustList<CustList<ImportedMethod>>();
        CustList<StringList> imports_ = _page.getImportingTypes();
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStaticCall();
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, _method.trim())) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = foundCandidate_.getGeneType();
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_.getFullName());
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_glClass, _method, m_, foundCandidate_.getFullName(), typesLoc_, _page, MethodModifier.STATIC_CALL);
                fetchImportStaticMethods(_glClass, _method, m_, foundCandidate_.getFullName(), typesLoc_, _page, MethodModifier.STATIC);
            }
            methods_.add(m_);
        }
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = foundCandidate_.getGeneType();
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_.getFullName());
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_glClass, _method, m_, foundCandidate_.getFullName(), typesLoc_, _page, MethodModifier.STATIC_CALL);
                fetchImportStaticMethods(_glClass, _method, m_, foundCandidate_.getFullName(), typesLoc_, _page, MethodModifier.STATIC);
            }
            methods_.add(m_);
        }
        return methods_;
    }

    private static void fetchImportStaticMethods(String _glClass, String _method, CustList<ImportedMethod> _methods, String _typeLoc, StringList _typesLoc, AnalyzedPageEl _page, MethodModifier _modif) {
        for (String s: _typesLoc) {
            AnaGeneType super_ = _page.getAnaGeneType(s);
            if (super_ instanceof RootBlock) {
                RootBlock t_ = (RootBlock) super_;
                for (NamedCalledFunctionBlock e: t_.getOverridableBlocks()) {
                    if (e.getModifier() != _modif) {
                        continue;
                    }
                    if (!StringUtil.quickEq(_method.trim(), e.getId().getName())) {
                        continue;
                    }
                    String pkg_ = super_.getPackageName();
                    Accessed a_ = new Accessed(e.getAccess(), pkg_, t_);
                    if (!ContextUtil.canAccess(_typeLoc, a_, _page)) {
                        continue;
                    }
                    if (!ContextUtil.canAccess(_glClass, a_, _page)) {
                        continue;
                    }
                    ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                    ImportedMethod value_ = new ImportedMethod(e.getImportedReturnType(), clMet_);
                    value_.setFileName(e.getFile().getFileName());
                    value_.memberId(t_.getNumberAll(),e.getNameOverrideNumber());
                    value_.setCustMethod(e);
                    value_.setType(t_);
                    value_.setOwner(t_);
                    addImportMethod(_methods, value_);
                }
            }
            if (super_ instanceof StandardType) {
                for (StandardMethod e: ((StandardType) super_).getMethods()) {
                    if (e.getModifier() != _modif) {
                        continue;
                    }
                    if (!StringUtil.quickEq(_method.trim(), e.getId().getName())) {
                        continue;
                    }
                    ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                    ImportedMethod value_ = new ImportedMethod(e.getImportedReturnType(), clMet_);
                    value_.setStandardMethod(e);
                    value_.setOwner(super_);
                    addImportMethod(_methods, value_);
                }
            }
        }
    }
    private static void addImportMethod(CustList<ImportedMethod> _methods, ImportedMethod _value) {
        ClassMethodId id_ = _value.getId();
        for (ImportedMethod e: _methods) {
            if (e.getId().eq(id_)) {
                return;
            }
        }
        _methods.add(_value);
    }

    public static IdMap<AnaGeneType,ImportedField> lookupImportStaticFields(String _glClass, String _method, AnalyzedPageEl _page) {
        IdMap<AnaGeneType,ImportedField> methods_ = new IdMap<AnaGeneType,ImportedField>();
        int import_ = 1;
        CustList<StringList> imports_ = _page.getImportingTypes();
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, _method.trim())) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = foundCandidate_.getGeneType();
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_.getFullName());
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFieldsTmp(_glClass, _method, methods_, import_, foundCandidate_.getFullName(), typesLoc_, _page);
            }
            import_++;
        }
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                ResolvedIdTypeContent foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = foundCandidate_.getGeneType();
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_.getFullName());
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFieldsTmp(_glClass, _method, methods_, import_, foundCandidate_.getFullName(), typesLoc_, _page);
            }
            import_++;
        }
        return methods_;
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
                if (ResolvedIdType.onlyOneElt(builtInners_)) {
                    res_ = builtInners_.first();
                    continue;
                }
                break;
            }
        }
        return res_;
    }

    private static void fetchImportStaticFieldsTmp(String _glClass, String _method, IdMap<AnaGeneType,ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc, AnalyzedPageEl _page) {
        fetchImportStaticFields(_glClass,_method,_methods,_import,_typeLoc,_typesLoc, _page);
    }

    public static void fetchImportStaticFields(String _glClass, String _method, IdMap<AnaGeneType,ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc, AnalyzedPageEl _page) {
        for (String s: _typesLoc) {
            AnaGeneType super_ = _page.getAnaGeneType(s);
            if (super_ instanceof StandardType) {
                for (CstFieldInfo m: StandardClass.getCstFields((StandardType) super_)) {
                    if (!StringUtil.quickEq(m.getFieldName(), _method.trim())) {
                        continue;
                    }
                    addImport(_methods,super_, new ImportedField(_import, m.getImportedClassName(),true,-1,""));
                }
            }
            if (super_ instanceof RootBlock){
                RootBlock cust_ = (RootBlock) super_;
                String pkg_ = cust_.getPackageName();
                for (InfoBlock e: cust_.getFieldsBlocks()) {
                    if (!e.isStaticField()) {
                        continue;
                    }
                    int v_ = AnaTypeUtil.getIndex(e,_method);
                    if (v_ < 0) {
                        continue;
                    }
                    Accessed a_ = new Accessed(e.getAccess(),pkg_, cust_);
                    if (!ContextUtil.canAccess(_typeLoc, a_, _page)) {
                        continue;
                    }
                    if (!ContextUtil.canAccess(_glClass, a_, _page)) {
                        continue;
                    }
                    ImportedField value_ = new ImportedField(_import, e.getImportedClassName(), e.isFinalField(), v_,e.getFile().getFileName());
                    value_.memberId(cust_.getNumberAll(),e.getFieldNumber());
                    value_.setFieldType(cust_);
                    addImport(_methods,super_, value_);
                }
            }
        }
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
