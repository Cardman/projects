package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractThreadFactory;

public final class ExecutorServiceStruct extends WithoutParentIdStruct {
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

    public void shutdown() {
        executorService.shutdown();
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getCustAliases().getAliasExecutorService();
    }
}
