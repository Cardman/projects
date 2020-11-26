package code.expressionlanguage.stds;

import code.expressionlanguage.functionid.ConstructorId;
import code.util.StringList;

public final class StandardConstructor extends StandardNamedFunction {

    private static final String DEFAULT_NAME = "";

    public StandardConstructor(StringList _parametersTypes, boolean _varargs) {
        super(DEFAULT_NAME, _parametersTypes, DEFAULT_NAME, _varargs, new StringList());
    }
    public StandardConstructor(StringList _parametersTypes, boolean _varargs, StringList _paramNames) {
        super(DEFAULT_NAME, _parametersTypes, DEFAULT_NAME, _varargs,_paramNames);
    }

    public ConstructorId getId() {
        return new ConstructorId("", getImportedParametersTypes(), isVarargs());
    }
}
