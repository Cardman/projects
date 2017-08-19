package code.expressionlanguage.methods;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;

public final class InterfaceBlock extends BracedBlock implements RootedBlock {

    private final String name;

    private final String packageName;

    private final StringList superInterfaces;

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final ObjectMap<FctConstraints, String> defaultMethods = new ObjectMap<FctConstraints, String>();

    private final AccessEnum access;

    public InterfaceBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        name = _el.getAttribute(ATTRIBUTE_NAME);
        packageName = _el.getAttribute(ATTRIBUTE_PACKAGE);
        superInterfaces = new StringList();
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_CLASS+i_)) {
            superInterfaces.add(_el.getAttribute(ATTRIBUTE_CLASS+i_));
            i_++;
        }
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }
    
    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    public ObjectMap<FctConstraints, StringList> getAllSignatures(Classes _classes) {
        ObjectMap<FctConstraints, StringList> map_;
        map_ = new ObjectMap<FctConstraints, StringList>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                map_.put(method_.getConstraints(), new StringList(getFullName()));
            }
        }
        for (String s: getAllSuperClasses()) {
            InterfaceBlock b_ = (InterfaceBlock) _classes.getClassBody(s);
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
            StringList overriding_ = new StringList(e.getValue());
            StringList overriden_ = new StringList();
            for (String f: e.getValue()) {
                boolean add_ = false;
                for (String g: e.getValue()) {
                    if (StringList.quickEq(f, g)) {
                        continue;
                    }
                    InterfaceBlock j_ = (InterfaceBlock) _classes.getClassBody(g);
                    if (j_.getAllSuperClasses().containsStr(f)) {
                        add_ = true;
                        break;
                    }
                }
                if (add_) {
                    overriden_.add(f);
                }
            }
            overriding_.removeAllElements(overriden_);
            overriding_.removeDuplicates();
            map_.put(e.getKey(), overriding_);
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
                String subType_ = sub_.getReturnType();
                for (String s: e.getValue()) {
                    MethodBlock sup_ = _classes.getMethodBody(s, cst_);
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
            if (PrimitiveTypeUtil.getSubslass(classes_, _classes).isEmpty()) {
                for (String c: classes_) {
                    addClass(output_, e.getKey(), c);
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
            int nbAbs_ = 0;
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
                if (method_.isNormalMethod()) {
                    nbConcrete_++;
                } else {
                    nbAbs_++;
                }
            }
            if (nbConcrete_ != 1 || nbAbs_ != 0) {
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
    private static void addClass(ObjectMap<FctConstraints, StringList> _map, FctConstraints _key, String _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
            _map.getVal(_key).removeDuplicates();
        } else {
            _map.put(_key, new StringList(_class));
        }
    }
    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        int i_ = 0;
        for (String t: superInterfaces) {
            tr_.put(ATTRIBUTE_CLASS+i_, t);
            i_++;
        }
        return tr_;
    }

    @Override
    public String getFullName() {
        return packageName+DOT+name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_INTERFACE;
    }

    @Override
    public StringList getDirectSuperClasses() {
        StringList classes_ = new StringList(superInterfaces);
        if (superInterfaces.isEmpty()) {
            classes_.add(Object.class.getName());
        }
        return classes_;
    }

    @Override
    public RootedBlock belong() {
        return this;
    }

    @Override
    public boolean isFinalType() {
        return false;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public boolean mustImplement() {
        return false;
    }

    @Override
    public ObjectMap<FctConstraints, String> getDefaultMethods() {
        return defaultMethods;
    }

    @Override
    public StringList getAllInterfaces() {
        return getAllSuperClasses();
    }
}
