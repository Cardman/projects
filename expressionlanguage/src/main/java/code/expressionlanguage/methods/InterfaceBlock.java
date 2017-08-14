package code.expressionlanguage.methods;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
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
                    if (map_.contains(method_.getConstraints())) {
                        map_.getVal(method_.getConstraints()).add(s);
                        map_.getVal(method_.getConstraints()).removeDuplicates();
                    } else {
                        map_.put(method_.getConstraints(), new StringList(s));
                    }
                }
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
            map_.put(e.getKey(), overriding_);
        }
        return map_;
    }
    public boolean areCompatible(ObjectMap<FctConstraints, StringList> _methodIds, Classes _classes) {
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            FctConstraints cst_ = e.getKey();
            if (e.getValue().containsStr(getFullName())) {
                //overridden by this interface
                MethodBlock sub_ = _classes.getMethodBody(getFullName(), cst_);
                String subType_ = sub_.getReturnType();
                for (String s: e.getValue()) {
                    MethodBlock sup_ = _classes.getMethodBody(s, cst_);
                    String supType_ = sup_.getReturnType();
                    if (StringList.quickEq(supType_, subType_)) {
                        continue;
                    }
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(supType_, subType_, _classes)) {
                        return false;
                    }
                }
                continue;
            }
            StringList classes_ = e.getValue();
            int len_ = classes_.size();
            for (int i = 0; i < len_; i++) {
                String one_ = classes_.get(i);
                String retOne_ = _classes.getMethodBody(one_, cst_).getReturnType();
                for (int j = 0; j < len_; j++) {
                    String two_ = classes_.get(j);
                    String retTwo_ = _classes.getMethodBody(two_, cst_).getReturnType();
                    if (StringList.quickEq(retOne_, retTwo_)) {
                        continue;
                    }
                    if (PrimitiveTypeUtil.canBeUseAsArgument(retOne_, retTwo_, _classes)) {
                        continue;
                    }
                    if (PrimitiveTypeUtil.canBeUseAsArgument(retTwo_, retOne_, _classes)) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static EqList<FctConstraints> remainingMethodsToImplement(
            ObjectMap<FctConstraints, StringList> _methodIds,
            Classes _classes) {
        EqList<FctConstraints> rem_ = new EqList<FctConstraints>();
        for (EntryCust<FctConstraints, StringList> e: _methodIds.entryList()) {
            int nbConcrete_ = 0;
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
                if (method_.isNormalMethod()) {
                    nbConcrete_++;
                }
            }
            if (nbConcrete_ != 1) {
                rem_.add(e.getKey());
                continue;
            }
            String subType_ = EMPTY_STRING;
            for (String f: e.getValue()) {
                MethodBlock method_ = _classes.getMethodBody(f, e.getKey());
                if (method_.isNormalMethod()) {
                    subType_ = method_.getReturnType();
                    break;
                }
            }
            for (String f: e.getValue()) {
                MethodBlock superMethod_ = _classes.getMethodBody(f, e.getKey());
                String supType_ = superMethod_.getReturnType();
                if (StringList.quickEq(supType_, subType_)) {
                    continue;
                }
                if (!PrimitiveTypeUtil.canBeUseAsArgument(supType_, subType_, _classes)) {
                    rem_.add(e.getKey());
                    break;
                }
            }
        }
        return rem_;
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
}
