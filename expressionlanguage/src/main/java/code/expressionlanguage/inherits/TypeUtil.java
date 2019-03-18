package code.expressionlanguage.inherits;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneClass;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadAccessMethod;
import code.expressionlanguage.errors.custom.BadInheritedClass;
import code.expressionlanguage.errors.custom.BadReturnTypeInherit;
import code.expressionlanguage.errors.custom.FinalMethod;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.methods.AloneBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.OverridingRelation;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardType;
import code.util.*;

public final class TypeUtil {

    private TypeUtil() {
    }

    public static void buildInherits(ContextEl _context){
        for (EntryCust<String, StandardType> s: _context.getStandards().getStandards().entryList()) {
            buildInherits(s.getValue(), _context);
        }
        for (EntryCust<String, StandardType> s: _context.getStandards().getStandards().entryList()) {
            StandardType s_ = s.getValue();
            if (s_ instanceof StandardClass) {
                s_.getAllSuperTypes().addAllElts(s_.getAllSuperClasses());
                s_.getAllSuperTypes().addAllElts(s_.getAllInterfaces());
            } else {
                s_.getAllSuperTypes().addAllElts(s_.getAllSuperClasses());
            }
        }
    }

    public static void checkInterfaces(ContextEl _context, StringList _types, boolean _predefined) {
        Classes classes_ = _context.getClasses();
        for (RootBlock c: classes_.getClassBodies(_predefined)) {
            _context.getAnalyzing().setImporting(c);
            _context.getAnalyzing().setCurrentBlock(c);
            _context.getAnalyzing().setGlobalClass(c.getGenericString());
            String d_ = c.getFile().getFileName();
            StringList ints_ = c.getStaticInitInterfaces();
            int len_ = ints_.size();
            if (len_ > 0 && c instanceof GeneInterface) {
                BadInheritedClass enum_;
                enum_ = new BadInheritedClass();
                enum_.setClassName(c.getFullName());
                enum_.setFileName(d_);
                enum_.setIndexFile(_context.getCurrentLocationIndex());
                classes_.addError(enum_);
            }
            for (int i = 0; i < len_; i++) {
                int offset_ = c.getStaticInitInterfacesOffset().get(i);
                String base_ = ContextEl.removeDottedSpaces(ints_.get(i));
                _context.getAnalyzing().setCurrentBlock(c);
                _context.getAnalyzing().setOffset(offset_);
                base_ = _context.resolveAccessibleIdType(base_);
                RootBlock r_ = classes_.getClassBody(base_);
                if (!(r_ instanceof InterfaceBlock)) {
                    BadInheritedClass enum_;
                    enum_ = new BadInheritedClass();
                    enum_.setClassName(base_);
                    enum_.setFileName(d_);
                    enum_.setIndexFile(_context.getCurrentLocationIndex());
                    classes_.addError(enum_);
                } else {
                    c.getStaticInitImportedInterfaces().add(base_);
                }
            }
            for (int i = 0; i < len_; i++) {
                String sup_ = ContextEl.removeDottedSpaces(ints_.get(i));
                int offsetSup_ = c.getStaticInitInterfacesOffset().get(i);
                _context.getAnalyzing().setCurrentBlock(c);
                _context.getAnalyzing().setGlobalClass(c.getGenericString());
                _context.getAnalyzing().setOffset(offsetSup_);
                sup_ = _context.resolveAccessibleIdType(sup_);
                RootBlock rs_ = classes_.getClassBody(sup_);
                if (rs_ == null) {
                    continue;
                }
                for (int j = i + 1; j < len_; j++) {
                    String sub_ = ContextEl.removeDottedSpaces(ints_.get(j));
                    int offsetSub_ = c.getStaticInitInterfacesOffset().get(j);
                    _context.getAnalyzing().setCurrentBlock(c);
                    _context.getAnalyzing().setGlobalClass(c.getGenericString());
                    _context.getAnalyzing().setOffset(offsetSub_);
                    sub_ = _context.resolveAccessibleIdType(sub_);
                    rs_ = classes_.getClassBody(sub_);
                    if (rs_ == null) {
                        continue;
                    }
                    if (PrimitiveTypeUtil.canBeUseAsArgument(sub_, sup_, _context)) {
                        BadInheritedClass undef_;
                        undef_ = new BadInheritedClass();
                        undef_.setClassName(sub_);
                        undef_.setFileName(d_);
                        int offset_ = c.getStaticInitInterfacesOffset().get(j);
                        undef_.setIndexFile(offset_);
                        classes_.addError(undef_);
                    }
                }
            }
        }
        for (String c: _types) {
            GeneType bl_ = _context.getClassBody(c);
            if (!(bl_ instanceof UniqueRootedBlock)) {
                continue;
            }
            UniqueRootedBlock un_ = (UniqueRootedBlock)bl_;
            StringList ints_ = un_.getStaticInitImportedInterfaces();
            StringList trimmedInt_ = new StringList();
            for (String i: ints_) {
                trimmedInt_.add(i);
            }
            StringList all_ = bl_.getAllInterfaces();
            StringList allCopy_ = new StringList(all_);
            allCopy_.removeAllElements(_context.getStandards().getPredefinedInterfacesInitOrder());
            String clName_ = un_.getImportedDirectGenericSuperClass();
            String id_ = Templates.getIdFromAllTypes(clName_);
            RootBlock superType_ = classes_.getClassBody(id_);
            if (superType_ != null) {
                allCopy_.removeAllElements(superType_.getAllInterfaces());
            }
            StringList filteredStatic_ = new StringList();
            for (String i: allCopy_) {
                RootBlock int_ = classes_.getClassBody(i);
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
                        if (a_.isStaticContext()) {
                            filteredStatic_.add(i);
                        }
                    }
                }
            }
            if (!StringList.equalsSet(filteredStatic_, trimmedInt_)) {
                BadInheritedClass undef_;
                undef_ = new BadInheritedClass();
                undef_.setClassName(c);
                undef_.setFileName(un_.getFile().getFileName());
                undef_.setIndexFile(0);
                classes_.addError(undef_);
            }
        }
    }
    private static void buildInherits(StandardType _type, ContextEl _context) {
        String aliasObject_ = _context.getStandards().getAliasObject();
        if (_type instanceof StandardClass) {
            StandardClass type_ = (StandardClass) _type;
            String typeName_ = type_.getSuperClass(_context);
            while (true) {
                if (typeName_.isEmpty()) {
                    break;
                }
                _type.getAllSuperClasses().add(typeName_);
                if (StringList.quickEq(typeName_, aliasObject_)) {
                    break;
                }
                type_ = (StandardClass) _context.getClassBody(typeName_);
                typeName_ = type_.getSuperClass(_context);
            }
            type_ = (StandardClass) _type;
            StringList allSuperInterfaces_ = new StringList(type_.getDirectInterfaces(_context));
            for (String s: _type.getAllSuperClasses()) {
                allSuperInterfaces_.addAllElts(((StandardClass)_context.getClassBody(s)).getDirectInterfaces(_context));
            }
            feedInts(_type,_context,allSuperInterfaces_);
            type_.getAllInterfaces().addAllElts(allSuperInterfaces_);
        } else {
            StandardInterface type_ = (StandardInterface) _type;
            StringList allSuperInterfaces_ = new StringList(type_.getDirectSuperClasses(_context));
            feedInts(_type,_context,allSuperInterfaces_);
            type_.getAllSuperClasses().addAllElts(allSuperInterfaces_);
        }
    }
    private static void feedInts(StandardType _type, ContextEl _context, StringList _ints) {
        String aliasObject_ = _context.getStandards().getAliasObject();
        StringList currentSuperInterfaces_ = new StringList(_type.getDirectInterfaces());
        while (true) {
            StringList newSuperInterfaces_ = new StringList();
            for (String c: currentSuperInterfaces_) {
                GeneType st_ = _context.getClassBody(c);
                if (!(st_ instanceof StandardInterface)) {
                    continue;
                }
                StandardInterface superType_ = (StandardInterface) st_;
                for (String s: superType_.getDirectSuperClasses(_context)) {
                    newSuperInterfaces_.add(s);
                    _ints.add(s);
                }
            }
            if (newSuperInterfaces_.isEmpty()) {
                break;
            }
            currentSuperInterfaces_ = newSuperInterfaces_;
        }
        _ints.removeAllObj(aliasObject_);
        _ints.removeDuplicates();
    }
    public static void buildOverrides(GeneType _type,ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        String fileName_ = "";
        if (_type instanceof RootBlock) {
            fileName_ = ((RootBlock)_type).getFile().getFileName();
        }
        for (ClassMethodId c: getAllDuplicates(_type, _context)) {
            BadReturnTypeInherit err_;
            err_ = new BadReturnTypeInherit();
            err_.setFileName(fileName_);
            GeneMethod sub_ = _context.getMethodBodiesById(c.getClassName(), c.getConstraints()).first();
            err_.setIndexFile(((MethodBlock) sub_).getReturnTypeOffset());
            err_.setReturnType(sub_.getImportedReturnType());
            err_.setMethod(c.getConstraints());
            err_.setParentClass(c.getClassName());
            classesRef_.addError(err_);
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> allOv_ = getAllInstanceSignatures(_type, _context);
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: _type.getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: allOv_.entryList()) {
            MethodId key_ = e.getKey();
            CustList<ClassMethodId> current_ = new CustList<ClassMethodId>();
            StringList visited_ = new StringList();
            CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: e.getValue()) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            list_ = PrimitiveTypeUtil.getSubclasses(list_, _context);
            EqList<ClassMethodId> out_ = new EqList<ClassMethodId>();
            for (String v: list_) {
                out_.add(new ClassMethodId(v, defs_.getVal(v)));
            }
            for (ClassMethodId t: out_) {
                OverridingRelation ovRelBase_ = new OverridingRelation();
                ovRelBase_.setSubMethod(t);
                ovRelBase_.setSupMethod(t);
                ovRelBase_.setBase(true);
                pairs_.add(ovRelBase_);
                current_.add(t);
                visited_.add(Templates.getIdFromAllTypes(t.getClassName()));
            }
            while (true) {
                CustList<ClassMethodId> next_ = new CustList<ClassMethodId>();
                CustList<OverridingRelation> newpairs_ = new CustList<OverridingRelation>();
                for (ClassMethodId c: current_) {
                    String templClass_ = c.getClassName();
                    String typeName_ = Templates.getIdFromAllTypes(templClass_);
                    GeneType root_ = _context.getClassBody(typeName_);
                    for (String u:root_.getAllGenericSuperTypes()) {
                        String superType_ = Templates.quickFormat(templClass_, u, _context);
                        String superTypeName_ = Templates.getIdFromAllTypes(u);
                        GeneType super_ = _context.getClassBody(superTypeName_);
                        for (GeneMethod m: ContextEl.getMethodBlocks(super_)) {
                            MethodId f_ = m.getQuickFormattedId(superType_, _context);
                            if (f_.eq(c.getConstraints().quickFormat(templClass_, _context))) {
                                OverridingRelation ovRel_ = new OverridingRelation();
                                ovRel_.setSubMethod(c);
                                ovRel_.setSupMethod(new ClassMethodId(superType_, m.getId()));
                                newpairs_.add(ovRel_);
                            }
                        }
                    }
                }
                for (OverridingRelation p: newpairs_) {
                    pairs_.add(p);
                    String superType_ = p.getSupMethod().getClassName();
                    String superTypeId_ = Templates.getIdFromAllTypes(superType_);
                    if (!visited_.containsStr(superTypeId_)) {
                        next_.add(p.getSupMethod());
                        visited_.add(superTypeId_);
                    }
                }
                if (next_.isEmpty()) {
                    break;
                }
                current_ = next_;
            }
            CustList<OverridingRelation> relations_ = new CustList<OverridingRelation>();
            for (OverridingRelation l: pairs_) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                GeneMethod sub_ = _context.getMethodBodiesById(subId_.getClassName(), subId_.getConstraints()).first();
                GeneMethod sup_ = _context.getMethodBodiesById(supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    if (Classes.canAccess(_type.getFullName(), sub_, _context)) {
                        relations_.add(l);
                    }
                } else if (Classes.canAccess(subId_.getClassName(), sup_, _context)) {
                    relations_.add(l);
                }
            }
            current_ = new CustList<ClassMethodId>();
            pairs_ = new CustList<OverridingRelation>();
            for (OverridingRelation t: relations_) {
                if (!t.isBase()) {
                    continue;
                }
                current_.add(t.getSubMethod());
                pairs_.add(t);
            }
            EqList<ClassMethodId> all_ = new EqList<ClassMethodId>();
            while (true) {
                CustList<ClassMethodId> next_ = new CustList<ClassMethodId>();
                for (ClassMethodId c: current_) {
                    for (OverridingRelation a: relations_) {
                        ClassMethodId clSup_ = a.getSupMethod();
                        ClassMethodId clSub_ = a.getSubMethod();
                        if (clSub_.eq(clSup_)) {
                            continue;
                        }
                        if (clSub_.eq(c)) {
                            if (all_.containsObj(clSup_)) {
                                continue;
                            }
                            all_.add(clSup_);
                            next_.add(clSup_);
                            pairs_.add(a);
                        }
                    }
                }
                if (next_.isEmpty()) {
                    break;
                }
                current_ = next_;
            }
            for (OverridingRelation l: relations_) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                GeneMethod sub_ = _context.getMethodBodiesById(subId_.getClassName(), subId_.getConstraints()).first();
                GeneMethod sup_ = _context.getMethodBodiesById(supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    addClass(_type.getAllOverridingMethods(), key_, subId_);
                } else {
                    String retBase_ = sup_.getImportedReturnType();
                    String retDerive_ = sub_.getImportedReturnType();
                    String formattedRetDer_ = Templates.quickFormat(subId_.getClassName(), retDerive_, _context);
                    String formattedRetBase_ = Templates.quickFormat(supId_.getClassName(), retBase_, _context);
                    if (sup_.isFinalMethod()) {
                        FinalMethod err_;
                        err_ = new FinalMethod();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(((MethodBlock) sub_).getNameOffset());
                        err_.setClassName(subId_.getClassName());
                        err_.setId(sub_.getId());
                        classesRef_.addError(err_);
                        continue;
                    }
                    if (sup_.getAccess().isStrictMoreAccessibleThan(sub_.getAccess())) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(((MethodBlock) sub_).getAccessOffset());
                        err_.setId(sub_.getId());
                        classesRef_.addError(err_);
                        continue;
                    }
                    if (!Templates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _context)) {
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(fileName_);
                        err_.setIndexFile(((MethodBlock) sub_).getReturnTypeOffset());
                        err_.setReturnType(retDerive_);
                        err_.setMethod(sub_.getId());
                        err_.setParentClass(supId_.getClassName());
                        classesRef_.addError(err_);
                        continue;
                    }
                    addClass(_type.getAllOverridingMethods(), key_, subId_);
                    addClass(_type.getAllOverridingMethods(), key_, supId_);
                }
            }
        }
    }
    static CustList<GeneMethod> getMethodBodiesByFormattedId(ContextEl _context, boolean _static, String _genericClassName, String _methodName, StringList _parametersTypes, boolean _vararg) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        int nbParams_ = _parametersTypes.size();
        for (GeneType c: _context.getClassBodies()) {
            if (!StringList.quickEq(c.getFullName(), base_)) {
                continue;
            }
            CustList<GeneMethod> bl_ = ContextEl.getMethodBlocks(c);
            for (GeneMethod b: bl_) {
                if (!StringList.quickEq(_methodName, b.getName())) {
                    continue;
                }
                StringList list_ = b.getId().getParametersTypes();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (_static != b.isStaticMethod()) {
                    continue;
                }
                if (nbParams_ > 0 && _vararg) {
                    if (!b.isVarargs()) {
                        continue;
                    }
                } else {
                    if (b.isVarargs()) {
                        continue;
                    }
                }
                boolean all_ = true;
                for (int i = CustList.FIRST_INDEX; i < nbParams_; i++) {
                    String type_ = Templates.quickFormat(_genericClassName, list_.get(i), _context);
                    if (!StringList.quickEq(type_, _parametersTypes.get(i))) {
                        all_ = false;
                        break;
                    }
                }
                if (!all_) {
                    continue;
                }
                methods_.add(b);
            }
        }
        return methods_;
    }
    public static StringMap<ClassMethodId> getConcreteMethodsToCall(GeneType _type,MethodId _realId, ContextEl _conf) {
        StringMap<ClassMethodId> eq_ = new StringMap<ClassMethodId>();
        String baseClassFound_ = _type.getFullName();
        for (GeneType c: _conf.getClassBodies()) {
            String name_ = c.getFullName();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, name_, _conf)) {
                continue;
            }
            if (!c.mustImplement()) {
                continue;
            }
            ClassMethodId f_ = tryGetUniqueId(baseClassFound_, c, _realId, _conf);
            if (f_ != null) {
                eq_.put(name_, f_);
                continue;
            }
            GeneClass subClassBlock_ = (GeneClass) c;
            EqList<ClassMethodId> finalMethods_ = new EqList<ClassMethodId>();
            EqList<ClassMethodId> methods_ = new EqList<ClassMethodId>();
            for (String s: subClassBlock_.getAllInterfaces()) {
                if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, s, _conf)) {
                    continue;
                }
                GeneType r_ = _conf.getClassBody(s);
                //r_, as super interface of c, is a sub type of type input
                String gene_ = r_.getGenericString();
                String v_ = Templates.getFullTypeByBases(gene_, baseClassFound_, _conf);
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
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, baseSuperType_, _conf)) {
                        continue;
                    }
                    foundSuperClasses_.add(t);
                }
                if (!found_) {
                    continue;
                }
                for (ClassMethodId t: foundSuperClasses_) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = Templates.getIdFromAllTypes(t_);
                    GeneMethod method_ = _conf.getMethodBodiesById(baseSuperType_, t.getConstraints()).first();
                    if (method_.isAbstractMethod()) {
                        continue;
                    }
                    if (method_.isFinalMethod()) {
                        finalMethods_.add(t);
                    }
                    methods_.add(t);
                }
            }
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: finalMethods_) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
            if (list_.size() == 1) {
                String class_ = list_.first();
                String classBase_ = Templates.getIdFromAllTypes(class_);
                eq_.put(name_, new ClassMethodId(classBase_, defs_.getVal(class_)));
            } else if (list_.isEmpty()) {
                defs_ = new StringMap<MethodId>();
                list_ = new StringList();
                for (ClassMethodId v: methods_) {
                    defs_.put(v.getClassName(), v.getConstraints());
                    list_.add(v.getClassName());
                }
                list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
                if (list_.size() == 1) {
                    String class_ = list_.first();
                    String classBase_ = Templates.getIdFromAllTypes(class_);
                    eq_.put(name_, new ClassMethodId(classBase_, defs_.getVal(class_)));
                }
            }
        }
        return eq_;
    }
    public static ClassMethodId tryGetUniqueId(String _subTypeName,GeneType _type,MethodId _realId, ContextEl _conf) {
        String name_ = _type.getFullName();
        if (_type instanceof EnumBlock) {
            String en_ = _conf.getStandards().getAliasEnum();
            if (!_conf.getMethodBodiesById(en_, _realId).isEmpty()) {
                return new ClassMethodId(en_, _realId);
            }
        }
        //c is a concrete sub type of type input
        GeneClass subClassBlock_ = (GeneClass) _type;
        StringList allBaseClasses_ = new StringList(name_);
        allBaseClasses_.addAllElts(subClassBlock_.getAllSuperClasses());
        for (String s: allBaseClasses_) {
            if (!PrimitiveTypeUtil.canBeUseAsArgument(_subTypeName, s, _conf)) {
                continue;
            }
            GeneClass r_ = (GeneClass) _conf.getClassBody(s);
            //r_, as super class of c, is a sub type of type input
            String gene_ = r_.getGenericString();
            String v_ = Templates.getFullTypeByBases(gene_, _subTypeName, _conf);
            MethodId l_ = _realId.quickFormat(v_, _conf);
            ObjectMap<MethodId, EqList<ClassMethodId>> ov_ = r_.getAllOverridingMethods();
            //r_ inherit the formatted method
            boolean found_ = false;
            StringList allSuperClasses_ = new StringList(gene_);
            for (String t: r_.getAllSuperClasses()) {
                allSuperClasses_.add(Templates.getFullTypeByBases(gene_, t, _conf));
            }
            TreeMap<String,MethodId> tree_ = new TreeMap<String,MethodId>(new ComparingByTypeList(allSuperClasses_));
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
            if (_conf.getMethodBodiesById(classNameFound_, realId_).first().isAbstractMethod()) {
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

    public static StringList getBuiltInners(boolean _protectedInc,String _gl, String _root, String _innerName, boolean _static,Analyzable _an) {
        StringList inners_ = new StringList();
        for (String o: getOwners(true,_protectedInc, _gl, _root, _innerName, _static, _an)) {
            inners_.add(StringList.concat(o,"..",_innerName));
        }
        return inners_;
    }
    public static StringList getInners(boolean _protectedInc,String _gl, String _root, String _innerName, boolean _static,Analyzable _an) {
        StringList inners_ = new StringList();
        for (String o: getOwners(false,_protectedInc, _gl, _root, _innerName, _static, _an)) {
            inners_.add(StringList.concat(o,"..",_innerName));
        }
        return inners_;
    }
    public static StringList getOwners(boolean _inherits,boolean _protectedInc,String _gl, String _root, String _innerName, boolean _staticOnly,Analyzable _an) {
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
                boolean add_ = false;
                for (RootBlock b: Classes.accessedClassMembers(_inherits, _protectedInc, _root,_gl,sub_, _an)) {
                    if (_staticOnly) {
                        if (!b.isStaticType()) {
                            continue;
                        }
                    }
                    String name_ = b.getName();
                    if (StringList.quickEq(name_, _innerName)) {
                        owners_.add(s);
                        add_ = true;
                    }
                }
                if (add_) {
                    continue;
                }
                for (String t: sub_.getImportedDirectBaseSuperTypes()) {
                    addIfNotFound(visited_, new_, t);
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            ids_ = new_;
        }
        owners_.removeDuplicates();
        return owners_;
    }
    public static StringList getGenericOwners(boolean _inherits, boolean _protectedInc, String _gl, String _root, String _innerName, Analyzable _an) {
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
                boolean add_ = false;
                for (RootBlock b: Classes.accessedClassMembers(_inherits, _protectedInc, _root,_gl,sub_, _an)) {
                    String name_ = b.getName();
                    if (StringList.quickEq(name_, _innerName)) {
                        owners_.add(s);
                        add_ = true;
                    }
                }
                if (add_) {
                    continue;
                }
                for (String t: sub_.getDirectGenericSuperTypes(_an)) {
                    String format_ = Templates.quickFormat(s, t, _an);
                    addIfNotFound(visited_, new_, format_);
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            ids_ = new_;
        }
        owners_.removeDuplicates();
        return owners_;
    }

    private static void addIfNotFound(StringList _visited, StringList _new, String _format) {
        if (_visited.containsStr(_format)) {
            return;
        }
        _visited.add(_format);
        _new.add(_format);
    }

    public static TypeOwnersDepends getOwnersDepends(boolean _protectedInc,String _gl, String _root, String _innerName, Analyzable _an) {
        TypeOwnersDepends out_ = new TypeOwnersDepends();
        StringList ids_ = new StringList(_root);
        StringList owners_ = new StringList();
        StringList depends_ = new StringList();
        StringList visited_ = new StringList();
        while (true) {
            StringList new_ = new StringList();
            for (String s: ids_) {
                GeneType g_ = _an.getClassBody(s);
                if (!(g_ instanceof RootBlock)) {
                    continue;
                }
                RootBlock sub_ = (RootBlock)g_;
                boolean add_ = false;
                for (RootBlock b: Classes.accessedClassMembers(true, _protectedInc, _root,_gl,sub_, _an)) {
                    String name_ = b.getName();
                    if (StringList.quickEq(name_, _innerName)) {
                        owners_.add(s);
                        add_ = true;
                    }
                }
                if (add_) {
                    continue;
                }
                if (!sub_.getImportedDirectBaseSuperTypes().isEmpty()) {
                    depends_.add(s);
                }
                for (String t: sub_.getImportedDirectBaseSuperTypes()) {
                    addIfNotFound(visited_, new_, t);
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            ids_ = new_;
        }
        owners_.removeDuplicates();
        out_.getTypeOwners().addAllElts(owners_);
        out_.getDepends().addAllElts(depends_);
        return out_;
    }
    private static EqList<ClassMethodId> getAllDuplicates(GeneType _type, ContextEl _classes) {
        EqList<ClassMethodId> list_;
        list_ = new EqList<ClassMethodId>();
        for (String s: _type.getAllGenericSuperTypes()) {
            EqList<MethodId> all_;
            all_ = new EqList<MethodId>();
            String base_ = Templates.getIdFromAllTypes(s);
            GeneType b_ = _classes.getClassBody(base_);
            for (GeneMethod b: ContextEl.getMethodBlocks(b_)) {
                if (b.isStaticMethod()) {
                    continue;
                }
                MethodId id_ = b.getQuickFormattedId(s, _classes);
                ClassMethodId formatted_ = new ClassMethodId(s, b.getId());
                if (all_.containsObj(id_)) {
                    list_.add(formatted_);
                }
                all_.add(id_);
            }
        }
        list_.removeDuplicates();
        return list_;
    }
    public static ObjectMap<MethodId, EqList<ClassMethodId>> getAllInstanceSignatures(GeneType _type, ContextEl _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (GeneMethod b: ContextEl.getMethodBlocks(_type)) {
            if (b.isStaticMethod()) {
                continue;
            }
            MethodId m_ = b.getId();
            map_.put(m_, new EqList<ClassMethodId>(new ClassMethodId(_type.getGenericString(), m_)));
        }
        for (String s: _type.getAllGenericSuperTypes()) {
            String base_ = Templates.getIdFromAllTypes(s);
            GeneType b_ = _classes.getClassBody(base_);
            for (GeneMethod b: ContextEl.getMethodBlocks(b_)) {
                if (b.isStaticMethod()) {
                    continue;
                }
                MethodId m_ = b.getId();
                addClass(map_, b.getQuickFormattedId(s, _classes), new ClassMethodId(s, m_));
            }
        }
        return map_;
    }
    public static ObjectMap<MethodId, ClassMethodId> getLocalSignatures(GeneType _type) {
        ObjectMap<MethodId, ClassMethodId> map_;
        map_ = new ObjectMap<MethodId, ClassMethodId>();
        for (GeneMethod b: ContextEl.getMethodBlocks(_type)) {
            MethodId m_ = b.getId();
            map_.put(m_, new ClassMethodId(_type.getGenericString(), m_));
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
