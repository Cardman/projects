package code.bean.nat;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;

public final class NativeContextEl extends ContextEl {
    public NativeContextEl(CommonExecutionInfos _executionInfos) {
        super(_executionInfos);
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        return _stack.getCallingState() instanceof CustomFoundExc;
    }
}
