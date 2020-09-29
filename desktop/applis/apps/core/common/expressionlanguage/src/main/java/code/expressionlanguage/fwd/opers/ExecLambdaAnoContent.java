package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.Forwards;

public final class ExecLambdaAnoContent {
    private final ClassMethodId method;
    private final ExecRootBlock declaring;
    public ExecLambdaAnoContent(AnaLambdaAnoContent _cont, Forwards _forwards) {
        method = _cont.getMethod();
        declaring = _forwards.getMapMembers().getValue(_cont.getRootNumber()).getRootBlock();
    }

    public ClassMethodId getMethod() {
        return method;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }
}
