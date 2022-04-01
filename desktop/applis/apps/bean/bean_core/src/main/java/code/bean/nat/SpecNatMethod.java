package code.bean.nat;

import code.expressionlanguage.functionid.MethodModifier;
import code.util.StringList;

public final class SpecNatMethod {

    private final MethodModifier modifier;

    private final String name;

    private final StringList parametersTypes;

    private final String returnType;

    private final boolean varargs;
    private final NatCaller caller;

    public SpecNatMethod(String _name,
                          String _returnType, boolean _varargs, MethodModifier _modifier, NatCaller _caller) {
        name = _name;
        parametersTypes = new StringList();
        returnType = _returnType;
        varargs = _varargs;
        modifier = _modifier;
        caller = _caller;
    }

    public NatCaller getCaller() {
        return caller;
    }

    public String getName() {
        return name;
    }

    public String getImportedReturnType() {
        return returnType;
    }

}
