package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ScheduledExecutorServiceStruct;

public final class FctScheduledExecutorNanos0 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct s_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        Struct d_ = _firstArgs.getArgumentWrappers().get(1).getValue().getStruct();
        Struct p_ = _firstArgs.getArgumentWrappers().get(2).getValue().getStruct();
        return new ArgumentWrapper(((ScheduledExecutorServiceStruct)_instance).scheduleAtFixedRateNanos((Runnable) s_, NumParsers.convertToNumber(d_).longStruct(),  NumParsers.convertToNumber(p_).longStruct()));
    }
}
