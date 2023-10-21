package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.*;

public final class ExecutorServiceStruct extends WithoutParentIdStruct implements AbsExecutorServiceStruct {
    private final AbstractBaseExecutorServiceParam<Struct> executorService;

    public ExecutorServiceStruct(AbstractInterceptor _e) {
        this.executorService = _e.newExecutorService();
    }

    public ExecutorServiceStruct(AbstractInterceptor _e, int _nbThreads) {
        this.executorService = _e.newExecutorService(_nbThreads);
    }

    public void execute(Runnable _command) {
        executorService.execute(_command);
    }

    public Struct submit(Runnable _command) {
        return new FutureStruct(executorService.submit(_command));
    }

    public Struct submit(AbstractInterceptor _i, Struct _command) {
        if (!(_command instanceof StructCallable)) {
            return NullStruct.NULL_VALUE;
        }
        return new FutureObjStruct(executorService.submitCallable(_i.wrap((StructCallable) _command)));
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
