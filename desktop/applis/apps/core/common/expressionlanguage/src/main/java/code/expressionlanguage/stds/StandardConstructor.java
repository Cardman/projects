package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.StringList;

public final class StandardConstructor extends StandardNamedFunction implements GeneConstructor {

    private static final String DEFAULT_NAME = "";

    public StandardConstructor(StringList _parametersTypes, boolean _varargs) {
        super(DEFAULT_NAME, _parametersTypes, DEFAULT_NAME, _varargs);
    }

    @Override
    public ConstructorId getId() {
        return new ConstructorId("", getImportedParametersTypes(), isVarargs());
    }
}
