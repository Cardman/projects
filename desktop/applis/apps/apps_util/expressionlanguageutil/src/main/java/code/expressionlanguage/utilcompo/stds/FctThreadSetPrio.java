package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ThreadStruct;

public final class FctThreadSetPrio implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (!((ThreadStruct) _instance).setPriority(((NumberStruct) _firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).intStruct())) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
