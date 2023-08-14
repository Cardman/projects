package code.expressionlanguage;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.threads.ConcreteBoolean;

public final class SingleInterruptedContextEl extends ContextEl {

    public SingleInterruptedContextEl(CommonExecutionInfos _executionInfos, ConcreteBoolean _conc) {
        super(_conc,_executionInfos);
    }
}
