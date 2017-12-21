package code.expressionlanguage.stds;

import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.OverridingRelation;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorIndexes;

public abstract class StandardType {

    private final String name;

    private final String packageName = "";

    private final AccessEnum access = AccessEnum.PUBLIC;

    private final CustList<StandardConstructor> constructors;
    private final StringMap<StandardField> fields;
    private final ObjectMap<MethodId, StandardMethod> methods;

    private final ObjectMap<MethodId, EqList<ClassMethodId>> allOverridingMethods;

    private String iterative = "";

    protected StandardType(String _name,
            StringMap<StandardField> _fields,
            CustList<StandardConstructor> _constructors,
            ObjectMap<MethodId, StandardMethod> _methods) {
        name = _name;
        fields = _fields;
        constructors = _constructors;
        methods = _methods;
        allOverridingMethods = new ObjectMap<MethodId, EqList<ClassMethodId>>();
    }
    /** Copy the list*/
    public abstract StringList getDirectSuperClasses();
    public abstract StringList getDirectSuperTypes();

    public abstract StringList getDirectInterfaces();
    public final StringList getAllSuperTypes(LgNames _classes) {
        StringList list_ = new StringList();
        StringList current_ = new StringList(getName());
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = c;
                StandardType stdType_ = _classes.getStandards().getVal(baseType_);
                StringList superTypes_ = stdType_.getDirectSuperTypes();
                for (String t: superTypes_) {
                    String format_ = t;
                    list_.add(format_);
                    next_.add(format_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        return list_;
    }
    public static ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds,
            LgNames _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: e.getValue()) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            list_ = LgNames.getSubclasses(list_, _classes);
            EqList<ClassMethodId> out_ = new EqList<ClassMethodId>();
            for (String v: list_) {
                out_.add(new ClassMethodId(v, defs_.getVal(v)));
            }
            map_.put(e.getKey(), out_);
        }
        return map_;
    }
    public void buildOverridingMethods(LgNames _context) {
        ObjectMap<MethodId, EqList<ClassMethodId>> allOv_ = getAllInstanceSignatures(_context);
        ObjectMap<MethodId, EqList<ClassMethodId>> allBaseOv_ = getAllOverridingMethods(allOv_, _context);
        ObjectMap<MethodId,CustList<OverridingRelation>> allOverridings_ = new ObjectMap<MethodId,CustList<OverridingRelation>>();
        CustList<OverridingRelation> allBases_ = new CustList<OverridingRelation>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: allOv_.entryList()) {
            CustList<ClassMethodId> current_ = new CustList<ClassMethodId>();
            StringList visited_ = new StringList();
            CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
            for (ClassMethodId t: allBaseOv_.getVal(e.getKey())) {
                OverridingRelation ovRelBase_ = new OverridingRelation();
                ovRelBase_.setRealId(e.getKey());
                ovRelBase_.setSubMethod(t);
                ovRelBase_.setSupMethod(t);
                allBases_.add(ovRelBase_);
                pairs_.add(ovRelBase_);
                current_.add(t);
                visited_.add(t.getClassName());
            }
            while (true) {
                CustList<ClassMethodId> next_ = new CustList<ClassMethodId>();
                CustList<OverridingRelation> newpairs_ = new CustList<OverridingRelation>();
                for (ClassMethodId c: current_) {
                    String templClass_ = c.getClassName();
                    String typeName_ = templClass_;
                    StandardType root_ = _context.getStandards().getVal(typeName_);
                    for (String u:root_.getAllSuperTypes(_context)) {
                        String superType_ = u;
                        String superTypeName_ = u;
                        StandardType super_ = _context.getStandards().getVal(superTypeName_);
                        for (StandardMethod m: super_.getMethods().values()) {
                            MethodId f_ = m.getId();
                            if (f_.eq(c.getConstraints())) {
                                OverridingRelation ovRel_ = new OverridingRelation();
                                ovRel_.setRealId(e.getKey());
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
                    String superTypeId_ = superType_;
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
            allOverridings_.put(e.getKey(), pairs_);
        }
        ObjectMap<MethodId,CustList<OverridingRelation>> builtAccOverridings_ = new ObjectMap<MethodId,CustList<OverridingRelation>>();
        for (EntryCust<MethodId,CustList<OverridingRelation>> o: allOverridings_.entryList()) {
            CustList<ClassMethodId> current_ = new CustList<ClassMethodId>();
            CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
            for (OverridingRelation t: allBases_) {
                if (t.getRealId().eq(o.getKey())) {
                    current_.add(t.getSubMethod());
                    pairs_.add(t);
                }
            }
            while (true) {
                CustList<ClassMethodId> next_ = new CustList<ClassMethodId>();
                for (ClassMethodId c: current_) {
                    for (OverridingRelation a: o.getValue()) {
                        ClassMethodId clSup_ = a.getSupMethod();
                        ClassMethodId clSub_ = a.getSubMethod();
                        if (clSub_.eq(clSup_)) {
                            continue;
                        }
                        if (clSub_.eq(c)) {
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
            if (pairs_.isEmpty()) {
                continue;
            }
            builtAccOverridings_.put(o.getKey(), pairs_);
        }
        for (EntryCust<MethodId,CustList<OverridingRelation>> o: builtAccOverridings_.entryList()) {
            for (OverridingRelation l: o.getValue()) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                if (subId_.eq(supId_)) {
                    addClass(getAllOverridingMethods(), l.getRealId(), subId_);
                } else {
                    addClass(getAllOverridingMethods(), l.getRealId(), subId_);
                    addClass(getAllOverridingMethods(), l.getRealId(), supId_);
                }
            }
        }
    }
    public final ObjectMap<MethodId, EqList<ClassMethodId>> getAllInstanceSignatures(LgNames _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (StandardMethod b: methods.values()) {
            if (b.isStaticMethod()) {
                continue;
            }
            map_.put(b.getId(), new EqList<ClassMethodId>(new ClassMethodId(getName(), b.getId())));
        }
        for (String s: getAllSuperTypes(_classes)) {
            String base_ = StringList.getAllTypes(s).first();
            StandardType b_ = _classes.getStandards().getVal(base_);
            for (StandardMethod b: b_.methods.values()) {
                if (b.isStaticMethod()) {
                    continue;
                }
                addClass(map_, b.getId(), new ClassMethodId(s, b.getId()));
            }
        }
        return map_;
    }
    public final ObjectMap<MethodId, ClassMethodId> getLocalSignatures(Classes _classes) {
        ObjectMap<MethodId, ClassMethodId> map_;
        map_ = new ObjectMap<MethodId, ClassMethodId>();
        for (StandardMethod b: methods.values()) {
            map_.put(b.getId(), new ClassMethodId(getName(), b.getId()));
        }
        return map_;
    }
    public final StringMap<ClassMethodId> getConcreteMethodsToCall(MethodId _realId, LgNames _conf) {
        StringMap<ClassMethodId> eq_ = new StringMap<ClassMethodId>();
        LgNames classes_ = _conf;
        String baseClassFound_ = getName();
        for (StandardType c: classes_.getStandards().values()) {
            String name_ = c.getName();
            if (!LgNames.canBeUseAsArgument(baseClassFound_, name_, _conf)) {
                continue;
            }
            if (!c.mustImplement()) {
                continue;
            }
            StandardClass subClassBlock_ = (StandardClass) c;
            StringList allBaseClasses_ = new StringList(name_);
            allBaseClasses_.addAllElts(subClassBlock_.getAllSuperClasses(_conf));
            boolean foundConcrete_ = false;
            for (String s: allBaseClasses_) {
                if (!LgNames.canBeUseAsArgument(baseClassFound_, s, _conf)) {
                    continue;
                }
                StandardClass r_ = (StandardClass) classes_.getStandards().getVal(s);
                String gene_ = r_.getName();
                MethodId l_ = _realId;
                ObjectMap<MethodId, EqList<ClassMethodId>> ov_ = r_.getAllOverridingMethods();
                if (!ov_.contains(l_)) {
                    continue;
                }
                boolean found_ = false;
                StringList foundSuperClasses_ = new StringList();
                StringList allSuperClasses_ = new StringList(gene_);
                for (String t: r_.getAllSuperClasses(_conf)) {
                    allSuperClasses_.add(t);
                }
                EqList<ClassMethodId> list_ = ov_.getVal(l_);
                //pkg.ExTwo & pkg.Int3
                for (ClassMethodId t: list_) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = t_;
                    if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                        found_ = true;
                    }
                    if (classes_.getStandards().getVal(baseSuperType_) instanceof StandardInterface) {
                        continue;
                    }
                    foundSuperClasses_.add(t_);
                }
                if (!found_) {
                    continue;
                }
                String classNameFound_;
                MethodId realId_;
                foundSuperClasses_.sortElts(new ComparatorIndexes<String>(allSuperClasses_));
                if (foundSuperClasses_.isEmpty()) {
                    continue;
                }
                classNameFound_ = foundSuperClasses_.first();
                int i_ = 0;
                while (true) {
                    ClassMethodId methId_ = list_.get(i_);
                    if (StringList.quickEq(methId_.getClassName(), classNameFound_)) {
                        realId_ = methId_.getConstraints();
                        break;
                    }
                    i_++;
                }
                classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                if (classes_.getMethodBodiesById(classNameFound_, realId_).isAbstractMethod()) {
                    continue;
                }
                foundConcrete_ = true;
                eq_.put(name_, new ClassMethodId(classNameFound_, realId_));
                break;
            }
            if (foundConcrete_) {
                continue;
            }
            EqList<ClassMethodId> finalMethods_ = new EqList<ClassMethodId>();
            EqList<ClassMethodId> methods_ = new EqList<ClassMethodId>();
            for (String s: subClassBlock_.getAllInterfaces(_conf)) {
                if (!LgNames.canBeUseAsArgument(baseClassFound_, s, _conf)) {
                    continue;
                }
                StandardType r_ = classes_.getStandards().getVal(s);
                MethodId l_ = _realId;
                ObjectMap<MethodId, EqList<ClassMethodId>> ov_ = r_.getAllOverridingMethods();
                if (!ov_.contains(l_)) {
                    continue;
                }
                EqList<ClassMethodId> foundSuperClasses_ = new EqList<ClassMethodId>();
                boolean found_ = false;
                EqList<ClassMethodId> list_ = ov_.getVal(l_);
                //pkg.ExTwo & pkg.Int3
                for (ClassMethodId t: list_) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = StringList.getAllTypes(t_).first();
                    if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                        found_ = true;
                    }
                    if (!LgNames.canBeUseAsArgument(baseClassFound_, baseSuperType_, _conf)) {
                        continue;
                    }
                    foundSuperClasses_.add(t);
                }
                if (!found_) {
                    continue;
                }
                for (ClassMethodId t: foundSuperClasses_) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = t_;
                    StandardMethod method_ = classes_.getMethodBodiesById(baseSuperType_, t.getConstraints());
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
            list_ = LgNames.getSubclasses(list_, _conf);
            if (list_.size() == 1) {
                String class_ = list_.first();
                String classBase_ = class_;
                eq_.put(name_, new ClassMethodId(classBase_, defs_.getVal(class_)));
            } else {
                defs_ = new StringMap<MethodId>();
                list_ = new StringList();
                for (ClassMethodId v: methods_) {
                    defs_.put(v.getClassName(), v.getConstraints());
                    list_.add(v.getClassName());
                }
                list_ = LgNames.getSubclasses(list_, _conf);
                if (list_.size() == 1) {
                    String class_ = list_.first();
                    String classBase_ = class_;
                    eq_.put(name_, new ClassMethodId(classBase_, defs_.getVal(class_)));
                }
            }
        }
        return eq_;
    }
    public abstract boolean mustImplement();

    protected static void addClass(ObjectMap<MethodId, EqList<ClassMethodId>> _map, MethodId _key, ClassMethodId _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
            _map.getVal(_key).removeDuplicates();
        } else {
            _map.put(_key, new EqList<ClassMethodId>(_class));
        }
    }
    public String getPrettyString() {
        StringBuilder str_ = new StringBuilder();
        str_.append(getName());
        str_.append(" is ");
        str_.append(getClass().getName());
        str_.append("\n");
        for (StandardField f: fields.values()) {
            str_.append(f.getPrettyString(getName()));
            str_.append("\n");
        }
        str_.append("\n");
        for (StandardConstructor c: constructors) {
            str_.append(c.getPrettyString(getName()));
            str_.append("\n");
        }
        for (StandardMethod m: methods.values()) {
            str_.append(m.getPrettyString());
            str_.append("\n");
        }
        return str_.toString();
    }
    public StringMap<StandardField> getFields() {
        return fields;
    }
    public ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public AccessEnum getAccess() {
        return access;
    }

    public CustList<StandardConstructor> getConstructors() {
        return constructors;
    }

    public ObjectMap<MethodId, StandardMethod> getMethods() {
        return methods;
    }
    public String getIterative() {
        return iterative;
    }
    public void setIterative(String _iterative) {
        iterative = _iterative;
    }
}
