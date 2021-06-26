package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public abstract class DefPreparerNoAbs implements AbstractPreparer {

    @Override
    public boolean isAbstract(ContextEl _context, StackCall _stack) {
        return false;
    }
}
