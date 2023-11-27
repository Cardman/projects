package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractScheduledExecutorService;
import code.threads.AbstractThreadFactory;

public final class ScheduledExecutorServiceStruct extends WithoutParentIdStruct implements AbsExecutorServiceStruct {
    private final AbstractScheduledExecutorService executorService;
    private final AbstractAtomicBoolean stopped;

    public ScheduledExecutorServiceStruct(AbstractThreadFactory _e) {
        this.executorService = _e.newScheduledExecutorService();
        stopped = _e.newAtomicBoolean();
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

    public AbstractAtomicBoolean getStopped() {
        return stopped;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasScheduledExecutorService();
    }
}
