package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.utilcompo.ExecutorServiceStruct;
import code.threads.AbstractThreadFactory;

public final class DfExecutorService implements DfInstancer {
    private final AbstractThreadFactory executorService;

    public DfExecutorService(AbstractThreadFactory _e) {
        this.executorService = _e;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new ExecutorServiceStruct(executorService));
    }
}