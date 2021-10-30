package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ThreadSetStruct;

public final class FctThreadsetContains implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        return new ArgumentWrapper(ins_.contains(arg_));
    }
}
