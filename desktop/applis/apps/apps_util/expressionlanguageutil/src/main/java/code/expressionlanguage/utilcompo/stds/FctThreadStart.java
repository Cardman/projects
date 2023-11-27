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
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsThreadStruct;

public final class FctThreadStart implements StdCaller {
    private final String aliasIllegalThreadStateException;
    private final String id;

    public FctThreadStart(String _aliasIllegalThreadStateException, String _i) {
        this.aliasIllegalThreadStateException = _aliasIllegalThreadStateException;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        FctThreadSetPrio.preCall(_stackCall, id+":"+((AbsThreadStruct) _instance).getRunnable().getClassName(_cont));
        if (!((AbsThreadStruct) _instance).start()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, aliasIllegalThreadStateException, _stackCall)));
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
