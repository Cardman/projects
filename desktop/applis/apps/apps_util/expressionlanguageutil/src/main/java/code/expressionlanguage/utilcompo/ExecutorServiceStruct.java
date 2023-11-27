package code.expressionlanguage.utilcompo;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractBaseExecutorServiceParam;

public final class ExecutorServiceStruct extends AbsExecutorServiceImplStruct {
    private final AbstractBaseExecutorServiceParam<Struct> executorService;

    public ExecutorServiceStruct(AbstractInterceptor _e, AbstractAtomicBoolean _shut) {
        super(_shut);
        this.executorService = _e.newExecutorService();
    }

    public ExecutorServiceStruct(AbstractInterceptor _e, AbstractAtomicBoolean _shut, int _nbThreads) {
        super(_shut);
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

    @Override
    public void shutdown() {
        getStopped().set(true);
        executorService.shutdown();
    }

}
