package code.expressionlanguage.utilcompo;

import code.expressionlanguage.structs.Struct;
import code.threads.AbstractScheduledExecutorService;
import code.threads.AbstractThreadFactory;

public final class ScheduledExecutorServiceStruct extends AbsScheduledExecutorServiceStruct {
    private final AbstractScheduledExecutorService executorService;

    public ScheduledExecutorServiceStruct(AbstractThreadFactory _e) {
        super(_e);
        this.executorService = _e.newScheduledExecutorService();
    }

    public Struct scheduleAtFixedRate(Runnable _command,
                                      long _initialDelay,
                                      long _period) {
        return new FutureStruct(executorService.scheduleAtFixedRate(_command,_initialDelay,_period));
    }
    public Struct scheduleAtFixedRateNanos(Runnable _command,
                                      long _initialDelay,
                                      long _period) {
        return new FutureStruct(executorService.scheduleAtFixedRateNanos(_command,_initialDelay,_period));
    }

    @Override
    public void shutdown() {
        getStopped().set(true);
        executorService.shutdown();
    }
}
