package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ExecutorServiceStruct;
import code.threads.AbstractThreadFactory;

public final class FctExecutorService0 implements StdCaller {
    private final AbstractThreadFactory executorService;

    public FctExecutorService0(AbstractThreadFactory _e) {
        this.executorService = _e;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new ExecutorServiceStruct(executorService));
    }
}
