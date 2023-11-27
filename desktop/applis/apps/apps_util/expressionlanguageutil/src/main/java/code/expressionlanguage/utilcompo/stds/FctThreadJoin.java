package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsThreadStruct;
import code.threads.ThState;

public final class FctThreadJoin implements StdCaller {

    private final String intro;

    public FctThreadJoin(String _i) {
        intro = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return toResult(((AbsThreadStruct) _instance).joinThread(_cont,_stackCall, intro+":"+((AbsThreadStruct) _instance).getRunnable().getClassName(_cont)));
    }

    public static ArgumentWrapper toResult(ThState _state) {
        if (_state == ThState.INTERRUPTED) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(BooleanStruct.of(_state == ThState.ALIVE));
    }
}
