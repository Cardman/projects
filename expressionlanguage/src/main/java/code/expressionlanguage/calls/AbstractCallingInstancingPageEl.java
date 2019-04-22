package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;


public abstract class AbstractCallingInstancingPageEl extends AbstractInstancingPageEl implements ForwardPageEl {

    @Override
    public final boolean forwardTo(AbstractPageEl _page, ContextEl _context) {
        Argument a_ = getGlobalArgument();
        return _page.receive(a_, _context);
    }
}
