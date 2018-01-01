package code.expressionlanguage.stds;

import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;

public final class StandardMethod extends StandardNamedFunction {

    private final MethodModifier modifier;

    private final String declaringType;

    public StandardMethod(String _name, StringList _parametersTypes,
            String _returnType, boolean _varargs, MethodModifier _modifier, String _declaringType) {
        super(_name, _parametersTypes, _returnType, _varargs);
        modifier = _modifier;
        declaringType = _declaringType;
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
        return new MethodId(isStaticMethod(), name_, pTypes_);
    }

    public boolean isStaticMethod() {
        return modifier == MethodModifier.STATIC;
    }

    public boolean isFinalMethod() {
        return modifier == MethodModifier.FINAL;
    }

    public boolean isAbstractMethod() {
        return modifier == MethodModifier.ABSTRACT;
    }

    public boolean isNormalMethod() {
        return modifier == MethodModifier.NORMAL;
    }

    public String getDeclaringType() {
        return declaringType;
    }
    public String getPrettyString() {
        return declaringType+"."+getId().getSignature()+":"+getReturnType()+" is "+modifier.name();
    }
}
