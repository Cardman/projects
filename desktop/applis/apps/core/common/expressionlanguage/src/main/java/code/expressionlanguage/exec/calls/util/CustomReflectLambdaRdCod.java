package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;

public final class CustomReflectLambdaRdCod extends AbstractReflectElement {

    private final Argument argument;

    public CustomReflectLambdaRdCod(Argument _argument) {
        super(false);
        argument = _argument;
    }

    public Argument getArgument() {
        return argument;
    }

}
