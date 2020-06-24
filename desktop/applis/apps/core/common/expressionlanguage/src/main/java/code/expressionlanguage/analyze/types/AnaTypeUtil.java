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
        Classes classesRef_ = _context.getClasses();
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
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: _type.getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (OverridingMethod e: getAllInstanceSignatures(val_, _context)) {
            FormattedMethodId key_ = e.getFormattedMethodId();
            CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
            CustList<ClassMethodId> allMethods_ = e.getMethodIds();
            for (ClassMethodId c: allMethods_) {
                String templClass_ = c.getClassName();
                String typeName_ = StringExpUtil.getIdFromAllTypes(templClass_);
                ExecRootBlock sub_ = classesRef_.getClassBody(typeName_);
                StringList allSuperTypes_ = sub_.getAllSuperTypes();
                for (ClassMethodId s: allMethods_) {
                    String super_ = s.getClassName();
                    String isSuper_ = StringExpUtil.getIdFromAllTypes(super_);
                    if (!StringList.quickEq(typeName_,isSuper_)) {
                        if (!StringList.contains(allSuperTypes_,isSuper_)) {
                            continue;
                        }
                    }
                    OverridingRelation ovRel_ = new OverridingRelation();
                    ExecRootBlock sup_ = classesRef_.getClassBody(isSuper_);
                    ovRel_.setSubMethod(c);
                    ovRel_.setSub(sub_);
                    ovRel_.setSupMethod(s);
                    ovRel_.setSup(sup_);
                    pairs_.add(ovRel_);
                }
            }
            CustList<OverridingRelation> relations_ = new CustList<OverridingRelation>();
            for (OverridingRelation l: pairs_) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                ExecNamedFunctionBlock sub_ = ExecBlock.getMethodBodiesById(_context,subId_.getClassName(), subId_.getConstraints()).first();
                ExecNamedFunctionBlock sup_ = ExecBlock.getMethodBodiesById(_context,supId_.getClassName(), supId_.getConstraints()).first();
                Accessed subAcc_ = newAccessed(sub_,l.getSub());
                Accessed supAcc_ = newAccessed(sup_,l.getSup());
                if (subId_.eq(supId_)) {
                    if (ContextUtil.canAccess(_type.getFullName(), subAcc_, _context)) {
                        relations_.add(l);
                    }
                } else if (ContextUtil.canAccess(subId_.getClassName(), supAcc_, _context)) {
                    relations_.add(l);
                }
            }
            for (OverridingRelation l: relations_) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                ExecNamedFunctionBlock sub_ = ExecBlock.getMethodBodiesById(_context,subId_.getClassName(), subId_.getConstraints()).first();
                ExecNamedFunctionBlock sup_ = ExecBlock.getMethodBodiesById(_context,supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    addClass(_type.getAllOverridingMethods(), key_, subId_);
                } else {
                    String retBase_ = sup_.getImportedReturnType();
                    String retDerive_ = sub_.getImportedReturnType();
                    String formattedRetDer_ = Templates.quickFormat(subId_.getClassName(), retDerive_, _context);
                    String formattedRetBase_ = Templates.quickFormat(supId_.getClassName(), retBase_, _context);
                    if (((ExecOverridableBlock)sup_).isFinalMethod()) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(sub_.getNameOffset());
                        //sub method name len
                        err_.buildError(_context.getAnalysisMessages().getDuplicatedFinal(),
                                supId_.getConstraints().getSignature(_context),
                                supId_.getClassName());
                        _context.addError(err_);
                        continue;
                    }
                    if (sup_.getAccess().isStrictMoreAccessibleThan(sub_.getAccess())) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(sub_.getAccessOffset());
                        //key word access or method name
                        err_.buildError(_context.getAnalysisMessages().getMethodsAccesses(),
                                supId_.getClassName(),
                                supId_.getConstraints().getSignature(_context),
                                subId_.getClassName(),
                                subId_.getConstraints().getSignature(_context));
                        _context.addError(err_);
                        continue;
                    }
                    if (((ExecOverridableBlock)sub_).getKind() != MethodKind.STD_METHOD) {
                        if (!StringList.quickEq(formattedRetBase_, formattedRetDer_)) {
                            FoundErrorInterpret err_;
                            err_ = new FoundErrorInterpret();
                            err_.setFileName(fileName_);
                            err_.setIndexFile(sub_.getReturnTypeOffset());
                            //sub return type len
                            err_.buildError(_context.getAnalysisMessages().getBadReturnTypeIndexer(),
                                    formattedRetBase_,
                                    supId_.getConstraints().getSignature(_context),
                                    supId_.getClassName(),
                                    formattedRetDer_,
                                    subId_.getConstraints().getSignature(_context),
                                    subId_.getClassName());
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
                        err_.setIndexFile(sub_.getReturnTypeOffset());
                        //sub return type len
                        err_.buildError(_context.getAnalysisMessages().getBadReturnTypeInherit(),
                                formattedRetDer_,
                                subId_.getConstraints().getSignature(_context),
                                subId_.getClassName(),
                                formattedRetBase_,
                                supId_.getConstraints().getSignature(_context),
                                supId_.getClassName());
                        _context.addError(err_);
                        continue;
                    }
                    addClass(_type.getAllOverridingMethods(), key_, subId_);
                    addClass(_type.getAllOverridingMethods(), key_, supId_);
                }
            }
        }
        val_.getAllOverridingMethods().addAllElts(_type.getAllOverridingMethods());
    }

    private static Accessed newAccessed(ExecNamedFunctionBlock _named, ExecRootBlock _root) {
        if (_named instanceof ExecOverridableBlock) {
            ExecOverridableBlock c = (ExecOverridableBlock) _named;
            return new Accessed(c.getAccess(), _root.getPackageName(), _root.getFullName(), _root.getOuterFullName());
        }
        return new Accessed(AccessEnum.PUBLIC, "", "", "");
    }
    private static CustList<ClassMethodId> getAllDuplicates(RootBlock _type, ContextEl _classes) {
        CustList<ClassMethodId> list_;
        list_ = new CustList<ClassMethodId>();
        for (String s: _type.getAllGenericSuperTypes()) {
            CustList<MethodId> all_;
            all_ = new CustList<MethodId>();
            String base_ = StringExpUtil.getIdFromAllTypes(s);
            ExecRootBlock b_ = _classes.getClasses().getClassBody(base_);
            for (GeneCustModifierMethod b: ExecBlock.getMethodExecBlocks(b_)) {
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

    private static CustList<OverridingMethod> getAllInstanceSignatures(ExecRootBlock _type, ContextEl _classes) {
        CustList<OverridingMethod> map_;
        map_ = new CustList<OverridingMethod>();
        for (GeneCustModifierMethod b: ExecBlock.getMethodExecBlocks(_type)) {
            if (b.hiddenInstance()) {
                continue;
            }
            MethodId m_ = b.getId();
            OverridingMethod o_ = new OverridingMethod(MethodId.to(m_));
            o_.getMethodIds().add(new ClassMethodId(_type.getGenericString(), m_));
            map_.add(o_);
        }
        for (String s: _type.getAllGenericSuperTypes()) {
            String base_ = StringExpUtil.getIdFromAllTypes(s);
            ExecRootBlock b_ = _classes.getClasses().getClassBody(base_);
            for (GeneCustModifierMethod b: ExecBlock.getMethodExecBlocks(b_)) {
                if (b.hiddenInstance()) {
                    continue;
                }
                MethodId m_ = b.getId();
                addClass(map_, b.getId().quickOverrideFormat(s, _classes), new ClassMethodId(s, m_));
            }
        }
        return map_;
    }

    private static void addClass(CustList<OverridingMethod> _map, FormattedMethodId _key, ClassMethodId _class) {
        boolean found_ = false;
        for (OverridingMethod o: _map) {
            if (o.getFormattedMethodId().eq(_key)) {
                o.getMethodIds().add(_class);
                found_ = true;
                break;
            }
        }
        if (!found_) {
            OverridingMethod o_ = new OverridingMethod(_key);
            o_.getMethodIds().add(_class);
            _map.add(o_);
        }
    }

    public static void checkInterfaces(ContextEl _context) {
        Classes classes_ = _context.getClasses();
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
                ExecRootBlock r_ = classes_.getClassBody(base_);
                if (!(r_ instanceof ExecInterfaceBlock)) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(d_);
                    enum_.setIndexFile(_context.getCurrentLocationIndex());
                    //interface len
                    enum_.buildError(_context.getAnalysisMessages().getCallIntOnly(),
                            base_);
                    _context.addError(enum_);
                } else {
                    type_.getStaticInitImportedInterfaces().add(base_);
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
                ExecRootBlock rs_ = classes_.getClassBody(sup_);
                if (rs_ == null) {
                    continue;
                }
                ExecRootBlock rsSup_ = rs_;
                for (int j = i + 1; j < len_; j++) {
                    String sub_ = StringExpUtil.removeDottedSpaces(ints_.get(j));
                    int offsetSub_ = c.getStaticInitInterfacesOffset().get(j);
                    page_.setCurrentBlock(c);
                    page_.setCurrentAnaBlock(c);
                    page_.setGlobalClass(type_.getGenericString());
                    page_.setGlobalOffset(offsetSub_);
                    page_.setOffset(0);
                    sub_ = ResolvingImportTypes.resolveAccessibleIdType(_context,0,sub_);
                    rs_ = classes_.getClassBody(sub_);
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
                    }
                }
            }
        }
        for (RootBlock c: page_.getFoundTypes()) {
            ExecRootBlock exec_ = _context.getAnalyzing().getMapTypes().getVal(c);
            if (!(exec_ instanceof ExecUniqueRootedBlock)) {
                continue;
            }
            ExecUniqueRootedBlock un_ = (ExecUniqueRootedBlock)exec_;
            StringList ints_ = un_.getStaticInitImportedInterfaces();
            StringList trimmedInt_ = new StringList();
            for (String i: ints_) {
                trimmedInt_.add(i);
            }
            StringList all_ = exec_.getAllSuperTypes();
            StringList allCopy_ = new StringList(all_);
            StringList.removeAllElements(allCopy_, _context.getStandards().getPredefinedInterfacesInitOrder());
            String clName_ = un_.getImportedDirectGenericSuperClass();
            String id_ = StringExpUtil.getIdFromAllTypes(clName_);
            ExecRootBlock superType_ = classes_.getClassBody(id_);
            if (superType_ instanceof ExecUniqueRootedBlock) {
                StringList.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            StringList filteredStatic_ = new StringList();
            for (String i: allCopy_) {
                ExecRootBlock int_ = classes_.getClassBody(i);
                if (!(int_ instanceof ExecInterfaceBlock)) {
                    continue;
                }
                for (ExecBlock b: ExecBlock.getDirectChildren(int_)) {
                    if (b instanceof ExecNamedFunctionBlock) {
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
                    if (b instanceof ExecStaticBlock) {
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
