package code.expressionlanguage.inherits;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.StandardType;
import code.util.*;

public final class TypeUtil {

    private TypeUtil() {
    }

    public static void buildInherits(ContextEl _context){
        for (EntryCust<String, StandardType> s: _context.getStandards().getStandards().entryList()) {
            buildInherits(s.getValue(), _context);
        }
    }

    public static void checkInterfaces(ContextEl _context) {
        Classes classes_ = _context.getClasses();
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            _context.getAnalyzing().setImporting(c);
            _context.getAnalyzing().setCurrentBlock(c);
            _context.getAnalyzing().setGlobalClass(c.getGenericString());
            String d_ = c.getFile().getFileName();
            StringList ints_ = c.getStaticInitInterfaces();
            int len_ = ints_.size();
            if (len_ > 0 && c instanceof GeneInterface) {
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
                String base_ = ContextEl.removeDottedSpaces(ints_.get(i));
                _context.getAnalyzing().setCurrentBlock(c);
                _context.getAnalyzing().setGlobalOffset(offset_);
                _context.getAnalyzing().setOffset(0);
                base_ = _context.resolveAccessibleIdType(0,base_);
                RootBlock r_ = classes_.getClassBody(base_);
                if (!(r_ instanceof InterfaceBlock)) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(d_);
                    enum_.setIndexFile(_context.getCurrentLocationIndex());
                    //interface len
                    enum_.buildError(_context.getAnalysisMessages().getCallIntOnly(),
                            base_);
                    _context.addError(enum_);
                } else {
                    c.getStaticInitImportedInterfaces().add(base_);
                }
            }
            for (int i = 0; i < len_; i++) {
                String sup_ = ContextEl.removeDottedSpaces(ints_.get(i));
                int offsetSup_ = c.getStaticInitInterfacesOffset().get(i);
                _context.getAnalyzing().setCurrentBlock(c);
                _context.getAnalyzing().setGlobalClass(c.getGenericString());
                _context.getAnalyzing().setGlobalOffset(offsetSup_);
                _context.getAnalyzing().setOffset(0);
                sup_ = _context.resolveAccessibleIdType(0,sup_);
                RootBlock rs_ = classes_.getClassBody(sup_);
                if (rs_ == null) {
                    continue;
                }
                RootBlock rsSup_ = rs_;
                for (int j = i + 1; j < len_; j++) {
                    String sub_ = ContextEl.removeDottedSpaces(ints_.get(j));
                    int offsetSub_ = c.getStaticInitInterfacesOffset().get(j);
                    _context.getAnalyzing().setCurrentBlock(c);
                    _context.getAnalyzing().setGlobalClass(c.getGenericString());
                    _context.getAnalyzing().setGlobalOffset(offsetSub_);
                    _context.getAnalyzing().setOffset(0);
                    sub_ = _context.resolveAccessibleIdType(0,sub_);
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
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
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
            String id_ = Templates.getIdFromAllTypes(clName_);
            RootBlock superType_ = classes_.getClassBody(id_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringList.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            StringList filteredStatic_ = new StringList();
            for (String i: allCopy_) {
                RootBlock int_ = classes_.getClassBody(i);
                if (!(int_ instanceof InterfaceBlock)) {
                    continue;
                }
                for (Block b: Classes.getDirectChildren(int_)) {
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
                    if (b instanceof AloneBlock) {
                        AloneBlock a_ = (AloneBlock) b;
                        if (a_.getStaticContext() == MethodAccessKind.STATIC) {
                            filteredStatic_.add(i);
                        }
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
    private static void buildInherits(StandardType _type, ContextEl _context) {
        feedSupers(_type,_context,_type.getAllSuperTypes());
    }
    private static void feedSupers(StandardType _type, ContextEl _context, StringList _types) {
        StringList currentSuperTypes_ = new StringList(_type.getDirectSuperTypes());
        _types.addAllElts(currentSuperTypes_);
        while (true) {
            StringList newSuperTypes_ = new StringList();
            for (String c: currentSuperTypes_) {
                StandardType st_ = _context.getStandards().getStandards().getVal(c);
                for (String s: st_.getDirectSuperTypes()) {
                    newSuperTypes_.add(s);
                    _types.add(s);
                }
            }
            if (newSuperTypes_.isEmpty()) {
                break;
            }
            currentSuperTypes_ = newSuperTypes_;
        }
        _types.removeDuplicates();
    }
    public static void buildOverrides(RootBlock _type,ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        String fileName_ = _type.getFile().getFileName();
        for (ClassMethodId c: getAllDuplicates(_type, _context)) {
            FoundErrorInterpret err_;
            err_ = new FoundErrorInterpret();
            err_.setFileName(fileName_);
            NamedFunctionBlock sub_ = Classes.getMethodBodiesById(_context,c.getClassName(),c.getConstraints()).first();
            err_.setIndexFile(sub_.getReturnTypeOffset());
            //type id len
            err_.buildError(_context.getAnalysisMessages().getDuplicatedOverriding(),
                    _type.getFullName(),
                    StringList.concat(c.getClassName(),".",c.getConstraints().getSignature(_context)));
            _context.addError(err_);
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> allOv_ = getAllInstanceSignatures(_type, _context);
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: _type.getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: allOv_.entryList()) {
            MethodId key_ = e.getKey();
            CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
            EqList<ClassMethodId> allMethods_ = e.getValue();
            for (ClassMethodId c: allMethods_) {
                String templClass_ = c.getClassName();
                String typeName_ = Templates.getIdFromAllTypes(templClass_);
                RootBlock sub_ = classesRef_.getClassBody(typeName_);
                StringList allSuperTypes_ = sub_.getAllSuperTypes();
                for (ClassMethodId s: allMethods_) {
                    String super_ = s.getClassName();
                    String isSuper_ = Templates.getIdFromAllTypes(super_);
                    if (!StringList.quickEq(typeName_,isSuper_)) {
                        if (!StringList.contains(allSuperTypes_,isSuper_)) {
                            continue;
                        }
                    }
                    OverridingRelation ovRel_ = new OverridingRelation();
                    ovRel_.setSubMethod(c);
                    ovRel_.setSupMethod(s);
                    pairs_.add(ovRel_);
                }
            }
            CustList<OverridingRelation> relations_ = new CustList<OverridingRelation>();
            for (OverridingRelation l: pairs_) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                NamedFunctionBlock sub_ = Classes.getMethodBodiesById(_context,subId_.getClassName(), subId_.getConstraints()).first();
                NamedFunctionBlock sup_ = Classes.getMethodBodiesById(_context,supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    if (Classes.canAccess(_type.getFullName(), sub_, _context)) {
                        relations_.add(l);
                    }
                } else if (Classes.canAccess(subId_.getClassName(), sup_, _context)) {
                    relations_.add(l);
                }
            }
            for (OverridingRelation l: relations_) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                NamedFunctionBlock sub_ = Classes.getMethodBodiesById(_context,subId_.getClassName(), subId_.getConstraints()).first();
                NamedFunctionBlock sup_ = Classes.getMethodBodiesById(_context,supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    addClass(_type.getAllOverridingMethods(), key_, subId_);
                } else {
                    String retBase_ = sup_.getImportedReturnType();
                    String retDerive_ = sub_.getImportedReturnType();
                    String formattedRetDer_ = Templates.quickFormat(subId_.getClassName(), retDerive_, _context);
                    String formattedRetBase_ = Templates.quickFormat(supId_.getClassName(), retBase_, _context);
                    if (((OverridableBlock)sup_).isFinalMethod()) {
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
                        err_.buildError(_context.getContextEl().getAnalysisMessages().getMethodsAccesses(),
                                supId_.getClassName(),
                                supId_.getConstraints().getSignature(_context),
                                subId_.getClassName(),
                                subId_.getConstraints().getSignature(_context));
                        _context.addError(err_);
                        continue;
                    }
                    if (((OverridableBlock)sub_).getKind() != MethodKind.STD_METHOD) {
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
                    if (!Templates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _context)) {
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
    }
    public static StringMap<ClassMethodId> getConcreteMethodsToCall(GeneType _type,MethodId _realId, ContextEl _conf) {
        StringMap<ClassMethodId> eq_ = new StringMap<ClassMethodId>();
        String baseClassFound_ = _type.getFullName();
        for (RootBlock c: _conf.getClasses().getClassBodies()) {
            String name_ = c.getFullName();
            String baseCond_ = Templates.getFullTypeByBases(c.getGenericString(), baseClassFound_, _conf);
            if (baseCond_.isEmpty()) {
                continue;
            }
            ClassMethodId f_ = tryGetUniqueId(baseClassFound_, c, _realId, _conf);
            if (f_ != null) {
                eq_.put(name_, f_);
                continue;
            }
            EqList<ClassMethodId> finalMethods_ = new EqList<ClassMethodId>();
            EqList<ClassMethodId> methods_ = new EqList<ClassMethodId>();
            StringList all_ = new StringList();
            all_.add(name_);
            all_.addAllElts(c.getAllSuperTypes());
            for (String s: all_) {
                RootBlock r_ = _conf.getClasses().getClassBody(s);
                if (!(r_ instanceof GeneInterface)) {
                    continue;
                }
                String gene_ = r_.getGenericString();
                String v_ = Templates.getFullTypeByBases(gene_, baseClassFound_, _conf);
                if (v_.isEmpty()) {
                    continue;
                }
                //r_, as super interface of c, is a sub type of type input
                MethodId l_ = _realId.quickFormat(v_, _conf);
                ObjectMap<MethodId, EqList<ClassMethodId>> ov_ = r_.getAllOverridingMethods();
                //r_ inherit the formatted method
                EqList<ClassMethodId> foundSuperClasses_ = new EqList<ClassMethodId>();
                boolean found_ = false;
                //if the overridden types contain the type input, then retrieve the sub types of the input type
                //(which are super types of r_)
                for (ClassMethodId t: getList(ov_,l_)) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = Templates.getIdFromAllTypes(t_);
                    if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                        found_ = true;
                    }
                    RootBlock sub_ = _conf.getClasses().getClassBody(baseSuperType_);
                    if (!sub_.isSubTypeOf(baseClassFound_,_conf)) {
                        continue;
                    }
                    foundSuperClasses_.add(t);
                }
                if (!found_) {
                    continue;
                }
                feedMehodsLists(_conf, finalMethods_, methods_, foundSuperClasses_);
            }
            ClassMethodId id_ = filterUniqId(_conf, finalMethods_, methods_);
            if (id_ != null) {
                eq_.put(name_, id_);
                continue;
            }
            finalMethods_ = new EqList<ClassMethodId>();
            methods_ = new EqList<ClassMethodId>();
            MethodId l_ = _realId.quickFormat(baseCond_, _conf);
            ObjectMap<MethodId, EqList<ClassMethodId>> ov_ = c.getAllOverridingMethods();
            //r_ inherit the formatted method
            EqList<ClassMethodId> foundSuperClasses_ = new EqList<ClassMethodId>();
            boolean found_ = false;
            //if the overridden types contain the type input, then retrieve the sub types of the input type
            //(which are super types of r_)
            for (ClassMethodId t: getList(ov_,l_)) {
                String t_ = t.getClassName();
                String baseSuperType_ = Templates.getIdFromAllTypes(t_);
                if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                    found_ = true;
                }
                foundSuperClasses_.add(t);
            }
            if (!found_) {
                continue;
            }
            feedMehodsLists(_conf, finalMethods_, methods_, foundSuperClasses_);
            id_ = filterUniqId(_conf, finalMethods_, methods_);
            if (id_ != null) {
                eq_.put(name_, id_);
            }
        }
        return eq_;
    }

    private static void feedMehodsLists(ContextEl _conf, EqList<ClassMethodId> finalMethods_, EqList<ClassMethodId> methods_, EqList<ClassMethodId> foundSuperClasses_) {
        for (ClassMethodId t: foundSuperClasses_) {
            String t_ = t.getClassName();
            String baseSuperType_ = Templates.getIdFromAllTypes(t_);
            GeneCustMethod method_ = (GeneCustMethod) Classes.getMethodBodiesById(_conf,baseSuperType_, t.getConstraints()).first();
            if (method_.isAbstractMethod()) {
                continue;
            }
            if (method_.isFinalMethod()) {
                finalMethods_.add(t);
            }
            methods_.add(t);
        }
    }
    private static ClassMethodId filterUniqId(ContextEl _conf, EqList<ClassMethodId> finalMethods_, EqList<ClassMethodId> methods_) {
        StringMap<MethodId> defs_ = new StringMap<MethodId>();
        StringList list_ = new StringList();
        for (ClassMethodId v: finalMethods_) {
            defs_.put(v.getClassName(), v.getConstraints());
            list_.add(v.getClassName());
        }
        list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
        if (list_.onlyOneElt()) {
            String class_ = list_.first();
            String classBase_ = Templates.getIdFromAllTypes(class_);
            return new ClassMethodId(classBase_, defs_.getVal(class_));
        }
        defs_ = new StringMap<MethodId>();
        list_ = new StringList();
        for (ClassMethodId v: methods_) {
            defs_.put(v.getClassName(), v.getConstraints());
            list_.add(v.getClassName());
        }
        list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
        if (list_.onlyOneElt()) {
            String class_ = list_.first();
            String classBase_ = Templates.getIdFromAllTypes(class_);
            return new ClassMethodId(classBase_, defs_.getVal(class_));
        }
        return null;
    }

    private static ClassMethodId tryGetUniqueId(String _subTypeName, RootBlock _type, MethodId _realId, ContextEl _conf) {
        if (ContextEl.isEnumType(_type)) {
            String en_ = _conf.getStandards().getAliasEnumType();
            if (!Classes.getMethodBodiesById(_conf,en_, _realId).isEmpty()) {
                return new ClassMethodId(en_, _realId);
            }
        }
        //c is a concrete sub type of type input
        for (String s: _type.getAllGenericClasses()) {
            RootBlock r_ = _conf.getClasses().getClassBody(Templates.getIdFromAllTypes(s));
            String gene_ = r_.getGenericString();
            String v_ = Templates.getFullTypeByBases(gene_, _subTypeName, _conf);
            if (v_.isEmpty()) {
                continue;
            }
            //r_, as super class of c, is a sub type of type input
            MethodId l_ = _realId.quickFormat(v_, _conf);
            ObjectMap<MethodId, EqList<ClassMethodId>> ov_ = r_.getAllOverridingMethods();
            //r_ inherit the formatted method
            boolean found_ = false;
            TreeMap<String,MethodId> tree_ = new TreeMap<String,MethodId>(new ComparingByTypeList(r_.getAllGenericClasses()));
            //if the overridden types contain the type input, then look for the "most sub typed" super class of r_
            for (ClassMethodId t: getList(ov_,l_)) {
                String t_ = t.getClassName();
                String baseSuperType_ = Templates.getIdFromAllTypes(t_);
                if (StringList.quickEq(baseSuperType_, _subTypeName)) {
                    found_ = true;
                }
                if (_conf.getClassBody(baseSuperType_) instanceof GeneInterface) {
                    continue;
                }
                tree_.put(t_,t.getConstraints());
            }
            if (!found_) {
                continue;
            }
            String classNameFound_;
            MethodId realId_;
            if (tree_.isEmpty()) {
                continue;
            }
            classNameFound_ = tree_.firstKey();
            realId_ = tree_.firstValue();
            classNameFound_ = Templates.getIdFromAllTypes(classNameFound_);
            if (((GeneCustMethod) Classes.getMethodBodiesById(_conf,classNameFound_, realId_).first()).isAbstractMethod()) {
                continue;
            }
            return new ClassMethodId(classNameFound_, realId_);
        }
        return null;
    }
    private static EqList<ClassMethodId> getList( ObjectMap<MethodId, EqList<ClassMethodId>> _list, MethodId _id) {
        EqList<ClassMethodId> out_ = _list.getVal(_id);
        if (out_ == null) {
            return new EqList<ClassMethodId>();
        }
        return out_;
    }

    public static StringList getInners(String _root, String _innerName, Analyzable _an) {
        StringList inners_ = new StringList();
        for (String o: getOwners(_root, _innerName, _an)) {
            inners_.add(StringList.concat(o,"..",_innerName));
        }
        return inners_;
    }
    public static StringList getOwners(String _root, String _innerName, Analyzable _an) {
         return getOwners(_root,_innerName,false,_an);
    }
    public static StringList getInners(String _root, String _innerName, boolean _staticOnly, Analyzable _an) {
        StringList inners_ = new StringList();
        for (String o: getOwners(_root, _innerName,_staticOnly, _an)) {
            inners_.add(StringList.concat(o,"..",_innerName));
        }
        return inners_;
    }
    public static StringList getOwners(String _root, String _innerName, boolean _staticOnly, Analyzable _an) {
        StringList ids_ = new StringList(_root);
        StringList owners_ = new StringList();
        StringList visited_ = new StringList();
        while (true) {
            StringList new_ = new StringList();
            for (String s: ids_) {
                GeneType g_ = _an.getClassBody(s);
                if (!(g_ instanceof RootBlock)) {
                    continue;
                }
                RootBlock sub_ = (RootBlock)g_;
                added(_innerName, _staticOnly, owners_, s, sub_);
                for (String t: sub_.getImportedDirectBaseSuperTypes().values()) {
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
    public static StringList getGenericOwners(String _root, String _innerName, Analyzable _an) {
        StringList ids_ = new StringList(_root);
        StringList owners_ = new StringList();
        StringList visited_ = new StringList();
        while (true) {
            StringList new_ = new StringList();
            for (String s: ids_) {
                String id_ = Templates.getIdFromAllTypes(s);
                GeneType g_ = _an.getClassBody(id_);
                if (!(g_ instanceof RootBlock)) {
                    continue;
                }
                RootBlock sub_ = (RootBlock)g_;
                added(_innerName, false, owners_, s, sub_);
                for (String t: sub_.getImportedDirectSuperTypes()) {
                    if (!Templates.correctNbParameters(s,_an)) {
                        String format_ = Templates.getIdFromAllTypes(t);
                        GeneType sup_ = _an.getClassBody(format_);
                        if (!sup_.isStaticType()) {
                            continue;
                        }
                        addIfNotFound(visited_, new_, format_);
                        continue;
                    }
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

    private static void added(String _innerName, boolean _staticOnly, StringList owners_, String s, RootBlock sub_) {
        for (RootBlock b: Classes.accessedClassMembers(sub_)) {
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

    private static EqList<ClassMethodId> getAllDuplicates(RootBlock _type, ContextEl _classes) {
        EqList<ClassMethodId> list_;
        list_ = new EqList<ClassMethodId>();
        for (String s: _type.getAllGenericSuperTypes()) {
            EqList<MethodId> all_;
            all_ = new EqList<MethodId>();
            String base_ = Templates.getIdFromAllTypes(s);
            RootBlock b_ = _classes.getClasses().getClassBody(base_);
            for (GeneCustMethod b: Classes.getMethodBlocks(b_)) {
                if (b.hiddenInstance()) {
                    continue;
                }
                MethodId id_ = b.getId().quickFormat(s, _classes);
                ClassMethodId formatted_ = new ClassMethodId(s, b.getId());
                if (all_.containsObj(id_)) {
                    list_.add(formatted_);
                }
                all_.add(id_);
            }
        }
        return list_;
    }
    public static ObjectMap<MethodId, EqList<ClassMethodId>> getAllInstanceSignatures(RootBlock _type, ContextEl _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (GeneCustMethod b: Classes.getMethodBlocks(_type)) {
            if (b.hiddenInstance()) {
                continue;
            }
            MethodId m_ = b.getId();
            map_.put(m_, new EqList<ClassMethodId>(new ClassMethodId(_type.getGenericString(), m_)));
        }
        for (String s: _type.getAllGenericSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            RootBlock b_ = _classes.getClasses().getClassBody(base_);
            for (GeneCustMethod b: Classes.getMethodBlocks(b_)) {
                if (b.hiddenInstance()) {
                    continue;
                }
                MethodId m_ = b.getId();
                addClass(map_, b.getId().quickFormat(s, _classes), new ClassMethodId(s, m_));
            }
        }
        return map_;
    }

    private static void addClass(ObjectMap<MethodId, EqList<ClassMethodId>> _map, MethodId _key, ClassMethodId _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
            _map.getVal(_key).removeDuplicates();
        } else {
            _map.put(_key, new EqList<ClassMethodId>(_class));
        }
    }
}
