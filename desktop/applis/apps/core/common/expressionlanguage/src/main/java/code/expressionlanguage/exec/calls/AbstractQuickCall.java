package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public interface AbstractQuickCall {
    Argument calculate(AbstractRefectCommonMethodPageEl _current, ContextEl _context, StackCall _stack);
}
