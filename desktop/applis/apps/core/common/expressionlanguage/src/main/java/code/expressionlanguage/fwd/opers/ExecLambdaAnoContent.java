package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;

public final class ExecLambdaAnoContent {
    private final ClassMethodId method;
    private final ExecRootBlock declaring;
    public ExecLambdaAnoContent(AnaLambdaAnoContent _cont, AnalyzedPageEl _page) {
        method = _cont.getMethod();
        declaring = _page.getMapMembers().getValue(_cont.getRootNumber()).getRootBlock();
    }

    public ClassMethodId getMethod() {
        return method;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }
}
