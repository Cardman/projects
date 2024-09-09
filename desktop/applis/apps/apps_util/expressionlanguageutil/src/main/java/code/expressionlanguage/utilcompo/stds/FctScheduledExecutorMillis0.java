package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ScheduledExecutorServiceStruct;

public final class FctScheduledExecutorMillis0 implements StdCaller {

    private final String id;

    public FctScheduledExecutorMillis0(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (nothing(_cont, _instance, _firstArgs, _stackCall, id)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct s_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        Struct d_ = _firstArgs.getArgumentWrappers().get(1).getValue();
        Struct p_ = _firstArgs.getArgumentWrappers().get(2).getValue();
        return new ArgumentWrapper(((ScheduledExecutorServiceStruct)_instance).scheduleAtFixedRate((Runnable) s_, NumParsers.convertToNumber(d_).longStruct(),  NumParsers.convertToNumber(p_).longStruct()));
    }
    static boolean nothing(ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall, String _id) {
        Struct s_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        if (!(s_ instanceof Runnable)) {
            return true;
        }
        Struct d_ = _firstArgs.getArgumentWrappers().get(1).getValue();
        Struct p_ = _firstArgs.getArgumentWrappers().get(2).getValue();
        if (!(_instance instanceof ScheduledExecutorServiceStruct)) {
            FctThreadSetPrio.preCall(_stackCall, _id +":"+s_.getClassName(_cont)+","+NumParsers.convertToNumber(d_).longStruct()+","+NumParsers.convertToNumber(p_).longStruct());
            return true;
        }
        return false;
    }
}
