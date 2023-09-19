package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.structs.Struct;

public final class CustomReflectLambdaQuick extends AbstractReflectElement {
    private final Struct lambda;
    public CustomReflectLambdaQuick(Struct _l) {
        super(true);
        lambda = _l;
    }

    public Struct getLambda() {
        return lambda;
    }
}
