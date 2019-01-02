package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;


public abstract class AbstractCallingInstancingPageEl extends AbstractInstancingPageEl implements ForwardPageEl {

    private Argument returnedArgument;

    public final Argument getReturnedArgument() {
        return returnedArgument;
    }

    @Override
    public final void setArgumentForConstructor() {
        returnedArgument = getGlobalArgument();
    }

    @Override
    public final boolean forwardTo(AbstractPageEl _page, ContextEl _context) {
        Argument a_ = getReturnedArgument();
        return _page.receive(a_, _context);
    }
}
