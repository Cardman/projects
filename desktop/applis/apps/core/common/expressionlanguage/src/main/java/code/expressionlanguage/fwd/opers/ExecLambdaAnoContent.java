package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class ExecLambdaAnoContent {
    private final MethodId method;
    private final ExecTypeFunction pair;
    private final MethodModifier modifier;
    public ExecLambdaAnoContent(AnaLambdaAnoContent _cont, ExecTypeFunction _pair) {
        method = _cont.getMethod();
        pair = _pair;
        modifier = ExecLambdaMethodContent.modif(_cont.getMethod());
    }

    public MethodModifier getModifier() {
        return modifier;
    }

    public MethodId getMethod() {
        return method;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }
}
