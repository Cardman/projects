package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsExecutorServiceStruct;

public final class FctExecutorServiceShutdown implements StdCaller {

    private final String id;

    public FctExecutorServiceShutdown(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ((AbsExecutorServiceStruct)_instance).shutdown();
        AbsLogDbg log_ = _stackCall.getStopper().getLogger();
        if (log_ != null) {
            log_.log(id+":"+_instance.getClassName(_cont));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
