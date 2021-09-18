package code.expressionlanguage.stds;

import code.expressionlanguage.functionid.ConstructorId;
import code.util.StringList;

public final class StandardConstructor extends StandardNamedFunction {

    private static final String DEFAULT_NAME = "";

    private final StdCaller caller;
    public StandardConstructor(StringList _parametersTypes, boolean _varargs) {
        this(_parametersTypes, _varargs, new StringList(), null);
    }
    public StandardConstructor(StringList _parametersTypes, boolean _varargs, StringList _paramNames) {
        this(_parametersTypes, _varargs,_paramNames, null);
    }
    public StandardConstructor(StringList _parametersTypes, boolean _varargs, StdCaller _caller) {
        this(_parametersTypes, _varargs, new StringList(), _caller);
    }
    public StandardConstructor(StringList _parametersTypes, boolean _varargs, StringList _paramNames, StdCaller _caller) {
        super(DEFAULT_NAME, _parametersTypes, DEFAULT_NAME, _varargs,_paramNames);
        caller = _caller;
    }

    public StdCaller getCaller() {
        return caller;
    }

    public ConstructorId getId() {
        return new ConstructorId("", getImportedParametersTypes(), isVarargs());
    }
}
