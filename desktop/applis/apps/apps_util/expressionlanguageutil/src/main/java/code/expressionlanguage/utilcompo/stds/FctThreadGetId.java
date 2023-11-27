package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsThreadStruct;
import code.threads.AbstractThread;

public final class FctThreadGetId implements StdCaller {

    private final String id;

    public FctThreadGetId(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        FctThreadSetPrio.preCall(_stackCall, id);
        AbstractThread thread_ = ((AbsThreadStruct) _instance).getThread();
        return new ArgumentWrapper(new LongStruct(thread_.getId()));
    }
}
