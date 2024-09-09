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
import code.expressionlanguage.utilcompo.ExecutorDbgServiceStruct;
import code.expressionlanguage.utilcompo.ExecutorServiceStruct;
import code.expressionlanguage.utilcompo.FutureDbgStruct;

public final class FctExecutorServiceSubmit0 implements StdCaller {

    private final String id;

    public FctExecutorServiceSubmit0(String _i) {
        this.id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct s_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        if (!(s_ instanceof Runnable)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (_instance instanceof ExecutorDbgServiceStruct) {
            AbsLogDbg log_ = _stackCall.getStopper().getLogger();
            log_.log(id+":"+s_.getClassName(_cont));
            if (((ExecutorDbgServiceStruct)_instance).getStopped().get()) {
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            return new ArgumentWrapper(new FutureDbgStruct(s_));
        }
        return new ArgumentWrapper(((ExecutorServiceStruct)_instance).submit((Runnable) s_));
    }
}
