package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.analyze.util.TypeVar;
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
        String fileName_ = _type.getFile().getFileName();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: _type.getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (OverridingMethodDto e: getAllInstanceSignatures(_type)) {
            FormattedMethodId key_ = e.getFormattedMethodId();
            if (!StringUtil.quickEq(key_.getName(),"[]")
                    &&!StringUtil.quickEq(key_.getName(),"[]=")) {
                for (AbsBk b: ClassesUtil.getDirectChildren(_type)) {
                    if (b instanceof InternOverrideBlock) {
                        for (OverridingMethodDto o: ((InternOverrideBlock) b).getOverrides()) {
                            if (o.getFormattedMethodId().eq(key_)) {
                                e.getMethodIds().clear();
                                e.getMethodIds().addAllElts(o.getMethodIds());
                            }
                        }
                    }
                }
            }
            CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
            CustList<GeneStringOverridable> allMethods_ = e.getMethodIds();
            for (GeneStringOverridable c: allMethods_) {
                IdList<AnaGeneType> allSuperTypes_ = c.getType().getAllSuperTypesInfo();
                for (GeneStringOverridable s: allMethods_) {
                    if (c.getType() != s.getType()) {
                        if (!allSuperTypes_.containsObj(s.getType())) {
                            continue;
                        }
                    }
                    OverridingRelation ovRel_ = new OverridingRelation();
                    ovRel_.setSubMethod(c);
                    ovRel_.setSub(c.getType());
                    ovRel_.setSupMethod(s);
                    ovRel_.setSup(s.getType());
                    pairs_.add(ovRel_);
                }
            }
            CustList<OverridingRelation> relations_ = new CustList<OverridingRelation>();
            for (OverridingRelation l: pairs_) {
                GeneStringOverridable subId_ = l.getSubMethod();
                GeneStringOverridable supId_ = l.getSupMethod();
                Accessed subAcc_ = new Accessed(subId_.getBlock().getAccess(), l.getSub().getPackageName(), l.getSub());
                Accessed supAcc_ = new Accessed(supId_.getBlock().getAccess(), l.getSup().getPackageName(), l.getSup());
                ClassMethodId cSup_ = new ClassMethodId(supId_.getGeneString(),supId_.getBlock().getId());
                ClassMethodId cSub_ = new ClassMethodId(subId_.getGeneString(),subId_.getBlock().getId());
                if (cSup_.eq(cSub_)) {
                    if (ContextUtil.canAccess(_type.getFullName(), subAcc_, _page)) {
                        relations_.add(l);
                    }
                } else if (ContextUtil.canAccess(subId_.getGeneString(), supAcc_, _page)) {
                    relations_.add(l);
                }
            }
            for (OverridingRelation l: relations_) {
                GeneStringOverridable subId_ = l.getSubMethod();
                GeneStringOverridable supId_ = l.getSupMethod();
                ClassMethodId cSup_ = new ClassMethodId(supId_.getGeneString(),supId_.getBlock().getId());
                ClassMethodId cSub_ = new ClassMethodId(subId_.getGeneString(),subId_.getBlock().getId());
                if (cSup_.eq(cSub_)) {
                    addClass(_type.getAllOverridingMethods(), key_, subId_);
                } else {
                    String retBase_ = supId_.getBlock().getImportedReturnType();
                    String retDerive_ = subId_.getBlock().getImportedReturnType();
                    String formattedRetDer_ = AnaInherits.quickFormat(subId_.getFormat(), retDerive_);
                    String formattedRetBase_ = AnaInherits.quickFormat(supId_.getFormat(), retBase_);
                    if (supId_.getBlock().isFinalMethod()) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(supId_.getBlock().getNameOffset());
                        //sub method name len
                        err_.buildError(_page.getAnalysisMessages().getDuplicatedFinal(),
                                supId_.getBlock().getId().getSignature(_page),
                                supId_.getGeneString());
                        subId_.getBlock().addNameErrors(err_);
                        _page.addLocError(err_);
                        continue;
                    }
                    if (supId_.getBlock().getAccess().isStrictMoreAccessibleThan(subId_.getBlock().getAccess())) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(supId_.getBlock().getAccessOffset());
                        //key word access or method name
                        err_.buildError(_page.getAnalysisMessages().getMethodsAccesses(),
                                supId_.getGeneString(),
                                supId_.getBlock().getId().getSignature(_page),
                                subId_.getGeneString(),
                                subId_.getBlock().getId().getSignature(_page));
                        subId_.getBlock().addNameErrors(err_);
                        _page.addLocError(err_);
                        continue;
                    }
                    if (supId_.getBlock().mustHaveSameRet()) {
                        if (!StringUtil.quickEq(formattedRetBase_, formattedRetDer_)) {
                            FoundErrorInterpret err_;
                            err_ = new FoundErrorInterpret();
                            err_.setFileName(fileName_);
                            err_.setIndexFile(supId_.getBlock().getReturnTypeOffset());
                            //sub return type len
                            err_.buildError(_page.getAnalysisMessages().getBadReturnTypeIndexer(),
                                    formattedRetBase_,
                                    supId_.getBlock().getId().getSignature(_page),
                                    supId_.getGeneString(),
                                    formattedRetDer_,
                                    subId_.getBlock().getId().getSignature(_page),
                                    subId_.getGeneString());
                            subId_.getBlock().addNameErrors(err_);
                            _page.addLocError(err_);
                            continue;
                        }
                        addClass(_type.getAllOverridingMethods(), key_, subId_);
                        addClass(_type.getAllOverridingMethods(), key_, supId_);
                        continue;
                    }
                    if (!AnaInherits.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _page)) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(supId_.getBlock().getReturnTypeOffset());
                        //sub return type len
                        err_.buildError(_page.getAnalysisMessages().getBadReturnTypeInherit(),
                                formattedRetDer_,
                                subId_.getBlock().getId().getSignature(_page),
                                subId_.getGeneString(),
                                formattedRetBase_,
                                supId_.getBlock().getId().getSignature(_page),
                                supId_.getGeneString());
                        subId_.getBlock().addNameErrors(err_);
                        _page.addLocError(err_);
                        continue;
                    }
                    addClass(_type.getAllOverridingMethods(), key_, subId_);
                    addClass(_type.getAllOverridingMethods(), key_, supId_);
                }
            }
        }
    }

    private static void addClass(CustList<OverridingMethodDto> _map, FormattedMethodId _key, GeneStringOverridable _class) {
        boolean found_ = false;
        for (OverridingMethodDto o: _map) {
            if (o.getFormattedMethodId().eq(_key)) {
                o.getMethodIds().add(_class);
                found_ = true;
                break;
            }
        }
        if (!found_) {
            OverridingMethodDto o_ = new OverridingMethodDto(_key);
            o_.getMethodIds().add(_class);
            _map.add(o_);
        }
    }

    private static CustList<OverridingMethodDto> getAllInstanceSignatures(RootBlock _r) {
        CustList<OverridingMethodDto> map_;
        map_ = new CustList<OverridingMethodDto>();
        for (NamedCalledFunctionBlock b: _r.getOverridableBlocks()) {
            if (b.hiddenInstance()) {
                continue;
            }
            MethodId m_ = b.getId();
            OverridingMethodDto o_ = new OverridingMethodDto(MethodId.to(m_));
            o_.getMethodIds().add(new GeneStringOverridable(new AnaFormattedRootBlock(_r), b));
            map_.add(o_);
        }
        for (AnaFormattedRootBlock s: _r.getAllGenericSuperTypesInfo()) {
            RootBlock b_ = s.getRootBlock();
            for (NamedCalledFunctionBlock b: b_.getOverridableBlocks()) {
                if (b.hiddenInstance()) {
                    continue;
                }
                addDtoClass(map_, b.getId().quickOverrideFormat(s),s, b);
            }
        }
        return map_;
    }

    private static void addDtoClass(CustList<OverridingMethodDto> _map, FormattedMethodId _key, AnaFormattedRootBlock _format, NamedCalledFunctionBlock _ov) {
        boolean found_ = false;
        for (OverridingMethodDto o: _map) {
            if (o.getFormattedMethodId().eq(_key)) {
                o.getMethodIds().add(new GeneStringOverridable(_format, _ov));
                found_ = true;
                break;
            }
        }
        if (!found_) {
            OverridingMethodDto o_ = new OverridingMethodDto(_key);
            o_.getMethodIds().add(new GeneStringOverridable(_format, _ov));
            _map.add(o_);
        }
    }

    public static void checkInterfaces(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getFoundTypes()) {
//            ExecRootBlock type_ = _page.getMapTypes().getVal(c);
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.setCurrentBlock(c);
            _page.setGlobalClass(c.getGenericString());
            _page.setGlobalType(c);
            String d_ = c.getFile().getFileName();
            StringList ints_ = c.getStaticInitInterfaces();
            int len_ = ints_.size();
            if (len_ > 0 && (c instanceof InterfaceBlock || c instanceof RecordBlock)) {
                FoundErrorInterpret enum_;
                enum_ = new FoundErrorInterpret();
                enum_.setFileName(d_);
                enum_.setIndexFile(_page.getTraceIndex());
                //original id len
                enum_.buildError(_page.getAnalysisMessages().getCallIntNoNeed(),
                        c.getFullName());
                _page.addLocError(enum_);
                c.addNameErrors(enum_);
            }
            for (int i = 0; i < len_; i++) {
                int offset_ = c.getStaticInitInterfacesOffset().get(i);
                _page.setCurrentBlock(c);
                _page.setGlobalOffset(offset_);
                _page.zeroOffset();
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(c.getMappings());
                String base_ = ResolvingTypes.resolveAccessibleIdType(0, ints_.get(i), _page);
                c.getPartsStaticInitInterfacesOffset().addAllElts(_page.getCurrentParts());
                RootBlock r_ = _page.getAnaClassBody(base_);
                if (!(r_ instanceof InterfaceBlock)) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(d_);
                    enum_.setIndexFile(_page.getTraceIndex());
                    //interface len
                    enum_.buildError(_page.getAnalysisMessages().getCallIntOnly(),
                            base_);
                    _page.addLocError(enum_);
                    if (!base_.isEmpty()) {
                        c.addNameErrors(enum_);
                    }
                } else {
//                    type_.getStaticInitImportedInterfaces().add(base_);
                    c.getStaticInitImportedInterfaces().add(base_);
                }
            }
            for (int i = 0; i < len_; i++) {
                int offsetSup_ = c.getStaticInitInterfacesOffset().get(i);
                _page.setCurrentBlock(c);
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalOffset(offsetSup_);
                _page.zeroOffset();
                String sup_ = ResolvingTypes.resolveAccessibleIdType(0, ints_.get(i), _page);
                RootBlock rs_ = _page.getAnaClassBody(sup_);
                if (rs_ == null) {
                    continue;
                }
                RootBlock rsSup_ = rs_;
                for (int j = i + 1; j < len_; j++) {
                    int offsetSub_ = c.getStaticInitInterfacesOffset().get(j);
                    _page.setCurrentBlock(c);
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalOffset(offsetSub_);
                    _page.zeroOffset();
                    String sub_ = ResolvingTypes.resolveAccessibleIdType(0, ints_.get(j), _page);
                    rs_ = _page.getAnaClassBody(sub_);
                    if (rs_ == null) {
                        continue;
                    }
                    if (rsSup_.isSubTypeOf(rs_)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(d_);
                        int offset_ = c.getStaticInitInterfacesOffset().get(j);
                        undef_.setIndexFile(offset_);
                        //interface j len
                        undef_.buildError(_page.getAnalysisMessages().getCallIntInherits(),
                                sup_,
                                sub_);
                        _page.addLocError(undef_);
                        c.addNameErrors(undef_);
                    }
                }
            }
        }
        for (RootBlock c: _page.getFoundTypes()) {
            if (!(c instanceof UniqueRootedBlock)) {
                continue;
            }
            UniqueRootedBlock un_ = (UniqueRootedBlock)c;
            StringList ints_ = un_.getStaticInitImportedInterfaces();
            StringList trimmedInt_ = new StringList();
            for (String i: ints_) {
                trimmedInt_.add(i);
            }
            StringList all_ = c.getAllSuperTypes();
            StringList allCopy_ = new StringList(all_);
            StringUtil.removeAllElements(allCopy_, _page.getPredefinedInterfacesInitOrder());
            String clName_ = un_.getImportedDirectGenericSuperClass();
            String id_ = StringExpUtil.getIdFromAllTypes(clName_);
            RootBlock superType_ = _page.getAnaClassBody(id_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringUtil.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            StringList filteredStatic_ = new StringList();
            for (String i: allCopy_) {
                RootBlock int_ = _page.getAnaClassBody(i);
                if (!(int_ instanceof InterfaceBlock)) {
                    continue;
                }
                for (AbsBk b: ClassesUtil.getDirectChildren(int_)) {
                    if (b instanceof NamedFunctionBlock) {
                        continue;
                    }
                    if (b instanceof InfoBlock) {
                        InfoBlock a_ = (InfoBlock) b;
                        if (!a_.isStaticField()) {
                            continue;
                        }
                        boolean allCst_ = true;
                        for (String n: a_.getFieldName()) {
                            StringMap<StringMap<Struct>> staticFields_ = _page.getStaticFields();
                            if (NumParsers.getStaticField(new ClassField(i, n), staticFields_) == null) {
                                allCst_ = false;
                                break;
                            }
                        }
                        if (!allCst_) {
                            filteredStatic_.add(i);
                        }
                    }
                    if (b instanceof StaticBlock) {
                        filteredStatic_.add(i);
                    }
                }
            }
            if (!StringUtil.equalsSet(filteredStatic_, trimmedInt_)) {
                for (String s: filteredStatic_) {
                    if (!StringUtil.contains(trimmedInt_,s)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(un_.getFile().getFileName());
                        undef_.setIndexFile(c.getIdRowCol());
                        //last parenthese
                        undef_.buildError(_page.getAnalysisMessages().getCallIntNeedType(),
                                s);
                        _page.addLocError(undef_);
                        c.addNameErrors(undef_);
                    }
                }
                for (String s: trimmedInt_) {
                    if (!StringUtil.contains(filteredStatic_,s)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(un_.getFile().getFileName());
                        undef_.setIndexFile(c.getIdRowCol());
                        //type len
                        undef_.buildError(_page.getAnalysisMessages().getCallIntNoNeedType(),
                                s);
                        _page.addLocError(undef_);
                        c.addNameErrors(undef_);
                    }
                }
            }
        }
    }

    public static StringList getInners(String _root, String _innerName, AnalyzedPageEl _page) {
        StringList inners_ = new StringList();
        for (String o: getOwners(_root, _innerName, _page)) {
            inners_.add(StringUtil.concat(o,"..",_innerName));
        }
        return inners_;
    }
    public static StringList getOwners(String _root, String _innerName, AnalyzedPageEl _page) {
        return getOwners(_root,_innerName,false, _page);
    }
    public static StringList getInners(String _root, String _sep, String _innerName, boolean _staticOnly, AnalyzedPageEl _page) {
        StringList inners_ = new StringList();
        if (StringUtil.quickEq(_sep,".")) {
            for (String o: getOwners(_root, _innerName,_staticOnly, _page)) {
                inners_.add(StringUtil.concat(o,"..",_innerName));
            }
        } else {
            for (String o: getEnumOwners(_root, _innerName, _page)) {
                inners_.add(StringUtil.concat(o,"-",_innerName));
            }
        }
        return inners_;
    }
    private static StringList getOwners(String _root, String _innerName, boolean _staticOnly, AnalyzedPageEl _page) {
        StringList ids_ = new StringList(_root);
        StringList owners_ = new StringList();
        StringList visited_ = new StringList();
        while (true) {
            StringList new_ = new StringList();
            for (String s: ids_) {
                RootBlock g_ = _page.getAnaClassBody(s);
                if (g_ == null) {
                    continue;
                }
                added(_innerName, _staticOnly, owners_, s, g_);
                for (String t: g_.getImportedDirectBaseSuperTypes().values()) {
                    addIfNotFound(visited_, new_, t);
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            ids_ = new_;
        }
        return getSubclasses(owners_, _page);
    }
    public static StringList getGenericOwners(String _root, String _innerName, AnalyzedPageEl _page) {
        StringList ids_ = new StringList(_root);
        StringList owners_ = new StringList();
        StringList visited_ = new StringList();
        while (true) {
            StringList new_ = new StringList();
            for (String s: ids_) {
                String id_ = StringExpUtil.getIdFromAllTypes(s);
                RootBlock g_ = _page.getAnaClassBody(id_);
                if (g_ == null) {
                    continue;
                }
                added(_innerName, false, owners_, s, g_);
                if (!AnaInherits.correctNbParameters(g_,s, _page)) {
                    for (AnaFormattedRootBlock t: g_.getImportedDirectSuperTypesInfo()) {
                        String format_ = StringExpUtil.getIdFromAllTypes(t.getFormatted());
                        addIfNotFound(visited_, new_, format_);
                    }
                    continue;
                }
                for (AnaFormattedRootBlock t: g_.getImportedDirectSuperTypesInfo()) {
                    String format_ = AnaInherits.quickFormat(g_,s, t.getFormatted());
                    addIfNotFound(visited_, new_, format_);
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            ids_ = new_;
        }
        return getSubclasses(owners_, _page);
    }

    private static void addIfNotFound(StringList _visited, StringList _new, String _format) {
        if (StringUtil.contains(_visited, _format)) {
            return;
        }
        _visited.add(_format);
        _new.add(_format);
    }

    private static void added(String _innerName, boolean _staticOnly, StringList _owners, String _s, RootBlock _sub) {
        for (RootBlock b: ClassesUtil.accessedClassMembers(_sub)) {
            if (_staticOnly) {
                if (!b.withoutInstance()) {
                    continue;
                }
            }
            String name_ = b.getName();
            if (StringUtil.quickEq(name_, _innerName)) {
                _owners.add(_s);
            }
        }
    }
    public static StringList getEnumOwners(String _root, String _innerName, AnalyzedPageEl _page) {
        StringList owners_ = new StringList();
        String id_ = StringExpUtil.getIdFromAllTypes(_root);
        RootBlock g_ = _page.getAnaClassBody(id_);
        if (g_ != null) {
            addedInnerElement(_innerName, owners_, _root, g_);
        }
        return owners_;
    }
    private static void addedInnerElement(String _innerName, StringList _owners, String _s, RootBlock _sub) {
        for (RootBlock b: ClassesUtil.accessedInnerElements(_sub)) {
            String name_ = b.getName();
            if (StringUtil.quickEq(name_, _innerName)) {
                _owners.add(_s);
            }
        }
    }
    public static StringList getSubTypes(StringList _classNames, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                if (StringUtil.quickEq(i, j)) {
                    continue;
                }
                Mapping m_ = new Mapping();
                m_.setArg(j);
                m_.setParam(i);
                m_.setMapping(_vars);
                if (AnaInherits.isCorrectOrNumbers(m_,_page)) {
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

    /** Only "object" classes are used as arguments */
    public static StringList getSubclasses(StringList _classNames, AnalyzedPageEl _page) {
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = StringExpUtil.getIdFromAllTypes(i);
                String baseSub_ = StringExpUtil.getIdFromAllTypes(j);
                if (StringUtil.quickEq(baseSup_, baseSub_)) {
                    continue;
                }
                AnaGeneType subType_ = _page.getAnaGeneType(baseSub_);
                if (subType_.isSubTypeOf(baseSup_, _page)) {
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
            v_ = _i.getFieldNameOffset();
        }
        return v_;
    }
}
