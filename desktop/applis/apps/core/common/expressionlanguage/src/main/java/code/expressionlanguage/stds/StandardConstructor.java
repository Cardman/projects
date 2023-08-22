package code.expressionlanguage.stds;

import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.StringList;

public final class StandardConstructor extends StandardNamedFunction {

    private static final String DEFAULT_NAME = "";

    public StandardConstructor(StringList _parametersTypes, boolean _varargs, StdCaller _caller) {
        this(_parametersTypes, _varargs, new StringList(), _caller);
    }
    public StandardConstructor(StringList _parametersTypes, boolean _varargs, StringList _paramNames, StdCaller _caller) {
        super(DEFAULT_NAME, _parametersTypes, DEFAULT_NAME, _varargs,_paramNames, _caller);
    }

    @Override
    public String getSignature(DisplayedStrings _dis) {
        return getId().getSignature(_dis);
    }

    public ConstructorId getId() {
        return new ConstructorId("", getImportedParametersTypes(), isVarargs());
    }
}
