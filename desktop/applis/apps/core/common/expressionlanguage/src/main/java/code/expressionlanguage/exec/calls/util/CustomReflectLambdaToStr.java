package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.structs.Struct;

public final class CustomReflectLambdaToStr extends AbstractReflectElement {

    private final Struct argument;

    public CustomReflectLambdaToStr(Struct _argument) {
        super(false);
        argument = _argument;
    }

    public Struct getArgument() {
        return argument;
    }

}
