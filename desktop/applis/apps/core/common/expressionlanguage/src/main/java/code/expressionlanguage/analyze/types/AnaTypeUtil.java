package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.core.SortConstants;
import code.util.core.StringUtil;

public final class AnaTypeUtil {
    private static final byte DOUBLE_CASTING = 7;
    private static final byte FLOAT_CASTING = 6;
    private static final byte LONG_CASTING = 5;
    private static final byte INT_CASTING = 4;
    private static final byte CHAR_CASTING = 3;
    private static final byte SHORT_CASTING = 2;
    private static final byte BYTE_CASTING = 1;

    private AnaTypeUtil() {
    }

    public static void buildOverrides(RootBlock _type, AnalyzedPageEl _page) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: _type.getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (OverridingMethodDto e: getAllInstanceSignatures(_type)) {
            loopInstanceMethod(_type, _page, vars_, e);
        }
    }

    private static void loopInstanceMethod(RootBlock _type, AnalyzedPageEl _page, StringMap<StringList> _vars, OverridingMethodDto _e) {
        FormattedMethodId key_ = _e.getFormattedMethodId();
        for (AbsBk b: ClassesUtil.getDirectChildren(_type)) {
            if (b instanceof InternOverrideBlock) {
                for (OverridingMethodDto o: ((InternOverrideBlock) b).getOverrides()) {
                    if (o.getFormattedMethodId().eq(key_)) {
                        _e.getMethodIds().clear();
                        _e.getMethodIds().addAllElts(o.getMethodIds());
                    }
                }
            }
        }
        CustList<OverridingRelation> pairs_ = buildPairs(_e);
        for (OverridingRelation l: pairs_) {
            loopPairs(_type, _page, _vars, key_, l);
        }
    }

    private static CustList<OverridingRelation> buildPairs(OverridingMethodDto _e) {
        CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
        CustList<GeneStringOverridable> allMethods_ = _e.getMethodIds();
        for (GeneStringOverridable c: allMethods_) {
            IdList<AnaGeneType> allSuperTypes_ = c.getType().getAllSuperTypesInfo();
            for (GeneStringOverridable s: allMethods_) {
                if (c.getType() != s.getType() && !allSuperTypes_.containsObj(s.getType())) {
                    continue;
                }
                OverridingRelation ovRel_ = new OverridingRelation();
                ovRel_.setSubMethod(c);
                ovRel_.setSub(c.getType());
                ovRel_.setSupMethod(s);
                ovRel_.setSup(s.getType());
                pairs_.add(ovRel_);
            }
        }
        return pairs_;
    }

    private static void loopPairs(RootBlock _type, AnalyzedPageEl _page, StringMap<StringList> _vars, FormattedMethodId _key, OverridingRelation _l) {
        GeneStringOverridable subId_ = _l.getSubMethod();
        GeneStringOverridable supId_ = _l.getSupMethod();
        Accessed subAcc_ = new Accessed(subId_.getBlock().getAccess(), _l.getSub().getPackageName(), _l.getSub());
        Accessed supAcc_ = new Accessed(supId_.getBlock().getAccess(), _l.getSup().getPackageName(), _l.getSup());
        if (subId_.eq(supId_)) {
            if (ContextUtil.canAccess(_type.getFullName(), subAcc_, _page)) {
                addDtoClass(_type.getAllOverridingMethods(), _key, subId_);
            }
        } else if (ContextUtil.canAccess(subId_.getGeneString(), supAcc_, _page)) {
            String formattedRetDer_ = quickFormat(subId_);
            String formattedRetBase_ = quickFormat(supId_);
            if (supId_.getBlock().isFinalMethod()) {
                FoundErrorInterpret err_;
                err_ = new FoundErrorInterpret();
                err_.setFile(_type.getFile());
                err_.setIndexFile(supId_.getBlock().getNameOffset());
                //sub method name len
                err_.buildError(_page.getAnalysisMessages().getDuplicatedFinal(),
                        supId_.getBlock().getId().getSignature(_page.getDisplayedStrings()),
                        supId_.getGeneString());
                subId_.getBlock().addNameErrors(err_);
                _page.addLocError(err_);
                return;
            }
            if (supId_.getBlock().getAccess().isStrictMoreAccessibleThan(subId_.getBlock().getAccess())) {
                FoundErrorInterpret err_;
                err_ = new FoundErrorInterpret();
                err_.setFile(_type.getFile());
                err_.setIndexFile(supId_.getBlock().getAccessOffset());
                //key word access or method name
                err_.buildError(_page.getAnalysisMessages().getMethodsAccesses(),
                        supId_.getGeneString(),
                        supId_.getBlock().getId().getSignature(_page.getDisplayedStrings()),
                        subId_.getGeneString(),
                        subId_.getBlock().getId().getSignature(_page.getDisplayedStrings()));
                subId_.getBlock().addNameErrors(err_);
                _page.addLocError(err_);
                return;
            }
            if (supId_.getBlock().mustHaveSameRet()) {
                if (!StringUtil.quickEq(formattedRetBase_, formattedRetDer_)) {
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFile(_type.getFile());
                    err_.setIndexFile(supId_.getBlock().getReturnTypeOffset());
                    //sub return type len
                    err_.buildError(_page.getAnalysisMessages().getBadReturnTypeIndexer(),
                            formattedRetBase_,
                            supId_.getBlock().getId().getSignature(_page.getDisplayedStrings()),
                            supId_.getGeneString(),
                            formattedRetDer_,
                            subId_.getBlock().getId().getSignature(_page.getDisplayedStrings()),
                            subId_.getGeneString());
                    subId_.getBlock().addNameErrors(err_);
                    _page.addLocError(err_);
                    return;
                }
                addDtoClass(_type.getAllOverridingMethods(), _key, subId_);
                addDtoClass(_type.getAllOverridingMethods(), _key, supId_);
                return;
            }
            if (!AnaInherits.isReturnCorrect(formattedRetBase_, formattedRetDer_, _vars, _page)) {
                FoundErrorInterpret err_;
                err_ = new FoundErrorInterpret();
                err_.setFile(_type.getFile());
                err_.setIndexFile(supId_.getBlock().getReturnTypeOffset());
                //sub return type len
                err_.buildError(_page.getAnalysisMessages().getBadReturnTypeInherit(),
                        formattedRetDer_,
                        subId_.getBlock().getId().getSignature(_page.getDisplayedStrings()),
                        subId_.getGeneString(),
                        formattedRetBase_,
                        supId_.getBlock().getId().getSignature(_page.getDisplayedStrings()),
                        supId_.getGeneString());
                subId_.getBlock().addNameErrors(err_);
                _page.addLocError(err_);
                return;
            }
            addDtoClass(_type.getAllOverridingMethods(), _key, subId_);
            addDtoClass(_type.getAllOverridingMethods(), _key, supId_);
        }
    }

    private static String quickFormat(GeneStringOverridable _id) {
        return AnaInherits.quickFormat(_id.getFormat(), _id.getBlock().getImportedReturnType());
    }

    private static CustList<OverridingMethodDto> getAllInstanceSignatures(RootBlock _r) {
        CustList<OverridingMethodDto> map_;
        map_ = new CustList<OverridingMethodDto>();
        for (NamedCalledFunctionBlock b: _r.getOverridableBlocks()) {
            if (b.hiddenInstance()) {
                continue;
            }
            MethodId m_ = b.getId();
            OverridingMethodDto o_ = new OverridingMethodDto(new FormattedMethodId(m_));
            o_.getMethodIds().add(new GeneStringOverridable(new AnaFormattedRootBlock(_r), b));
            map_.add(o_);
        }
        for (AnaFormattedRootBlock s: _r.getAllGenericSuperTypesInfo()) {
            RootBlock b_ = s.getRootBlock();
            for (NamedCalledFunctionBlock b: b_.getOverridableBlocks()) {
                if (b.hiddenInstance()) {
                    continue;
                }
                addDtoClass(map_, new FormattedMethodId(b.getId().quickFormat(AnaInherits.getVarTypes(s))), new GeneStringOverridable(s, b));
            }
        }
        return map_;
    }

    private static void addDtoClass(CustList<OverridingMethodDto> _map, FormattedMethodId _key, GeneStringOverridable _gene) {
        boolean found_ = false;
        for (OverridingMethodDto o: _map) {
            if (o.getFormattedMethodId().eq(_key)) {
                o.getMethodIds().add(_gene);
                found_ = true;
                break;
            }
        }
        if (!found_) {
            OverridingMethodDto o_ = new OverridingMethodDto(_key);
            o_.getMethodIds().add(_gene);
            _map.add(o_);
        }
    }

    public static void checkInterfaces(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getAllFoundTypes()) {
            recordType(_page, c);
        }
        for (RootBlock c: _page.getAllFoundTypes()) {
//            ExecRootBlock type_ = _page.getMapTypes().getVal(c);
            staticInitType(_page, c);
        }
        for (RootBlock c: _page.getAllFoundTypes()) {
            _page.setCurrentFile(c.getFile());
            if (c instanceof RecordBlock) {
                ins(_page, c);
                st(_page, c);
                continue;
            }
            if (c instanceof UniqueRootedBlock) {
                st(_page, c);
            }
        }
    }

    private static void staticInitType(AnalyzedPageEl _page, RootBlock _c) {
        _page.setImporting(_c);
        _page.setImportingAcces(new TypeAccessor(_c.getFullName()));
        _page.setImportingTypes(_c);
        _page.setCurrentBlock(_c);
        ClassesUtil.globalType(_page, _c);
        StringList ints_ = _c.getStaticInitInterfaces();
        int len_ = ints_.size();
        if (len_ > 0 && (_c instanceof InterfaceBlock)) {
            FoundErrorInterpret enum_;
            enum_ = new FoundErrorInterpret();
            enum_.setFile(_c.getFile());
            enum_.setIndexFile(_page);
            //original id len
            enum_.buildError(_page.getAnalysisMessages().getCallIntNoNeed(),
                    _c.getFullName());
            _page.addLocError(enum_);
            _c.addNameErrors(enum_);
        }
        CustList<ResolvedIdTypeContent> resolvedIdTypes_ = new CustList<ResolvedIdTypeContent>();
        for (int i = 0; i < len_; i++) {
            int offset_ = _c.getStaticInitInterfacesOffset().get(i);
            _page.setCurrentBlock(_c);
            _page.setSumOffset(offset_);
            _page.zeroOffset();
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(_c.getRefMappings());
            ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(0, ints_.get(i), _page);
            resolvedIdTypes_.add(resolvedIdType_.getContent());
            String base_ = resolvedIdType_.getFullName();
            _c.getPartsStaticInitInterfacesOffset().add(resolvedIdType_.getDels());
            AnaGeneType r_ = resolvedIdType_.getContent().getGeneType();
            AnaFormattedRootBlock found_ = AnaInherits.getOverridingFullTypeByBases(_c, r_);
            if (!(r_ instanceof InterfaceBlock)||found_ == null) {
                errKindType(_page, _c, base_);
            } else {
//                    type_.getStaticInitImportedInterfaces().add(base_);
                _c.getStaticInitImportedInterfaces().add((RootBlock) r_);
            }
        }
        checkInherits(_page, _c, resolvedIdTypes_, _c.getStaticInitInterfacesOffset());
    }

    private static void recordType(AnalyzedPageEl _page, RootBlock _c) {
        _page.setImporting(_c);
        _page.setImportingAcces(new TypeAccessor(_c.getFullName()));
        _page.setImportingTypes(_c);
        _page.setCurrentBlock(_c);
        ClassesUtil.globalType(_page, _c);
        StringList ints_ = _c.getInstInitInterfaces();
        int len_ = ints_.size();
        CustList<ResolvedIdTypeContent> resolvedIdTypes_ = new CustList<ResolvedIdTypeContent>();
        for (int i = 0; i < len_; i++) {
            int offset_ = _c.getInstInitInterfacesOffset().get(i);
            _page.setCurrentBlock(_c);
            _page.setSumOffset(offset_);
            _page.zeroOffset();
            _page.getMappingLocal().clear();
            _page.getMappingLocal().putAllMap(_c.getRefMappings());
            ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(0, ints_.get(i), _page);
            resolvedIdTypes_.add(resolvedIdType_.getContent());
            String base_ = resolvedIdType_.getFullName();
            _c.getPartsInstInitInterfacesOffset().add(resolvedIdType_.getDels());
            AnaGeneType r_ = resolvedIdType_.getContent().getGeneType();
            AnaFormattedRootBlock found_ = AnaInherits.getOverridingFullTypeByBases(_c, r_);
            if (!(r_ instanceof InterfaceBlock)||found_ == null) {
                errKindType(_page, _c, base_);
            } else {
                _c.getInstanceInitImportedInterfaces().add(found_);
            }
        }
        checkInherits(_page, _c, resolvedIdTypes_, _c.getInstInitInterfacesOffset());
    }

    private static void errKindType(AnalyzedPageEl _page, RootBlock _c, String _base) {
        FoundErrorInterpret enum_;
        enum_ = new FoundErrorInterpret();
        enum_.setFile(_c.getFile());
        enum_.setIndexFile(_page);
        //interface len
        enum_.buildError(_page.getAnalysisMessages().getCallIntOnly(),
                _base);
        _page.addLocError(enum_);
        if (!_base.isEmpty()) {
            _c.addNameErrors(enum_);
        }
    }

    private static void checkInherits(AnalyzedPageEl _page, RootBlock _root, CustList<ResolvedIdTypeContent> _resolvedIdTypes, Ints _offsets) {
        CustList<FoundTypeErrorDto> errors_ = new CustList<FoundTypeErrorDto>();
        checkInherits(_page, _resolvedIdTypes, _offsets,errors_);
        for (FoundTypeErrorDto f: errors_) {
            FoundErrorInterpret undef_;
            undef_ = new FoundErrorInterpret();
            undef_.setFile(_root.getFile());
            undef_.setIndexFile(f.getIndexInType());
            undef_.setBuiltError(f.getSolved());
            _page.addLocError(undef_);
            _root.addNameErrors(undef_);
        }
    }

    public static void checkInherits(AnalyzedPageEl _page, CustList<ResolvedIdTypeContent> _resolvedIdTypes, Ints _offsets, CustList<FoundTypeErrorDto> _errors) {
        int len_ = _resolvedIdTypes.size();
        for (int i = 0; i < len_; i++) {
            String sup_ = _resolvedIdTypes.get(i).getFullName();
            RootBlock rs_ = _page.getAnaClassBody(sup_);
            if (rs_ == null) {
                continue;
            }
            RootBlock rsSup_ = rs_;
            for (int j = i + 1; j < len_; j++) {
                String sub_ = _resolvedIdTypes.get(j).getFullName();
                rs_ = _page.getAnaClassBody(sub_);
                if (rs_ == null) {
                    continue;
                }
                if (rsSup_.isSubTypeOf(rs_)) {
                    int offset_ = _offsets.get(j);
                    _errors.add(new FoundTypeErrorDto(offset_,"",FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getCallIntInherits(),
                            sup_,
                            sub_)));
                }
            }
        }
    }

    private static void ins(AnalyzedPageEl _page, RootBlock _c) {
        StringList trimmedInt_ = getInts(_c);
        StringList filteredStatic_ = feedInst(_page, _c);
        lookForErrors(_page, _c, trimmedInt_, filteredStatic_);
    }

    public static StringList getInts(RootBlock _c) {
        CustList<AnaFormattedRootBlock> ints_ = _c.getInstanceInitImportedInterfaces();
        return getStrings(ints_);
    }

    public static StringList getStrings(CustList<AnaFormattedRootBlock> _ints) {
        StringList trimmedInt_ = new StringList();
        for (AnaFormattedRootBlock i: _ints) {
            trimmedInt_.add(i.getRootBlock().getFullName());
        }
        return trimmedInt_;
    }

    public static StringList feedInst(AnalyzedPageEl _page, RootBlock _c) {
        StringList all_ = _c.getAllSuperTypes();
        StringList allCopy_ = new StringList(all_);
        StringList filteredStatic_ = new StringList();
        for (String i: allCopy_) {
            feedInst(_page, filteredStatic_, i);
        }
        return filteredStatic_;
    }

    public static void feedInst(AnalyzedPageEl _page, StringList _filterInst, String _i) {
        RootBlock int_ = _page.getAnaClassBody(_i);
        if (!(int_ instanceof InterfaceBlock)) {
            return;
        }
        for (AbsBk b: ClassesUtil.getDirectChildren(int_)) {
            if (b instanceof InfoBlock) {
                InfoBlock a_ = (InfoBlock) b;
                if (a_.isStaticField()) {
                    continue;
                }
                _filterInst.add(_i);
            }
            if (b instanceof InstanceBlock) {
                _filterInst.add(_i);
            }
        }
    }

    private static void st(AnalyzedPageEl _page, RootBlock _c) {
        CustList<RootBlock> ints_ = _c.getStaticInitImportedInterfaces();
        StringList trimmedInt_ = new StringList();
        for (RootBlock i: ints_) {
            trimmedInt_.add(i.getFullName());
        }
        StringList all_ = _c.getAllSuperTypes();
        StringList allCopy_ = new StringList(all_);
        String clName_ = _c.getImportedDirectGenericSuperClass();
        String id_ = StringExpUtil.getIdFromAllTypes(clName_);
        RootBlock superType_ = _page.getAnaClassBody(id_);
        if (superType_ instanceof UniqueRootedBlock) {
            StringUtil.removeAllElements(allCopy_, superType_.getAllSuperTypes());
        }
        StringList filteredStatic_ = new StringList();
        for (String i: allCopy_) {
            feedStatic(_page, filteredStatic_, i);
        }
        lookForErrors(_page, _c, trimmedInt_, filteredStatic_);
    }

    private static void feedStatic(AnalyzedPageEl _page, StringList _filteredStatic, String _i) {
        RootBlock int_ = _page.getAnaClassBody(_i);
        if (!(int_ instanceof InterfaceBlock)) {
            return;
        }
        for (AbsBk b: ClassesUtil.getDirectChildren(int_)) {
            if (b instanceof InfoBlock) {
                InfoBlock a_ = (InfoBlock) b;
                if (!a_.isStaticField()) {
                    continue;
                }
                boolean allCst_ = allCst(_page, _i, a_);
                if (!allCst_) {
                    _filteredStatic.add(_i);
                }
            }
            if (b instanceof StaticBlock) {
                _filteredStatic.add(_i);
            }
        }
    }

    private static boolean allCst(AnalyzedPageEl _page, String _i, InfoBlock _a) {
        boolean allCst_ = true;
        for (String n: _a.getFieldName()) {
            StringMap<StringMap<Struct>> staticFields_ = _page.getStaticFields();
            if (NumParsers.getStaticField(new ClassField(_i, n), staticFields_) == null) {
                allCst_ = false;
                break;
            }
        }
        return allCst_;
    }

    private static void lookForErrors(AnalyzedPageEl _page, RootBlock _c, StringList _trimmedInt, StringList _filteredStatic) {
        if (!StringUtil.equalsSet(_filteredStatic, _trimmedInt)) {
            for (String s : _filteredStatic) {
                if (!StringUtil.contains(_trimmedInt, s)) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFile(_c.getFile());
                    undef_.setIndexFile(_c.getIdRowCol());
                    //last parenthese
                    undef_.buildError(_page.getAnalysisMessages().getCallIntNeedType(),
                            s);
                    _page.addLocError(undef_);
                    _c.addNameErrors(undef_);
                }
            }
            for (String s : _trimmedInt) {
                if (!StringUtil.contains(_filteredStatic, s)) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFile(_c.getFile());
                    undef_.setIndexFile(_c.getIdRowCol());
                    //type len
                    undef_.buildError(_page.getAnalysisMessages().getCallIntNoNeedType(),
                            s);
                    _page.addLocError(undef_);
                    _c.addNameErrors(undef_);
                }
            }
        }
    }

    public static StringMap<MappingLocalType> getRefMappings(StringMap<MappingLocalType> _map) {
        StringMap<MappingLocalType> map_ = new StringMap<MappingLocalType>();
        for (EntryCust<String,MappingLocalType> e: _map.entryList()) {
            MappingLocalType value_ = e.getValue();
            if (!value_.getType().isReference()) {
                continue;
            }
            map_.addEntry(e.getKey(), value_);
        }
        return map_;
    }

    public static OwnerListResultInfo getInners(String _root, String _innerName, AnalyzedPageEl _page) {
        return getOwners(_root, _innerName, _page);
    }
    public static OwnerListResultInfo getOwners(String _root, String _innerName, AnalyzedPageEl _page) {
        return getOwners(_root,_innerName,false, _page);
    }
    public static CustList<ResolvedIdTypeContent> getInners(String _root, String _sep, String _innerName, boolean _staticOnly, AnalyzedPageEl _page) {
        if (StringUtil.quickEq(_sep,".")) {
            return getOwners(_root, _innerName,_staticOnly, _page).found();
        }
        return getEnumOwners(_root, _innerName, _page).found();
    }
    private static OwnerListResultInfo getOwners(String _root, String _innerName, boolean _staticOnly, AnalyzedPageEl _page) {
        StringList ids_ = new StringList(_root);
        OwnerListResultInfo owners_ = new OwnerListResultInfo();
        StringList visited_ = new StringList();
        while (true) {
            StringList next_ = new StringList();
            for (String s: ids_) {
                RootBlock g_ = _page.getAnaClassBody(s);
                if (g_ == null) {
                    continue;
                }
                added(_innerName, _staticOnly, owners_, s, g_);
                for (String t: g_.getImportedDirectBaseSuperTypes().values()) {
                    addIfNotFound(visited_, next_, t);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            ids_ = next_;
        }
        return owners_.getSubclasses();
    }
    public static OwnerListResultInfo getGenericOwners(String _root, String _innerName, AnalyzedPageEl _page) {
        StringList ids_ = new StringList(_root);
        OwnerListResultInfo owners_ = new OwnerListResultInfo();
        StringList visited_ = new StringList();
        while (true) {
            StringList next_ = new StringList();
            for (String s: ids_) {
                String id_ = StringExpUtil.getIdFromAllTypes(s);
                RootBlock g_ = _page.getAnaClassBody(id_);
                if (g_ == null) {
                    continue;
                }
                added(_innerName, false, owners_, s, g_);
                genericSuperTypes(_page, visited_, next_, s, g_);
            }
            if (next_.isEmpty()) {
                break;
            }
            ids_ = next_;
        }
        return owners_.getSubclasses();
    }

    private static void genericSuperTypes(AnalyzedPageEl _page, StringList _visited, StringList _next, String _s, RootBlock _g) {
        if (!AnaInherits.correctNbParameters(_g, _s, _page)) {
            for (AnaFormattedRootBlock t: _g.getImportedDirectSuperTypesInfo()) {
                String format_ = StringExpUtil.getIdFromAllTypes(t.getFormatted());
                addIfNotFound(_visited, _next, format_);
            }
        } else {
            for (AnaFormattedRootBlock t : _g.getImportedDirectSuperTypesInfo()) {
                String format_ = AnaInherits.format(_g, _s, t.getFormatted());
                addIfNotFound(_visited, _next, format_);
            }
        }
    }

    private static void addIfNotFound(StringList _visited, StringList _next, String _format) {
        if (StringUtil.contains(_visited, _format)) {
            return;
        }
        _visited.add(_format);
        _next.add(_format);
    }

    private static void added(String _innerName, boolean _staticOnly, OwnerListResultInfo _owners, String _s, RootBlock _sub) {
        for (RootBlock b: accessedClassMembers(_sub)) {
            if (_staticOnly && !b.withoutInstance()) {
                continue;
            }
            String name_ = b.getName();
            if (StringUtil.quickEq(name_, _innerName)) {
                _owners.add(_s,_sub,name_,b);
            }
        }
    }
    public static OwnerListResultInfo getEnumOwners(String _root, String _innerName, AnalyzedPageEl _page) {
        OwnerListResultInfo owners_ = new OwnerListResultInfo();
        String id_ = StringExpUtil.getIdFromAllTypes(_root);
        RootBlock g_ = _page.getAnaClassBody(id_);
        if (g_ != null) {
            addedInnerElement(_innerName, owners_, _root, g_);
        }
        return owners_;
    }
    private static void addedInnerElement(String _innerName, OwnerListResultInfo _owners, String _s, RootBlock _sub) {
        for (RootBlock b: accessedInnerElements(_sub)) {
            String name_ = b.getName();
            if (StringUtil.quickEq(name_, _innerName)) {
                _owners.addInnElt(_s,_sub,name_,b);
            }
        }
    }
    public static StringList getSubTypes(StringList _classNames, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                if (!StringUtil.quickEq(i, j)) {
                    Mapping m_ = new Mapping();
                    m_.setArg(j);
                    m_.setParam(i);
                    m_.setMapping(_vars);
                    if (AnaInherits.isCorrectOrNumbers(m_, _page)) {
                        sub_ = false;
                        break;
                    }
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        return types_;
    }

    /** Only "object" classes are used as arguments */
    public static StringList getSubclasses(StringList _classNames, AnalyzedPageEl _page) {
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = StringExpUtil.getIdFromAllTypes(i);
                String baseSub_ = StringExpUtil.getIdFromAllTypes(j);
                if (!StringUtil.quickEq(baseSup_, baseSub_) && _page.getAnaGeneType(baseSub_).isSubTypeOf(baseSup_, _page)) {
                    sub_ = false;
                    break;
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        return types_;
    }
    public static IdTypeList<AnaGeneType> getSubclasses(IdTypeList<AnaGeneType> _classNames, AnalyzedPageEl _page) {
        IdTypeList<AnaGeneType> types_ = new IdTypeList<AnaGeneType>();
        for (AnaGeneType i: _classNames.ls()) {
            boolean sub_ = true;
            for (AnaGeneType j: _classNames.ls()) {
                if (isSubTypeOfDiff(j,i,_page)) {
                    sub_ = false;
                    break;
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        return types_;
    }
    public static boolean isSubTypeOfDiff(AnaGeneType _sub, AnaGeneType _sup, AnalyzedPageEl _page) {
        if (_sub == _sup) {
            return false;
        }
        if (_sub instanceof RootBlock) {
            return ((RootBlock) _sub).isSubTypeOf(_sup);
        }
        return _sub.isSubTypeOf(_sup.getFullName(), _page);
    }
    public static IdList<RootBlock> getSubclassesCust(IdList<RootBlock> _classNames) {
        IdList<RootBlock> types_ = new IdList<RootBlock>();
        for (RootBlock i: _classNames) {
            boolean sub_ = true;
            for (RootBlock j: _classNames) {
                if (i != j && j.isSubTypeOf(i)) {
                    sub_ = false;
                    break;
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        return types_;
    }

    public static int cmpTypes(String _one, String _two, AnalyzedPageEl _page) {
        AnaGeneType one_ = _page.getAnaGeneType(_one);
        AnaGeneType two_ = _page.getAnaGeneType(_two);
        if (two_.isSubTypeOf(_one, _page)) {
            return SortConstants.SWAP_SORT;
        }
        if (one_.isSubTypeOf(_two, _page)) {
            return SortConstants.NO_SWAP_SORT;
        }
        return SortConstants.EQ_CMP;
    }

    public static boolean isFloatOrderClass(AnaClassArgumentMatching _class, AnaClassArgumentMatching _classTwo, AnalyzedPageEl _page) {
        return isFloatOrderClass(_class, _page) && isFloatOrderClass(_classTwo, _page);
    }

    private static boolean isFloatOrderClass(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        return getFloatOrderClass(_class, _page) > 0;
    }

    public static int getFloatOrderClass(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        AnaClassArgumentMatching class_ = toPrimitive(_class, _page.getPrimitiveTypes());
        if (class_.matchClass(_page.getAliasPrimDouble())) {
            return DOUBLE_CASTING;
        }
        if (class_.matchClass(_page.getAliasPrimFloat())) {
            return FLOAT_CASTING;
        }
        return 0;
    }

    public static int getIntOrderClass(String _class, AnalyzedPageEl _page) {
        return getIntOrderClass(new AnaClassArgumentMatching(_class), _page);
    }

    public static boolean isIntOrderClass(AnaClassArgumentMatching _class, AnaClassArgumentMatching _classTwo, AnalyzedPageEl _page) {
        return isIntOrderClass(_class, _page) && isIntOrderClass(_classTwo, _page);
    }

    public static boolean isIntOrderClass(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        return getIntOrderClass(_class, _page) > 0;
    }

    public static int getIntOrderClass(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        AnaClassArgumentMatching class_ = toPrimitive(_class, _page.getPrimitiveTypes());
        if (class_.matchClass(_page.getAliasPrimLong())) {
            return LONG_CASTING;
        }
        if (class_.matchClass(_page.getAliasPrimInteger())) {
            return INT_CASTING;
        }
        if (class_.matchClass(_page.getAliasPrimChar())) {
            return CHAR_CASTING;
        }
        if (class_.matchClass(_page.getAliasPrimShort())) {
            return SHORT_CASTING;
        }
        if (class_.matchClass(_page.getAliasPrimByte())) {
            return BYTE_CASTING;
        }
        return 0;
    }

    public static String toPrimitive(String _class, StringMap<PrimitiveType> _primitiveTypes) {
        for (EntryCust<String, PrimitiveType> e: _primitiveTypes.entryList()) {
            if (StringUtil.quickEq(e.getValue().getWrapper(), _class)) {
                return e.getKey();
            }
        }
        return _class;
    }

    public static boolean isPrimitiveOrWrapper(String _className, AnalyzedPageEl _page) {
        return isPrimitiveOrWrapper(_className, _page.getPrimitiveTypes());
    }

    public static boolean isPrimitiveOrWrapper(String _className, StringMap<PrimitiveType> _primitiveTypes) {
        if (_primitiveTypes.contains(_className)) {
            return true;
        }
        return isWrapper(_className, _primitiveTypes);
    }

    public static boolean isPrimitiveOrWrapper(AnaClassArgumentMatching _className, AnalyzedPageEl _page) {
        for (String c: _className.getNames()) {
            if (isPrimitiveOrWrapper(c, _page)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWrapper(String _className, StringMap<PrimitiveType> _primitiveTypes) {
        for (EntryCust<String, PrimitiveType> e: _primitiveTypes.entryList()) {
            String wrap_ = e.getValue().getWrapper();
            if (StringUtil.quickEq(wrap_, _className)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPrimitive(String _className, AnalyzedPageEl _context) {
        return _context.getPrimitiveTypes().contains(_className);
    }

    public static boolean isPureNumberClass(AnaClassArgumentMatching _class, AnalyzedPageEl _context) {
        AnaClassArgumentMatching out_ = toPrimitive(_class, _context.getPrimitiveTypes());
        if (out_.matchClass(_context.getAliasPrimDouble())) {
            return true;
        }
        if (out_.matchClass(_context.getAliasPrimFloat())) {
            return true;
        }
        if (out_.matchClass(_context.getAliasPrimLong())) {
            return true;
        }
        if (out_.matchClass(_context.getAliasPrimInteger())) {
            return true;
        }
        if (out_.matchClass(_context.getAliasPrimChar())) {
            return true;
        }
        if (out_.matchClass(_context.getAliasPrimShort())) {
            return true;
        }
        return out_.matchClass(_context.getAliasPrimByte());
    }

    public static AnaClassArgumentMatching toPrimitive(AnaClassArgumentMatching _class, AnalyzedPageEl _context) {
        return toPrimitive(_class, _context.getPrimitiveTypes());
    }

    public static boolean isPrimitive(AnaClassArgumentMatching _clMatchLeft,
                                      AnalyzedPageEl _conf) {
        return isPrimitive(_clMatchLeft, _conf.getPrimitiveTypes());
    }

    public static AnaClassArgumentMatching toPrimitive(AnaClassArgumentMatching _class, StringMap<PrimitiveType> _primitiveTypes) {
        for (String w: _class.getNames()) {
            for (EntryCust<String, PrimitiveType> e: _primitiveTypes.entryList()) {
                if (StringUtil.quickEq(e.getValue().getWrapper(), w)) {
                    return new AnaClassArgumentMatching(e.getKey(),e.getValue().getCastNb());
                }
            }
        }
        return _class;
    }

    public static boolean isPrimitive(AnaClassArgumentMatching _clMatchLeft, StringMap<PrimitiveType> _primitiveTypes) {
        for (String n: _clMatchLeft.getNames()) {
            if (_primitiveTypes.contains(n)) {
                return true;
            }
        }
        return false;
    }

    public static AnaClassArgumentMatching getQuickComponentType(AnaClassArgumentMatching _className) {
        StringList cl_ = new StringList();
        for (String c: _className.getNames()) {
            String res_ = StringExpUtil.getQuickComponentType(c);
            if (res_ == null) {
                continue;
            }
            cl_.add(res_);
        }
        return new AnaClassArgumentMatching(cl_);
    }
    public static int getIndex(InfoBlock _i, String _field) {
        int ind_ = StringUtil.indexOf(_i.getFieldName(), _field.trim());
        if (ind_ < 0) {
            return -1;
        }
        int v_ = -1;
        if (_i instanceof FieldBlock) {
            v_ = ((FieldBlock)_i).getValuesOffset().get(ind_);
        }
        if (_i instanceof InnerTypeOrElement) {
            v_ = ((InnerTypeOrElement)_i).getFieldNameOffset();
        }
        return v_;
    }

    private static CustList<RootBlock> accessedClassMembers(RootBlock _clOwner) {
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        for (RootBlock b: getDirectMemberTypes(_clOwner)) {
            if (b instanceof InnerElementBlock) {
                continue;
            }
            inners_.add(b);
        }
        return inners_;
    }

    private static CustList<RootBlock> accessedInnerElements(RootBlock _clOwner) {
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        for (RootBlock b: getDirectMemberTypes(_clOwner)) {
            if (!(b instanceof InnerElementBlock)) {
                continue;
            }
            inners_.add(b);
        }
        return inners_;
    }

    public static CustList<RootBlock> getDirectMemberTypes(AbsBk _element) {
        CustList<RootBlock> list_ = new CustList<RootBlock>();
        if (_element == null) {
            return list_;
        }
        AbsBk elt_ = _element.getFirstChild();
        while (elt_ != null) {
            if (elt_ instanceof RootBlock && ((RootBlock)elt_).isReference()) {
                list_.add((RootBlock) elt_);
            }
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

}
