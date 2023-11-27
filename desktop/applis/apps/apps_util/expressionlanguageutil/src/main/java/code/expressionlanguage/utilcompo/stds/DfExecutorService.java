package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.ExecutorDbgServiceStruct;
import code.expressionlanguage.utilcompo.ExecutorServiceStruct;
import code.threads.AbstractThreadFactory;

public final class DfExecutorService implements DfInstancer {
    private final AbstractInterceptor executorService;
    private final AbstractThreadFactory custAliases;
    private final String id;

    public DfExecutorService(AbstractInterceptor _e, AbstractThreadFactory _c, String _i) {
        this.executorService = _e;
        custAliases = _c;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return build(_stackCall, id, custAliases, executorService);
    }

    static ArgumentWrapper build(StackCall _stackCall, String _id, AbstractThreadFactory _th, AbstractInterceptor _interc) {
        AbsLogDbg log_ = _stackCall.getStopper().getLogger();
        if (log_ != null) {
            log_.log(_id);
            return new ArgumentWrapper(new ExecutorDbgServiceStruct(_th.newAtomicBoolean()));
        }
        return new ArgumentWrapper(new ExecutorServiceStruct(_interc, _th.newAtomicBoolean()));
    }
}
