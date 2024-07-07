package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class FieldInitClass implements AbstractInitClass {
    private final ReflectFieldContent ref;

    public FieldInitClass(ReflectFieldContent _r) {
        this.ref = _r;
    }

    @Override
    public boolean koParent(AbstractLambdaVariable _lda, ContextEl _context, StackCall _stack) {
        return ref.koParent(_context, _stack);
    }

    @Override
    public boolean hasToExit(AbstractLambdaVariable _lda, ContextEl _context, StackCall _stack) {
        return ref.hasToExit(_context, _stack);
    }
}
