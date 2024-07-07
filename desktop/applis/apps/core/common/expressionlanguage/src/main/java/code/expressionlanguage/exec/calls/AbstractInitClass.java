package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public interface AbstractInitClass {
    boolean koParent(AbstractLambdaVariable _lda,ContextEl _context, StackCall _stack);
    boolean hasToExit(AbstractLambdaVariable _lda,ContextEl _context, StackCall _stack);
}
