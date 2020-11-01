package code.bean.nat;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultFullStack;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.Struct;

public final class NativeContextEl extends ContextEl {
    public NativeContextEl(CommonExecutionInfos _executionInfos) {
        super(_executionInfos, InitPhase.READ_ONLY_OTHERS);
        setFullStack(new DefaultFullStack(this));
    }

    @Override
    public boolean callsOrException() {
        return getCallingState() instanceof CustomFoundExc;
    }
}
