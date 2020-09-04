package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneCustStaticMethod;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.StringList;

public final class StandardMethod extends StandardNamedFunction implements GeneCustStaticMethod {

    private final MethodModifier modifier;

    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier) {
        super(_name, _parametersTypes, _returnType, _varargs);
        modifier = _modifier;
    }

    @Override
    public MethodId getId() {
        String name_ = getName();
        return new MethodId(MethodId.getKind(modifier), name_, getImportedParametersTypes(), isVarargs());
    }

    @Override
    public boolean isStaticMethod() {
        return modifier == MethodModifier.STATIC;
    }

    public MethodModifier getModifier() {
        return modifier;
    }

}
