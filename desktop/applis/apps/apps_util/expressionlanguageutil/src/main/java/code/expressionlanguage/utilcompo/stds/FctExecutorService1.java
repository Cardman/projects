package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.ExecutorServiceStruct;

public final class FctExecutorService1 implements StdCaller {
    private final AbstractInterceptor executorService;

    public FctExecutorService1(AbstractInterceptor _e) {
        this.executorService = _e;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct s_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        return new ArgumentWrapper(new ExecutorServiceStruct(executorService, NumParsers.convertToNumber(s_).intStruct()));
    }
}
