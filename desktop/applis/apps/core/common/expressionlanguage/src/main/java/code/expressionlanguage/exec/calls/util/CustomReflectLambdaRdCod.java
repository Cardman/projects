package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.structs.Struct;

public final class CustomReflectLambdaRdCod extends AbstractReflectElement {

    private final Struct argument;

    public CustomReflectLambdaRdCod(Struct _argument) {
        super(false);
        argument = _argument;
    }

    public Struct getArgument() {
        return argument;
    }

}
