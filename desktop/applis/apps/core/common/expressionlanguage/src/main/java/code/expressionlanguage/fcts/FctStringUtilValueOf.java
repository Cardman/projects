package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringUtilValueOf implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct a_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        a_ = IndirectCalledFctUtil.processString(a_,_cont, _stackCall);
        if (_stackCall.getCallingState() == null) {
            return new ArgumentWrapper(a_);
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
