package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public interface CallingState {
    AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack);
}
