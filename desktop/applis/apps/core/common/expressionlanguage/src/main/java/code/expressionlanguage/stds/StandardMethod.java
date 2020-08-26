package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneCustStaticMethod;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.StringList;

public final class StandardMethod extends StandardNamedFunction implements GeneCustStaticMethod {

    private final MethodModifier modifier;

    public StandardMethod(String _name, StringList _parametersTypes,
            String _returnType, boolean _varargs, MethodModifier _modifier, StandardType _type) {
        super(_name, _parametersTypes, _returnType, _varargs, _type);
        modifier = _modifier;
    }

    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodId.getKind(modifier), name_, pTypes_, isVarargs());
    }

    @Override
    public boolean isStaticMethod() {
        return modifier == MethodModifier.STATIC;
    }

    public MethodModifier getModifier() {
        return modifier;
    }

}
