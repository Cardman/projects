package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;

public interface AbstractQuickCall {
    Struct calculate(AbstractRefectCommonMethodPageEl _current, ContextEl _context, StackCall _stack);
}
