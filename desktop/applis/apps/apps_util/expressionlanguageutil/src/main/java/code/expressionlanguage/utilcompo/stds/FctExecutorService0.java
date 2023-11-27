package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.threads.AbstractThreadFactory;

public final class FctExecutorService0 implements StdCaller {
    private final AbstractInterceptor executorService;
    private final AbstractThreadFactory custAliases;
    private final String id;

    public FctExecutorService0(AbstractInterceptor _e, AbstractThreadFactory _c, String _i) {
        this.executorService = _e;
        custAliases = _c;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return DfExecutorService.build(_stackCall,id,custAliases,executorService);
    }
}
