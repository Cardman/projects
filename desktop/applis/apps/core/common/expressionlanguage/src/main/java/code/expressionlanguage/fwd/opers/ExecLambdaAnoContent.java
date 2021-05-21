package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class ExecLambdaAnoContent {
    private final MethodId method;
    private final ExecTypeFunction pair;
    public ExecLambdaAnoContent(AnaLambdaAnoContent _cont, ExecTypeFunction _pair) {
        method = _cont.getMethod();
        pair = _pair;
    }

    public MethodId getMethod() {
        return method;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }
}
