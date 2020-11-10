package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ExecLambdaAnoContent {
    private final ClassMethodId method;
    public ExecLambdaAnoContent(AnaLambdaAnoContent _cont) {
        method = _cont.getMethod();
    }

    public ClassMethodId getMethod() {
        return method;
    }
}
