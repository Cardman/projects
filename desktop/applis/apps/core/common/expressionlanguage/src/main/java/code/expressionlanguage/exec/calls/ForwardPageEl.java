package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public interface ForwardPageEl {

    void forwardTo(AbstractPageEl _page, ContextEl _context, StackCall _stack);
}
