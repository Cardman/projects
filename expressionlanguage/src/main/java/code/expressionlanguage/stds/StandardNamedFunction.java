package code.expressionlanguage.stds;

import code.expressionlanguage.methods.AccessEnum;
import code.util.StringList;

public abstract class StandardNamedFunction {

    private final String name;

    private final StringList parametersTypes;

    private final String returnType;

    private final AccessEnum access = AccessEnum.PUBLIC;

    private final boolean varargs;

    protected StandardNamedFunction(String _name, StringList _parametersTypes,
            String _returnType, boolean _varargs) {
        name = _name;
        parametersTypes = _parametersTypes;
        returnType = _returnType;
        varargs = _varargs;
    }

    public String getName() {
        return name;
    }

    public StringList getParametersTypes() {
        return parametersTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public AccessEnum getAccess() {
        return access;
    }

    public boolean isVarargs() {
        return varargs;
    }
}
