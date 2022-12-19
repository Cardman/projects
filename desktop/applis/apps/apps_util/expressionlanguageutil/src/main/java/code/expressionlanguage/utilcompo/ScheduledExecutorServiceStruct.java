package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractScheduledExecutorService;
import code.threads.AbstractShutdownExecutorService;
import code.threads.AbstractThreadFactory;

public final class ScheduledExecutorServiceStruct extends WithoutParentIdStruct implements AbsExecutorServiceStruct {
    private final AbstractScheduledExecutorService executorService;

    public ScheduledExecutorServiceStruct(AbstractThreadFactory _e) {
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
        return new FutureStruct(executorService.scheduleAtFixedRate(_command,_initialDelay,_period));
    }

    public AbstractShutdownExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getCustAliases().getAliasScheduledExecutorService();
    }
}
