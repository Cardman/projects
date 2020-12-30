package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public abstract class AbstractReflectElement implements CallingState {

    private final boolean lambda;

    public AbstractReflectElement(boolean _lambda) {
        lambda = _lambda;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createReflectMethod(this, _stack);
    }
    public abstract ReflectingType getReflect();

    public boolean isLambda() {
        return lambda;
    }

}
