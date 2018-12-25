package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;

public interface ReturnablePageEl {

    void postReturn(ContextEl _context);
    void setReturnedArgument(Argument _arg);
}
