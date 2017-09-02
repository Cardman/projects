package code.expressionlanguage.methods;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;

public abstract class RootBlock extends BracedBlock implements RootedBlock {

    RootBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public abstract void setupBasicOverrides(ContextEl _context);

    public ObjectMap<FctConstraints, StringList> getAllSignatures(Classes _classes) {
        ObjectMap<FctConstraints, StringList> map_;
        map_ = new ObjectMap<FctConstraints, StringList>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                map_.put(method_.getConstraints(), new StringList(getFullName()));
            }
        }
        for (String s: getAllSuperTypes()) {
            RootBlock b_ = (RootBlock) _classes.getClassBody(s);
            for (Block b: Classes.getDirectChildren(b_)) {
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    addClass(map_, method_.getConstraints(), s);
                }
            }
        }
        return map_;
    }

    public ObjectMap<FctConstraints, String> getLocalSignatures(Classes _classes) {
        ObjectMap<FctConstraints, String> map_;
        map_ = new ObjectMap<FctConstraints, String>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                map_.put(method_.getConstraints(), getFullName());
            }
        }
        return map_;
    }

    public static ObjectMap<FctConstraints, StringList> getAllOverridingMethods(
            ObjectMap<FctConstraints, StringList> _methodIds,
            Classes _classes) {
        ObjectMap<FctConstraints, StringList> map_;
        map_ = new ObjectMap<FctConstraints, StringList>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            map_.put(e.getKey(), PrimitiveTypeUtil.getSubclasses(e.getValue(), _classes));
        }
        return map_;
    }
    public static ObjectMap<FctConstraints, StringList> areCompatible(
            ObjectMap<FctConstraints, String> _localMethodIds,
            ObjectMap<FctConstraints, StringList> _methodIds, Classes _classes) {
        ObjectMap<FctConstraints, StringList> output_;
        output_ = new ObjectMap<FctConstraints, StringList>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            FctConstraints cst_ = e.getKey();
            StringList classes_ = e.getValue();
            if (_localMethodIds.contains(e.getKey())) {
                //overridden by this interface
                String subInt_ = _localMethodIds.getVal(e.getKey());
                MethodBlock sub_ = _classes.getMethodBody(subInt_, cst_);
                if (sub_.isStaticMethod()) {
                    StringList retClasses_ = new StringList();
                    for (String s: e.getValue()) {
                        MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                        if (sup_.isStaticMethod()) {
                            continue;
                        }
                        retClasses_.add(sup_.getReturnType());
                    }
                    if (!retClasses_.isEmpty() && PrimitiveTypeUtil.getSubslass(retClasses_, _classes).isEmpty()) {
                        for (String c: classes_) {
                            addClass(output_, e.getKey(), c);
                        }
                    }
                    continue;
                }
                String subType_ = sub_.getReturnType();
                for (String s: e.getValue()) {
                    MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                    if (sup_.isStaticMethod()) {
                        continue;
                    }
                    String supType_ = sup_.getReturnType();
                    if (StringList.quickEq(supType_, subType_)) {
                        continue;
                    }
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(supType_, subType_, _classes)) {
                        addClass(output_, e.getKey(), subInt_);
                        addClass(output_, e.getKey(), s);
                    }
                }
                continue;
            }
            StringList retClasses_ = new StringList();
            for (String s: e.getValue()) {
                MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                if (sup_.isStaticMethod()) {
                    continue;
                }
                retClasses_.add(sup_.getReturnType());
            }
            if (!retClasses_.isEmpty() && PrimitiveTypeUtil.getSubslass(retClasses_, _classes).isEmpty()) {
                for (String c: classes_) {
                    addClass(output_, e.getKey(), c);
                }
            }
        }
        return output_;
    }
    public static ObjectMap<FctConstraints, StringList> areModifierCompatible(
            ObjectMap<FctConstraints, StringList> _methodIds, Classes _classes) {
        ObjectMap<FctConstraints, StringList> output_;
        output_ = new ObjectMap<FctConstraints, StringList>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            FctConstraints cst_ = e.getKey();
            StringList retClasses_ = new StringList();
            StringList fClasses_ = new StringList();
            StringList aClasses_ = new StringList();
            for (String s: e.getValue()) {
                MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                if (sup_.isStaticMethod()) {
                    continue;
                }
                if (sup_.isAbstractMethod()) {
                    aClasses_.add(s);
                }
                if (sup_.isFinalMethod()) {
                    fClasses_.add(s);
                }
                retClasses_.add(sup_.getReturnType());
            }
            if (fClasses_.size() > 1) {
                for (String c: fClasses_) {
                    addClass(output_, e.getKey(), c);
                }
                continue;
            }
            if (fClasses_.size() > 0 && aClasses_.size() > 0) {
                for (String c: fClasses_) {
                    addClass(output_, e.getKey(), c);
                }
                for (String c: aClasses_) {
                    addClass(output_, e.getKey(), c);
                }
                continue;
            }
            if (fClasses_.size() == 1) {
                String subInt_ = fClasses_.first();
                MethodBlock sub_ = _classes.getMethodBody(subInt_, cst_);
                String subType_ = sub_.getReturnType();
                for (String s: e.getValue()) {
                    MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                    if (sup_.isStaticMethod()) {
                        continue;
                    }
                    String supType_ = sup_.getReturnType();
                    if (StringList.quickEq(supType_, subType_)) {
                        continue;
                    }
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(supType_, subType_, _classes)) {
                        addClass(output_, e.getKey(), subInt_);
                        addClass(output_, e.getKey(), s);
                    }
                }
            }
        }
        return output_;
    }
    public static EqList<ClassMethodId> remainingMethodsToImplement(
            ObjectMap<FctConstraints, StringList> _methodIds,
            Classes _classes) {
        EqList<ClassMethodId> rem_ = new EqList<ClassMethodId>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            int nbConcrete_ = 0;
            int nbFinal_ = 0;
            int nbAbs_ = 0;
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
                if (method_.isStaticMethod()) {
                    continue;
                }
                if (method_.isFinalMethod()) {
                    nbFinal_++;
                }
                if (method_.isConcreteMethod()) {
                    nbConcrete_++;
                } else {
                    nbAbs_++;
                }
            }
            if (nbConcrete_ != 1 && nbFinal_ == 0|| nbAbs_ != 0) {
                for (String f: e.getValue()) {
                    ClassName cl_ = new ClassName(f, false);
                    ClassMethodId id_ = new ClassMethodId(cl_, e.getKey());
                    rem_.add(id_);
                }
            }
        }
        return rem_;
    }
    public static ObjectMap<FctConstraints, String> defaultMethods(
            ObjectMap<FctConstraints, StringList> _methodIds,
            Classes _classes) {
        ObjectMap<FctConstraints, String> map_;
        map_ = new ObjectMap<FctConstraints, String>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            boolean found_ = false;
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
                if (method_.isFinalMethod()) {
                    map_.put(e.getKey(), f);
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                continue;
            }
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
                if (method_.isNormalMethod()) {
                    map_.put(e.getKey(), f);
                    break;
                }
            }
        }
        return map_;
    }

    protected static void addClass(ObjectMap<FctConstraints, StringList> _map, FctConstraints _key, String _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
            _map.getVal(_key).removeDuplicates();
        } else {
            _map.put(_key, new StringList(_class));
        }
    }
}
