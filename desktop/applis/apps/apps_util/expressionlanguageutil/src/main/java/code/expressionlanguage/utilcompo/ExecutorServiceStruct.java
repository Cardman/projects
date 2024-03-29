package code.expressionlanguage.utilcompo;

import code.expressionlanguage.guicompos.EventStruct;
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
        return new FutureStruct(executorService.submitLater(_command));
    }

    public Struct submit(Struct _command) {
        if (!(_command instanceof EventStruct)) {
            return NullStruct.NULL_VALUE;
        }
        return new FutureObjStruct(executorService.submitWrCallable((EventStruct) _command));
    }

    @Override
    public void shutdown() {
        getStopped().set(true);
        executorService.shutdown();
    }

}
