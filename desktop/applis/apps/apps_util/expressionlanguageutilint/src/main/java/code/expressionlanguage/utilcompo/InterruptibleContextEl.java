package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.threads.AbstractAtomicBoolean;

public class InterruptibleContextEl extends ContextEl {

    public InterruptibleContextEl(AbstractAtomicBoolean _i,CommonExecutionInfos _executionInfos) {
        super(_i,_executionInfos);
    }

    public void stopJoinSleep() {
        interrupt();
    }
    public void interrupt() {
        getInterrupt().set(true);
    }

}
