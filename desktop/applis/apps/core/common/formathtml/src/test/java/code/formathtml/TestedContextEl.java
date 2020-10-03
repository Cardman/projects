package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultFullStack;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;

public final class TestedContextEl extends ContextEl {
    public TestedContextEl(CommonExecutionInfos _executionInfos) {
        super(_executionInfos, InitPhase.READ_ONLY_OTHERS);
        setFullStack(new DefaultFullStack(this));
    }

}
