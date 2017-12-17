package code.expressionlanguage.stds;

import code.util.StringList;

public final class StandardConstructor extends StandardNamedFunction {

    private static final String DEFAULT_NAME = "";

    protected StandardConstructor(StringList _parametersTypes, boolean _varargs) {
        super(DEFAULT_NAME, _parametersTypes, DEFAULT_NAME, _varargs);
    }

}
