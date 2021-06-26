package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public final class CallConstructorPageEl extends AbstractCallingInstancingPageEl {
    public CallConstructorPageEl(ExecFormattedRootBlock _global) {
        super(_global);
    }

    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_stack)) {
            return;
        }
        commonTageBase(_context,_stack,null);
    }

}
