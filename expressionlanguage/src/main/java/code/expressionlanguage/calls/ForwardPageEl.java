package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;

public interface ForwardPageEl extends ReturnablePageEl {

    boolean forwardTo(AbstractPageEl _page, ContextEl _context);

    Argument getReturnedArgument();

    void setReturnedArgument(Argument _returnedArgument);
}
