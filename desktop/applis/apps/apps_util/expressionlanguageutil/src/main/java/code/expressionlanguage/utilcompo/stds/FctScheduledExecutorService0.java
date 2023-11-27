package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractThreadFactory;

public final class FctScheduledExecutorService0 implements StdCaller {
    private final AbstractThreadFactory executorService;
    private final String id;

    public FctScheduledExecutorService0(AbstractThreadFactory _e, String _i) {
        this.executorService = _e;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return DfScheduledExecutorService.build(_stackCall, id,executorService);
    }
}
