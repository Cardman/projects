package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.StackCall;
import code.threads.AbstractAtomicBoolean;

public class InterruptibleContextEl extends ContextEl {

    private final AbstractAtomicBoolean interrupt;
    public InterruptibleContextEl(AbstractAtomicBoolean _i,CommonExecutionInfos _executionInfos) {
        super(_executionInfos);
        interrupt = _i;
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        return stopped() || super.callsOrException(_stack);
    }

    protected boolean stopped() {
        return interrupt.get();
    }

    public void stopJoinSleep() {
        interrupt();
    }
    public void interrupt() {
        interrupt.set(true);
    }

    public AbstractAtomicBoolean getInterrupt() {
        return interrupt;
    }
}
