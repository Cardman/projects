package code.expressionlanguage.stds;

import code.util.StringList;

public abstract class StandardNamedFunction {

    private final String name;

    private final StringList parametersTypes;
    private final StringList parametersNames;

    private final String returnType;

    private final boolean varargs;

    protected StandardNamedFunction(String _name, StringList _parametersTypes,
                                    String _returnType, boolean _varargs, StringList _parametersNames) {
        name = _name;
        parametersTypes = _parametersTypes;
        returnType = _returnType;
        varargs = _varargs;
        parametersNames = _parametersNames;
    }

    public String getName() {
        return name;
    }

    public StringList getImportedParametersTypes() {
        return new StringList(parametersTypes);
    }

    public StringList getParametersNames() {
        return parametersNames;
    }

    public String getImportedReturnType() {
        return returnType;
    }

    public final boolean isVarargs() {
        return varargs;
    }
}
