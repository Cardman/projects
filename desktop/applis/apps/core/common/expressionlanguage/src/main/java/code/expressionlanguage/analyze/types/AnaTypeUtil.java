package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

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
            if (!StringList.quickEq(key_.getName(),"[]")
                    &&!StringList.quickEq(key_.getName(),"[]=")) {
                for (Block b: ClassesUtil.getDirectChildren(_type)) {
                    if (b instanceof InternOverrideBlock) {
                        CustList<OverridingMethodDto> overrides_ = ((InternOverrideBlock) b).getOverrides();
                        for (OverridingMethodDto o: overrides_) {
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
                String templClass_ = c.getGeneString();
                String typeName_ = StringExpUtil.getIdFromAllTypes(templClass_);
                StringList allSuperTypes_ = c.getType().getAllSuperTypes();
                for (GeneStringOverridable s: allMethods_) {
                    String super_ = s.getGeneString();
                    String isSuper_ = StringExpUtil.getIdFromAllTypes(super_);
                    if (!StringList.quickEq(typeName_,isSuper_)) {
                        if (!StringList.contains(allSuperTypes_,isSuper_)) {
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
                Accessed subAcc_ = new Accessed(subId_.getBlock().getAccess(), l.getSub().getPackageName(), l.getSub().getFullName(), l.getSub().getOuterFullName());
                Accessed supAcc_ = new Accessed(supId_.getBlock().getAccess(), l.getSup().getPackageName(), l.getSup().getFullName(), l.getSup().getOuterFullName());
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
                    String formattedRetDer_ = AnaTemplates.quickFormat(subId_.getType(),subId_.getGeneString(), retDerive_);
                    String formattedRetBase_ = AnaTemplates.quickFormat(supId_.getType(),supId_.getGeneString(), retBase_);
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
                    if (supId_.getBlock().getKind() != MethodKind.STD_METHOD) {
                        if (!StringList.quickEq(formattedRetBase_, formattedRetDer_)) {
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
                    if (!AnaTemplates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _page)) {
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

    private static CustList<OverridingMethodDto> getAllInstanceSignatures(RootBlock _r) {
        CustList<OverridingMethodDto> map_;
        map_ = new CustList<OverridingMethodDto>();
        for (OverridableBlock b: ClassesUtil.getMethodExecBlocks(_r)) {
            if (b.hiddenInstance()) {
                continue;
            }
            MethodId m_ = b.getId();
            OverridingMethodDto o_ = new OverridingMethodDto(MethodId.to(m_));
            o_.getMethodIds().add(new GeneStringOverridable(_r.getGenericString(),_r,b));
            map_.add(o_);
        }
        for (AnaFormattedRootBlock s: _r.getAllGenericSuperTypesInfo()) {
            RootBlock b_ = s.getRootBlock();
            for (OverridableBlock b: ClassesUtil.getMethodExecBlocks(b_)) {
                if (b.hiddenInstance()) {
                    continue;
                }
                addDtoClass(map_, b.getId().quickOverrideFormat(b_,s.getFormatted()),b_, b,s.getFormatted());
            }
        }
        return map_;
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


    private static void addDtoClass(CustList<OverridingMethodDto> _map, FormattedMethodId _key, RootBlock _r,OverridableBlock _ov, String _str) {
        boolean found_ = false;
        for (OverridingMethodDto o: _map) {
            if (o.getFormattedMethodId().eq(_key)) {
                o.getMethodIds().add(new GeneStringOverridable(_str,_r,_ov));
                found_ = true;
                break;
            }
        }
        if (!found_) {
            OverridingMethodDto o_ = new OverridingMethodDto(_key);
            o_.getMethodIds().add(new GeneStringOverridable(_str,_r,_ov));
            _map.add(o_);
        }
    }

    public static void checkInterfaces(AnalyzedPageEl _page) {
        for (RootBlock c: _page.getFoundTypes()) {
            ExecRootBlock type_ = _page.getMapTypes().getVal(c);
            _page.setImporting(c);
            _page.setImportingAcces(new TypeAccessor(c.getFullName()));
            _page.setImportingTypes(c);
            _page.setCurrentBlock(c);
            _page.setCurrentAnaBlock(c);
            _page.setGlobalClass(c.getGenericString());
            _page.setGlobalType(c);
            _page.setGlobalDirType(null);
            String d_ = c.getFile().getFileName();
            StringList ints_ = c.getStaticInitInterfaces();
            int len_ = ints_.size();
            if (len_ > 0 && c instanceof InterfaceBlock) {
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
                String base_ = StringExpUtil.removeDottedSpaces(ints_.get(i));
                _page.setCurrentBlock(c);
                _page.setCurrentAnaBlock(c);
                _page.setGlobalOffset(offset_);
                _page.setOffset(0);
                _page.getMappingLocal().clear();
                _page.getMappingLocal().putAllMap(c.getMappings());
                base_ = ResolvingImportTypes.resolveAccessibleIdType(0,base_, _page);
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
                    type_.getStaticInitImportedInterfaces().add(base_);
                    c.getStaticInitImportedInterfaces().add(base_);
                }
            }
            for (int i = 0; i < len_; i++) {
                String sup_ = StringExpUtil.removeDottedSpaces(ints_.get(i));
                int offsetSup_ = c.getStaticInitInterfacesOffset().get(i);
                _page.setCurrentBlock(c);
                _page.setCurrentAnaBlock(c);
                _page.setGlobalClass(c.getGenericString());
                _page.setGlobalType(c);
                _page.setGlobalDirType(null);
                _page.setGlobalOffset(offsetSup_);
                _page.setOffset(0);
                sup_ = ResolvingImportTypes.resolveAccessibleIdType(0,sup_, _page);
                RootBlock rs_ = _page.getAnaClassBody(sup_);
                if (rs_ == null) {
                    continue;
                }
                RootBlock rsSup_ = rs_;
                for (int j = i + 1; j < len_; j++) {
                    String sub_ = StringExpUtil.removeDottedSpaces(ints_.get(j));
                    int offsetSub_ = c.getStaticInitInterfacesOffset().get(j);
                    _page.setCurrentBlock(c);
                    _page.setCurrentAnaBlock(c);
                    _page.setGlobalClass(c.getGenericString());
                    _page.setGlobalType(c);
                    _page.setGlobalDirType(null);
                    _page.setGlobalOffset(offsetSub_);
                    _page.setOffset(0);
                    sub_ = ResolvingImportTypes.resolveAccessibleIdType(0,sub_, _page);
                    rs_ = _page.getAnaClassBody(sub_);
                    if (rs_ == null) {
                        continue;
                    }
                    if (rsSup_.isSubTypeOf(sub_, _page)) {
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
            StringList.removeAllElements(allCopy_, _page.getStandards().getPredefinedInterfacesInitOrder());
            String clName_ = un_.getImportedDirectGenericSuperClass();
            String id_ = StringExpUtil.getIdFromAllTypes(clName_);
            RootBlock superType_ = _page.getAnaClassBody(id_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringList.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            StringList filteredStatic_ = new StringList();
            for (String i: allCopy_) {
                RootBlock int_ = _page.getAnaClassBody(i);
                if (!(int_ instanceof InterfaceBlock)) {
                    continue;
                }
                for (Block b: ClassesUtil.getDirectChildren(int_)) {
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
                            if (_page.getClasses().getStaticField(new ClassField(i, n)) == null) {
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
            if (!StringList.equalsSet(filteredStatic_, trimmedInt_)) {
                for (String s: filteredStatic_) {
                    if (!StringList.contains(trimmedInt_,s)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(un_.getFile().getFileName());
                        undef_.setIndexFile(0);
                        //last parenthese
                        undef_.buildError(_page.getAnalysisMessages().getCallIntNeedType(),
                                s);
                        _page.addLocError(undef_);
                        c.addNameErrors(undef_);
                    }
                }
                for (String s: trimmedInt_) {
                    if (!StringList.contains(filteredStatic_,s)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(un_.getFile().getFileName());
                        undef_.setIndexFile(0);
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
            inners_.add(StringList.concat(o,"..",_innerName));
        }
        return inners_;
    }
    public static StringList getOwners(String _root, String _innerName, AnalyzedPageEl _page) {
        return getOwners(_root,_innerName,false, _page);
    }
    public static StringList getInners(String _root, String _sep, String _innerName, boolean _staticOnly, AnalyzedPageEl _page) {
        StringList inners_ = new StringList();
        if (StringList.quickEq(_sep,".")) {
            for (String o: getOwners(_root, _innerName,_staticOnly, _page)) {
                inners_.add(StringList.concat(o,"..",_innerName));
            }
        } else {
            for (String o: getEnumOwners(_root, _innerName, _page)) {
                inners_.add(StringList.concat(o,"-",_innerName));
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
                if (!AnaTemplates.correctNbParameters(g_,s, _page)) {
                    for (AnaFormattedRootBlock t: g_.getImportedDirectSuperTypesInfo()) {
                        String format_ = StringExpUtil.getIdFromAllTypes(t.getFormatted());
                        addIfNotFound(visited_, new_, format_);
                    }
                    continue;
                }
                for (AnaFormattedRootBlock t: g_.getImportedDirectSuperTypesInfo()) {
                    String format_ = AnaTemplates.quickFormat(g_,s, t.getFormatted());
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
        if (StringList.contains(_visited, _format)) {
            return;
        }
        _visited.add(_format);
        _new.add(_format);
    }

    private static void added(String _innerName, boolean _staticOnly, StringList owners_, String s, RootBlock sub_) {
        for (RootBlock b: ClassesUtil.accessedClassMembers(sub_)) {
            if (_staticOnly) {
                if (!b.isStaticType()) {
                    continue;
                }
            }
            String name_ = b.getName();
            if (StringList.quickEq(name_, _innerName)) {
                owners_.add(s);
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
    private static void addedInnerElement(String _innerName, StringList owners_, String s, RootBlock sub_) {
        for (RootBlock b: ClassesUtil.accessedInnerElements(sub_)) {
            String name_ = b.getName();
            if (StringList.quickEq(name_, _innerName)) {
                owners_.add(s);
            }
        }
    }

    /** Only "object" classes are used as arguments */
    public static StringList getSubclasses(StringList _classNames, AnalyzedPageEl _page) {
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = StringExpUtil.getIdFromAllTypes(i);
                String baseSub_ = StringExpUtil.getIdFromAllTypes(j);
                if (StringList.quickEq(baseSup_, baseSub_)) {
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
        AnaInheritedType one_ = _page.getAnaGeneType(_one);
        AnaInheritedType two_ = _page.getAnaGeneType(_two);
        if (two_.isSubTypeOf(_one, _page)) {
            return CustList.SWAP_SORT;
        }
        if (one_.isSubTypeOf(_two, _page)) {
            return CustList.NO_SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }

    public static boolean isFloatOrderClass(AnaClassArgumentMatching _class, AnaClassArgumentMatching _classTwo, AnalyzedPageEl _page) {
        return isFloatOrderClass(_class, _page) && isFloatOrderClass(_classTwo, _page);
    }

    private static boolean isFloatOrderClass(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        return isFloatOrderClass(_class, _page.getStandards());
    }

    private static boolean isFloatOrderClass(AnaClassArgumentMatching _class, LgNames _context) {
        return getFloatOrderClass(_class,_context) > 0;
    }

    public static int getFloatOrderClass(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        return getFloatOrderClass(_class, _page.getStandards());
    }

    private static int getFloatOrderClass(AnaClassArgumentMatching _class, LgNames _stds) {
        AnaClassArgumentMatching class_ = toPrimitive(_class, _stds);
        if (class_.matchClass(_stds.getAliasPrimDouble())) {
            return DOUBLE_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimFloat())) {
            return FLOAT_CASTING;
        }
        return 0;
    }

    public static int getIntOrderClass(String _class, AnalyzedPageEl _page) {
        return getIntOrderClass(_class, _page.getStandards());
    }

    private static int getIntOrderClass(String _class, LgNames _stds) {
        return getIntOrderClass(new AnaClassArgumentMatching(_class), _stds);
    }

    public static boolean isIntOrderClass(AnaClassArgumentMatching _class, AnaClassArgumentMatching _classTwo, AnalyzedPageEl _page) {
        return isIntOrderClass(_class, _page) && isIntOrderClass(_classTwo, _page);
    }

    public static boolean isIntOrderClass(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        return getIntOrderClass(_class, _page) > 0;
    }

    public static int getIntOrderClass(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        return getIntOrderClass(_class, _page.getStandards());
    }

    private static int getIntOrderClass(AnaClassArgumentMatching _class, LgNames _stds) {
        AnaClassArgumentMatching class_ = toPrimitive(_class, _stds);
        if (class_.matchClass(_stds.getAliasPrimLong())) {
            return LONG_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimInteger())) {
            return INT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimChar())) {
            return CHAR_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimShort())) {
            return SHORT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimByte())) {
            return BYTE_CASTING;
        }
        return 0;
    }

    public static String toPrimitive(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(e.getValue().getWrapper(), _class)) {
                return e.getKey();
            }
        }
        return _class;
    }

    public static boolean isPrimitiveOrWrapper(String _className, AnalyzedPageEl _page) {
        return isPrimitiveOrWrapper(_className, _page.getStandards());
    }

    public static boolean isPrimitiveOrWrapper(String _className, LgNames _stds) {
        if (PrimitiveTypeUtil.isPrimitive(_className, _stds)) {
            return true;
        }
        return isWrapper(_className, _stds);
    }

    public static boolean isPrimitiveOrWrapper(AnaClassArgumentMatching _className, AnalyzedPageEl _page) {
        for (String c: _className.getNames()) {
            if (isPrimitiveOrWrapper(c, _page)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWrapper(String _className, AnalyzedPageEl _page) {
        return isWrapper(_className, _page.getStandards());
    }

    public static boolean isWrapper(String _className, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            String wrap_ = e.getValue().getWrapper();
            if (StringList.quickEq(wrap_, _className)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPrimitive(String _className, AnalyzedPageEl _context) {
        return PrimitiveTypeUtil.isPrimitive(_className, _context.getStandards());
    }

    public static boolean isPureNumberClass(AnaClassArgumentMatching _class, AnalyzedPageEl _context) {
        return isPureNumberClass(_class, _context.getStandards());
    }

    public static AnaClassArgumentMatching toPrimitive(AnaClassArgumentMatching _class, AnalyzedPageEl _context) {
        return toPrimitive(_class, _context.getStandards());
    }

    public static boolean isPrimitive(AnaClassArgumentMatching _clMatchLeft,
                                      AnalyzedPageEl _conf) {
        LgNames stds_ = _conf.getStandards();
        return isPrimitive(_clMatchLeft, stds_);
    }

    public static boolean isPureNumberClass(AnaClassArgumentMatching _class, LgNames _stds) {
        AnaClassArgumentMatching out_ = toPrimitive(_class, _stds);
        if (out_.matchClass(_stds.getAliasPrimDouble())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimFloat())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimLong())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimInteger())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimChar())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimShort())) {
            return true;
        }
        return out_.matchClass(_stds.getAliasPrimByte());
    }

    public static AnaClassArgumentMatching toPrimitive(AnaClassArgumentMatching _class, LgNames _stds) {
        for (String w: _class.getNames()) {
            for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
                if (StringList.quickEq(e.getValue().getWrapper(), w)) {
                    return new AnaClassArgumentMatching(e.getKey(),e.getValue().getCastNb());
                }
            }
        }
        return _class;
    }

    public static boolean isPrimitive(AnaClassArgumentMatching _clMatchLeft, LgNames stds_) {
        for (String n: _clMatchLeft.getNames()) {
            if (PrimitiveTypeUtil.isPrimitive(n, stds_)) {
                return true;
            }
        }
        return false;
    }
}
