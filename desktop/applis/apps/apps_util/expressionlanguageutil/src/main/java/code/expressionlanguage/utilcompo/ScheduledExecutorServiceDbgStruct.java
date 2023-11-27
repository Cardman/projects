package code.expressionlanguage.utilcompo;

import code.threads.AbstractThreadFactory;

public final class ScheduledExecutorServiceDbgStruct extends AbsScheduledExecutorServiceStruct {

    public ScheduledExecutorServiceDbgStruct(AbstractThreadFactory _e) {
        super(_e);
    }

    @Override
    public void shutdown() {
        getStopped().set(true);
    }
}
