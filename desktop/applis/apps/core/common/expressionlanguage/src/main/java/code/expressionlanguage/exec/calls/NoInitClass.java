package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class NoInitClass implements AbstractInitClass {

    @Override
    public boolean koParent(AbstractLambdaVariable _lda, ContextEl _context, StackCall _stack) {
        return false;
    }

    @Override
    public boolean hasToExit(AbstractLambdaVariable _lda, ContextEl _context, StackCall _stack) {
        return false;
    }
}
