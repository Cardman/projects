package code.bean.nat;

import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.StringList;

public final class SpecNatMethod {

    private final MethodModifier modifier;

    private final String name;

    private final StringList parametersTypes;

    private final String returnType;

    private final boolean varargs;
    private final NatCaller caller;
    public SpecNatMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier) {
        this(_name,_parametersTypes,_returnType,_varargs,_modifier,null);
    }
    public SpecNatMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier, NatCaller _caller) {
        name = _name;
        parametersTypes = _parametersTypes;
        returnType = _returnType;
        varargs = _varargs;
        modifier = _modifier;
        caller = _caller;
    }

    public NatCaller getCaller() {
        return caller;
    }

    public MethodId getId() {
        String name_ = getName();
        return new MethodId(MethodId.getKind(modifier), name_, getImportedParametersTypes(), isVarargs());
    }

    public String getName() {
        return name;
    }

    public StringList getImportedParametersTypes() {
        return new StringList(parametersTypes);
    }

    public String getImportedReturnType() {
        return returnType;
    }

    public boolean isVarargs() {
        return varargs;
    }

}
