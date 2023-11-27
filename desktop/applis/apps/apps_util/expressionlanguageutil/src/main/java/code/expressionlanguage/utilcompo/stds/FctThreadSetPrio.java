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
import code.expressionlanguage.utilcompo.AbsThreadStruct;

public final class FctThreadSetPrio implements StdCaller {

    private final String id;

    public FctThreadSetPrio(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        preCall(_stackCall, id+":"+((NumberStruct) _firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).intStruct());
        if (!((AbsThreadStruct) _instance).setPriority(((NumberStruct) _firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).intStruct())) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }

    public static void preCall(StackCall _stackCall, String _id) {
        if (_stackCall.getStopper().getLogger() != null) {
            _stackCall.getStopper().getLogger().log(_id);
        }
    }
}
