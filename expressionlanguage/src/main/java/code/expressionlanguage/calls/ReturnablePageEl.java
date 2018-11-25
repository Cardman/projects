package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;

public interface ReturnablePageEl {

    void postReturn(ContextEl _context);
    void setReturnedArgument();
}
