package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.structs.Struct;

public final class ExecRunMethodState implements CallingState {
    private final Struct instance;

    public ExecRunMethodState(Struct _i) {
        this.instance = _i;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return new ExecRunMethodPageEl(instance);
    }
}
