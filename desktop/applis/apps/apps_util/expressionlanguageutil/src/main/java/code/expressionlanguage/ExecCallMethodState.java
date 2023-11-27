package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsDbgFutureStruct;

public final class ExecCallMethodState implements CallingState {
    private final AbsDbgFutureStruct future;
    private final Struct instance;

    public ExecCallMethodState(AbsDbgFutureStruct _f,Struct _i) {
        this.future = _f;
        this.instance = _i;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return new ExecCallMethodPageEl(future,instance);
    }
}
