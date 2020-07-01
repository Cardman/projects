package code.expressionlanguage.stds;

import code.util.StringList;

public abstract class StandardNamedFunction {

    private final String name;

    private final StringList parametersTypes;

    private final String returnType;

    private final boolean varargs;

    private final StandardType owner;

    protected StandardNamedFunction(String _name, StringList _parametersTypes,
            String _returnType, boolean _varargs, StandardType _owner) {
        name = _name;
        parametersTypes = _parametersTypes;
        returnType = _returnType;
        varargs = _varargs;
        owner = _owner;
    }

    public StandardType getOwner() {
        return owner;
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

    public final boolean isVarargs() {
        return varargs;
    }
}
