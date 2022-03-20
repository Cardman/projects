package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.util.ArgumentListCall;

public final class CustomReflectLambdaVarMethod extends AbstractReflectElement {

    private final ReflectingType reflect;

    private final ArgumentListCall array;

    public CustomReflectLambdaVarMethod(ReflectingType _reflect,
                                        ArgumentListCall _array) {
        super(true);
        reflect = _reflect;
        array = _array;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public ArgumentListCall getArray() {
        return array;
    }

}
