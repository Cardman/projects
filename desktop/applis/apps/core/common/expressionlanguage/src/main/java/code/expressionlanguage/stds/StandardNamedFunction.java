package code.expressionlanguage.stds;

import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.functionid.Identifiable;
import code.util.StringList;

public abstract class StandardNamedFunction {

    private final String name;

    private final StringList parametersTypes;
    private final StringList parametersNames;

    private final String returnType;

    private final boolean varargs;

    private final StdCaller caller;

    protected StandardNamedFunction(String _name, StringList _parametersTypes,
                                    String _returnType, boolean _varargs, StringList _parametersNames, StdCaller _caller) {
        name = _name;
        parametersTypes = _parametersTypes;
        returnType = _returnType;
        varargs = _varargs;
        parametersNames = _parametersNames;
        caller = _caller;
    }

    public StdCaller getCaller() {
        return caller;
    }

    public String getName() {
        return name;
    }

    public StringList getImportedParametersTypes() {
        return parametersTypes;
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

    public abstract Identifiable id();
    public abstract String getSignature(DisplayedStrings _dis);
}
