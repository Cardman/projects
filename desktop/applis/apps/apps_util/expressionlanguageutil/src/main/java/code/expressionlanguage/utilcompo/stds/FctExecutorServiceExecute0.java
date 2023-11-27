package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.stds.FctCompoInvokeLater;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ExecutorDbgServiceStruct;
import code.expressionlanguage.utilcompo.ExecutorServiceStruct;

public final class FctExecutorServiceExecute0 implements StdCaller {

    private final String id;

    public FctExecutorServiceExecute0(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct s_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(s_ instanceof Runnable)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (_instance instanceof ExecutorDbgServiceStruct) {
            AbsLogDbg log_ = _stackCall.getStopper().getLogger();
            if (((ExecutorDbgServiceStruct)_instance).getStopped().get()) {
                log_.log(id +":"+ s_.getClassName(_cont));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            FctCompoInvokeLater.procRunnable(_cont,_stackCall,log_,s_, id);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ((ExecutorServiceStruct)_instance).execute((Runnable) s_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
