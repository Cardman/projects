package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class AllParamReflectCheckerStepping implements AbstractParamReflectCheckerStepping {
    @Override
    public boolean checkParams(AbstractRefectCommonMethodPageEl _page, ContextEl _context, StackCall _stack) {
        return _page.checkParams(_context,_stack);
    }

    @Override
    public boolean postArg(AbstractRefectCommonMethodPageEl _page, StackCall _stack) {
        return _page.postArg(_stack);
    }
}
