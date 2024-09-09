package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.ExecutorDbgServiceStruct;
import code.expressionlanguage.utilcompo.ExecutorServiceStruct;
import code.threads.AbstractThreadFactory;

public final class FctExecutorService1 implements StdCaller {
    private final AbstractInterceptor executorService;
    private final AbstractThreadFactory custAliases;
    private final String id;

    public FctExecutorService1(AbstractInterceptor _e, AbstractThreadFactory _c, String _id) {
        this.executorService = _e;
        custAliases = _c;
        id = _id;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct s_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        AbsLogDbg log_ = _stackCall.getStopper().getLogger();
        if (log_ != null) {
            log_.log(id +":"+NumParsers.convertToNumber(s_).intStruct());
            return new ArgumentWrapper(new ExecutorDbgServiceStruct(custAliases.newAtomicBoolean()));
        }
        return new ArgumentWrapper(new ExecutorServiceStruct(executorService,custAliases.newAtomicBoolean(), NumParsers.convertToNumber(s_).intStruct()));
    }
}
