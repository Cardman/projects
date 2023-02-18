package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractShutdownExecutorService;
import code.threads.AbstractThreadFactory;

public final class ExecutorServiceStruct extends WithoutParentIdStruct implements AbsExecutorServiceStruct {
    private final AbstractBaseExecutorService executorService;

    public ExecutorServiceStruct(AbstractThreadFactory _e) {
        this.executorService = _e.newExecutorService();
    }

    public ExecutorServiceStruct(AbstractThreadFactory _e, int _nbThreads) {
        this.executorService = _e.newExecutorService(_nbThreads);
    }

    public void execute(Runnable _command) {
        executorService.execute(_command);
    }

    public Struct submit(Runnable _command) {
        return new FutureStruct(executorService.submit(_command));
    }

    public static void shutdown(AbstractShutdownExecutorService _ex) {
        _ex.shutdown();
    }

    public AbstractShutdownExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasExecutorService();
    }
}
