package code.expressionlanguage;

import code.expressionlanguage.exec.*;

public final class SingleContextEl extends ContextEl {

    public SingleContextEl(CommonExecutionInfos _executionInfos) {
        super(_executionInfos, InitPhase.READ_ONLY_OTHERS);
        setFullStack(new DefaultFullStack(this));
    }

}
