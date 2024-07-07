package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;

public final class CloneQuickCall implements AbstractQuickCall {
    @Override
    public Argument calculate(AbstractRefectCommonMethodPageEl _current, ContextEl _context, StackCall _stack) {
        return ExecInvokingOperation.cloneArray(_current.getInstance(),_context, _stack);
    }
}
