package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.types.OverridingMethod;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AnaTypeUtil {
    private AnaTypeUtil() {
    }

    public static void buildOverrides(RootBlock _type, ContextEl _context) {
        ExecRootBlock val_ = _context.getAnalyzing().getMapTypes().getVal(_type);
        String fileName_ = _type.getFile().getFileName();
        for (ClassMethodId c: getAllDuplicates(_type, _context)) {
            FoundErrorInterpret err_;
            err_ = new FoundErrorInterpret();
            err_.setFileName(fileName_);
            ExecNamedFunctionBlock sub_ = ExecBlock.getMethodBodiesById(_context,c.getClassName(),c.getConstraints()).first();
            err_.setIndexFile(sub_.getReturnTypeOffset());
            //type id len
            err_.buildError(_context.getAnalysisMessages().getDuplicatedOverriding(),
                    _type.getFullName(),
                    StringList.concat(c.getClassName(),".",c.getConstraints().getSignature(_context)));
            _context.addError(err_);
            _type.addNameErrors(err_);
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: _type.getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (OverridingMethodDto e: getAllInstanceSignatures(_type, _context)) {
            FormattedMethodId key_ = e.getFormattedMethodId();
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
                    if (ContextUtil.canAccess(_type.getFullName(), subAcc_, _context)) {
                        relations_.add(l);
                    }
                } else if (ContextUtil.canAccess(subId_.getGeneString(), supAcc_, _context)) {
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
                    String formattedRetDer_ = Templates.quickFormat(subId_.getGeneString(), retDerive_, _context);
                    String formattedRetBase_ = Templates.quickFormat(supId_.getGeneString(), retBase_, _context);
                    if (supId_.getBlock().isFinalMethod()) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(supId_.getBlock().getNameOffset());
                        //sub method name len
                        err_.buildError(_context.getAnalysisMessages().getDuplicatedFinal(),
                                supId_.getBlock().getId().getSignature(_context),
                                supId_.getGeneString());
                        subId_.getBlock().addNameErrors(err_);
                        _context.addError(err_);
                        continue;
                    }
                    if (supId_.getBlock().getAccess().isStrictMoreAccessibleThan(subId_.getBlock().getAccess())) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(supId_.getBlock().getAccessOffset());
                        //key word access or method name
                        err_.buildError(_context.getAnalysisMessages().getMethodsAccesses(),
                                supId_.getGeneString(),
                                supId_.getBlock().getId().getSignature(_context),
                                subId_.getGeneString(),
                                subId_.getBlock().getId().getSignature(_context));
                        subId_.getBlock().addNameErrors(err_);
                        _context.addError(err_);
                        continue;
                    }
                    if (supId_.getBlock().getKind() != MethodKind.STD_METHOD) {
                        if (!StringList.quickEq(formattedRetBase_, formattedRetDer_)) {
                            FoundErrorInterpret err_;
                            err_ = new FoundErrorInterpret();
                            err_.setFileName(fileName_);
                            err_.setIndexFile(supId_.getBlock().getReturnTypeOffset());
                            //sub return type len
                            err_.buildError(_context.getAnalysisMessages().getBadReturnTypeIndexer(),
                                    formattedRetBase_,
                                    supId_.getBlock().getId().getSignature(_context),
                                    supId_.getGeneString(),
                                    formattedRetDer_,
                                    subId_.getBlock().getId().getSignature(_context),
                                    subId_.getGeneString());
                            subId_.getBlock().addNameErrors(err_);
                            _context.addError(err_);
                            continue;
                        }
                        addClass(_type.getAllOverridingMethods(), key_, subId_);
                        addClass(_type.getAllOverridingMethods(), key_, supId_);
                        continue;
                    }
                    if (!AnaTemplates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _context)) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(supId_.getBlock().getReturnTypeOffset());
                        //sub return type len
                        err_.buildError(_context.getAnalysisMessages().getBadReturnTypeInherit(),
                                formattedRetDer_,
                                subId_.getBlock().getId().getSignature(_context),
                                subId_.getGeneString(),
                                formattedRetBase_,
                                supId_.getBlock().getId().getSignature(_context),
                                supId_.getGeneString());
                        subId_.getBlock().addNameErrors(err_);
                        _context.addError(err_);
                        continue;
                    }
                    addClass(_type.getAllOverridingMethods(), key_, subId_);
                    addClass(_type.getAllOverridingMethods(), key_, supId_);
                }
            }
        }
        for (OverridingMethodDto o: _type.getAllOverridingMethods()) {
            OverridingMethod elt_ = new OverridingMethod(o.getFormattedMethodId());
            for (GeneStringOverridable g:o.getMethodIds()) {
                elt_.getMethodIds().add(new ClassMethodId(g.getGeneString(),g.getBlock().getId()));
            }
            val_.getAllOverridingMethods().add(elt_);
        }
    }

    private static CustList<ClassMethodId> getAllDuplicates(RootBlock _type, ContextEl _classes) {
        CustList<ClassMethodId> list_;
        list_ = new CustList<ClassMethodId>();
        for (String s: _type.getAllGenericSuperTypes()) {
            CustList<MethodId> all_;
            all_ = new CustList<MethodId>();
            String base_ = StringExpUtil.getIdFromAllTypes(s);
            RootBlock b_ = _classes.getAnalyzing().getAnaClassBody(base_);
            for (OverridableBlock b: ClassesUtil.getMethodExecBlocks(b_)) {
                if (b.hiddenInstance()) {
                    continue;
                }
                MethodId id_ = b.getId().quickFormat(s, _classes);
                ClassMethodId formatted_ = new ClassMethodId(s, b.getId());
                boolean found_ = false;
                for (MethodId m: all_) {
                    if (id_.eq(m)) {
                        found_ = true;
                        break;
                    }
                }
                if (found_) {
                    list_.add(formatted_);
                }
                all_.add(id_);
            }
        }
        return list_;
    }

    private static CustList<OverridingMethodDto> getAllInstanceSignatures(RootBlock _r, ContextEl _classes) {
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
        for (String s: _r.getAllGenericSuperTypes()) {
            String base_ = StringExpUtil.getIdFromAllTypes(s);
            RootBlock b_ = _classes.getAnalyzing().getAnaClassBody(base_);
            for (OverridableBlock b: ClassesUtil.getMethodExecBlocks(b_)) {
                if (b.hiddenInstance()) {
                    continue;
                }
                addDtoClass(map_, b.getId().quickOverrideFormat(s, _classes),b_, b,s);
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

    public static void checkInterfaces(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (RootBlock c: page_.getFoundTypes()) {
            ExecRootBlock type_ = _context.getAnalyzing().getMapTypes().getVal(c);
            page_.setImporting(type_);
            page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
            page_.setImportingTypes(type_);
            page_.setCurrentBlock(c);
            page_.setCurrentAnaBlock(c);
            page_.setGlobalClass(type_.getGenericString());
            String d_ = c.getFile().getFileName();
            StringList ints_ = c.getStaticInitInterfaces();
            int len_ = ints_.size();
            if (len_ > 0 && c instanceof InterfaceBlock) {
                FoundErrorInterpret enum_;
                enum_ = new FoundErrorInterpret();
                enum_.setFileName(d_);
                enum_.setIndexFile(_context.getCurrentLocationIndex());
                //original id len
                enum_.buildError(_context.getAnalysisMessages().getCallIntNoNeed(),
                        c.getFullName());
                _context.addError(enum_);
                c.addNameErrors(enum_);
            }
            for (int i = 0; i < len_; i++) {
                int offset_ = c.getStaticInitInterfacesOffset().get(i);
                String base_ = StringExpUtil.removeDottedSpaces(ints_.get(i));
                page_.setCurrentBlock(c);
                page_.setCurrentAnaBlock(c);
                page_.setGlobalOffset(offset_);
                page_.setOffset(0);
                base_ = ResolvingImportTypes.resolveAccessibleIdType(_context,0,base_);
                c.getPartsStaticInitInterfacesOffset().addAllElts(page_.getCurrentParts());
                RootBlock r_ = page_.getAnaClassBody(base_);
                if (!(r_ instanceof InterfaceBlock)) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(d_);
                    enum_.setIndexFile(_context.getCurrentLocationIndex());
                    //interface len
                    enum_.buildError(_context.getAnalysisMessages().getCallIntOnly(),
                            base_);
                    _context.addError(enum_);
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
                page_.setCurrentBlock(c);
                page_.setCurrentAnaBlock(c);
                page_.setGlobalClass(type_.getGenericString());
                page_.setGlobalOffset(offsetSup_);
                page_.setOffset(0);
                sup_ = ResolvingImportTypes.resolveAccessibleIdType(_context,0,sup_);
                RootBlock rs_ = page_.getAnaClassBody(sup_);
                if (rs_ == null) {
                    continue;
                }
                RootBlock rsSup_ = rs_;
                for (int j = i + 1; j < len_; j++) {
                    String sub_ = StringExpUtil.removeDottedSpaces(ints_.get(j));
                    int offsetSub_ = c.getStaticInitInterfacesOffset().get(j);
                    page_.setCurrentBlock(c);
                    page_.setCurrentAnaBlock(c);
                    page_.setGlobalClass(type_.getGenericString());
                    page_.setGlobalOffset(offsetSub_);
                    page_.setOffset(0);
                    sub_ = ResolvingImportTypes.resolveAccessibleIdType(_context,0,sub_);
                    rs_ = page_.getAnaClassBody(sub_);
                    if (rs_ == null) {
                        continue;
                    }
                    if (rsSup_.isSubTypeOf(sub_,_context)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(d_);
                        int offset_ = c.getStaticInitInterfacesOffset().get(j);
                        undef_.setIndexFile(offset_);
                        //interface j len
                        undef_.buildError(_context.getAnalysisMessages().getCallIntInherits(),
                                sup_,
                                sub_);
                        _context.addError(undef_);
                        c.addNameErrors(undef_);
                    }
                }
            }
        }
        for (RootBlock c: page_.getFoundTypes()) {
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
            StringList.removeAllElements(allCopy_, _context.getStandards().getPredefinedInterfacesInitOrder());
            String clName_ = un_.getImportedDirectGenericSuperClass();
            String id_ = StringExpUtil.getIdFromAllTypes(clName_);
            RootBlock superType_ = page_.getAnaClassBody(id_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringList.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            StringList filteredStatic_ = new StringList();
            for (String i: allCopy_) {
                RootBlock int_ = page_.getAnaClassBody(i);
                if (!(int_ instanceof InterfaceBlock)) {
                    continue;
                }
                for (Block b: ClassesUtil.getDirectChildren(int_)) {
                    if (b instanceof NamedFunctionBlock) {
                        continue;
                    }
                    if (b instanceof GeneField) {
                        GeneField a_ = (GeneField) b;
                        if (!a_.isStaticField()) {
                            continue;
                        }
                        boolean allCst_ = true;
                        for (String n: a_.getFieldName()) {
                            if (_context.getClasses().getStaticField(new ClassField(i, n)) == null) {
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
                        undef_.buildError(_context.getAnalysisMessages().getCallIntNeedType(),
                                s);
                        _context.addError(undef_);
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
                        undef_.buildError(_context.getAnalysisMessages().getCallIntNoNeedType(),
                                s);
                        _context.addError(undef_);
                        c.addNameErrors(undef_);
                    }
                }
            }
        }
    }

    public static StringList getInners(String _root, String _innerName, ContextEl _an) {
        StringList inners_ = new StringList();
        for (String o: getOwners(_root, _innerName, _an)) {
            inners_.add(StringList.concat(o,"..",_innerName));
        }
        return inners_;
    }
    public static StringList getOwners(String _root, String _innerName, ContextEl _an) {
        return getOwners(_root,_innerName,false,_an);
    }
    public static StringList getInners(String _root, String _sep, String _innerName, boolean _staticOnly, ContextEl _an) {
        StringList inners_ = new StringList();
        if (StringList.quickEq(_sep,".")) {
            for (String o: getOwners(_root, _innerName,_staticOnly, _an)) {
                inners_.add(StringList.concat(o,"..",_innerName));
            }
        } else {
            for (String o: getEnumOwners(_root, _innerName, _an)) {
                inners_.add(StringList.concat(o,"-",_innerName));
            }
        }
        return inners_;
    }
    private static StringList getOwners(String _root, String _innerName, boolean _staticOnly, ContextEl _an) {
        StringList ids_ = new StringList(_root);
        StringList owners_ = new StringList();
        StringList visited_ = new StringList();
        while (true) {
            StringList new_ = new StringList();
            for (String s: ids_) {
                GeneType g_ = _an.getClassBody(s);
                if (!(g_ instanceof ExecRootBlock)) {
                    continue;
                }
                ExecRootBlock sub_ = (ExecRootBlock)g_;
                added(_innerName, _staticOnly, owners_, s, sub_);
                for (String t: sub_.getImportedDirectBaseSuperTypes()) {
                    addIfNotFound(visited_, new_, t);
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            ids_ = new_;
        }
        return PrimitiveTypeUtil.getSubclasses(owners_,_an);
    }
    public static StringList getGenericOwners(String _root, String _innerName, ContextEl _an) {
        StringList ids_ = new StringList(_root);
        StringList owners_ = new StringList();
        StringList visited_ = new StringList();
        while (true) {
            StringList new_ = new StringList();
            for (String s: ids_) {
                String id_ = StringExpUtil.getIdFromAllTypes(s);
                GeneType g_ = _an.getClassBody(id_);
                if (!(g_ instanceof ExecRootBlock)) {
                    continue;
                }
                ExecRootBlock sub_ = (ExecRootBlock)g_;
                added(_innerName, false, owners_, s, sub_);
                if (!Templates.correctNbParameters(s,_an)) {
                    for (String t: sub_.getImportedDirectSuperTypes()) {
                        String format_ = StringExpUtil.getIdFromAllTypes(t);
                        GeneType sup_ = _an.getClassBody(format_);
                        if (!sup_.isStaticType()) {
                            continue;
                        }
                        addIfNotFound(visited_, new_, format_);
                    }
                    continue;
                }
                for (String t: sub_.getImportedDirectSuperTypes()) {
                    String format_ = Templates.quickFormat(s, t, _an);
                    addIfNotFound(visited_, new_, format_);
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            ids_ = new_;
        }
        return PrimitiveTypeUtil.getSubclasses(owners_,_an);
    }

    private static void addIfNotFound(StringList _visited, StringList _new, String _format) {
        if (StringList.contains(_visited, _format)) {
            return;
        }
        _visited.add(_format);
        _new.add(_format);
    }

    private static void added(String _innerName, boolean _staticOnly, StringList owners_, String s, ExecRootBlock sub_) {
        for (ExecRootBlock b: ClassesUtil.accessedClassMembers(sub_)) {
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
    public static StringList getEnumOwners(String _root, String _innerName, ContextEl _an) {
        StringList owners_ = new StringList();
        String id_ = StringExpUtil.getIdFromAllTypes(_root);
        GeneType g_ = _an.getClassBody(id_);
        if (g_ instanceof ExecRootBlock) {
            ExecRootBlock sub_ = (ExecRootBlock)g_;
            addedInnerElement(_innerName, owners_, _root, sub_);
        }
        return owners_;
    }
    private static void addedInnerElement(String _innerName, StringList owners_, String s, ExecRootBlock sub_) {
        for (ExecRootBlock b: ClassesUtil.accessedInnerElements(sub_)) {
            String name_ = b.getName();
            if (StringList.quickEq(name_, _innerName)) {
                owners_.add(s);
            }
        }
    }
}
