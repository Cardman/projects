package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;

public final class CustomReflectLambdaToStr extends AbstractReflectElement {

    private final Argument argument;

    public CustomReflectLambdaToStr(Argument _argument) {
        super(false);
        argument = _argument;
    }

    public Argument getArgument() {
        return argument;
    }

    @Override
    public ReflectingType getReflect() {
        return ReflectingType.TO_STR;
    }
}
