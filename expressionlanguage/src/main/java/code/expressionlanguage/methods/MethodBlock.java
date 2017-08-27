package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.FctConstraints;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class MethodBlock extends NamedFunctionBlock {

    private final boolean staticMethod;

    private final boolean finalMethod;
    private final boolean abstractMethod;

    private final boolean normalMethod;

    private StringList overridenClasses;

    private StringList allOverridenClasses;

    private FctConstraints constraints;

    private final String declaringType;

    public MethodBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        String modifier_ = _el.getAttribute(ATTRIBUTE_MODIFIER);
        staticMethod = StringList.quickEq(modifier_, VALUE_STATIC);
        finalMethod = StringList.quickEq(modifier_, VALUE_FINAL);
        abstractMethod = StringList.quickEq(modifier_, VALUE_ABSTRACT);
        normalMethod = StringList.quickEq(modifier_, VALUE_NORMAL);
        overridenClasses = new StringList();
        allOverridenClasses = new StringList();
        declaringType = getRooted().getFullName();
    }

    public MethodModifier getModifier() {
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        if (finalMethod) {
            return MethodModifier.FINAL;
        }
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        return MethodModifier.NORMAL;
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = super.getClassNames();
        tr_.put(ATTRIBUTE_CLASS, getReturnType());
        return tr_;
    }

    public boolean isOverriding(MethodBlock _other) {
        if (!getId().eq(_other.getId())) {
            return false;
        }
        return allOverridenClasses.containsStr(_other.declaringType);
    }

    public String getDeclaringType() {
        return declaringType;
    }

    public StringList getOverridenClasses() {
        return overridenClasses;
    }

    public StringList getAllOverridenClasses() {
        return allOverridenClasses;
    }

    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(new ClassName(n_, i + 1 == len_ && isVarargs()));
        }
        return new MethodId(name_, pTypes_);
    }


    public boolean isConcreteInstanceDerivableMethod() {
        if (staticMethod) {
            return false;
        }
        if (finalMethod) {
            return false;
        }
        if (abstractMethod) {
            return false;
        }
        return true;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isFinalMethod() {
        return finalMethod;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public boolean isNormalMethod() {
        return normalMethod;
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
    public boolean isStaticContext() {
        return staticMethod;
    }

    @Override
    public String getTagName() {
        return TAG_METHOD;
    }

    @Override
    public RootBlock belong() {
        return (RootBlock) getParent();
    }

    @Override
    public FctConstraints getConstraints() {
        return constraints;
    }

    @Override
    public void setConstraints(FctConstraints _constraints) {
        constraints = _constraints;
    }
}
