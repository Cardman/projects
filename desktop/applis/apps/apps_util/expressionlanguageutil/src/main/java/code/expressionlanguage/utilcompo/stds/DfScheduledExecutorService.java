package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.utilcompo.*;
import code.threads.AbstractThreadFactory;

public final class DfScheduledExecutorService implements DfInstancer {
    private final AbstractThreadFactory executorService;
    private final String id;

    public DfScheduledExecutorService(AbstractThreadFactory _e, String _i) {
        this.executorService = _e;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return build(_stackCall, id,executorService);
    }

    static ArgumentWrapper build(StackCall _stackCall, String _id, AbstractThreadFactory _th) {
        AbsLogDbg log_ = _stackCall.getStopper().getLogger();
        if (log_ != null) {
            log_.log(_id);
            return new ArgumentWrapper(new ScheduledExecutorServiceDbgStruct(_th));
        }
        return new ArgumentWrapper(new ScheduledExecutorServiceStruct(_th));
    }
}
