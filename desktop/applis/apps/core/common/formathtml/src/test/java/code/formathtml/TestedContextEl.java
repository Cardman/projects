package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.StackCall;
import code.threads.ConcreteBoolean;

public final class TestedContextEl extends ContextEl {
    public TestedContextEl(CommonExecutionInfos _executionInfos) {
        super(new ConcreteBoolean(),_executionInfos);
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        return _stack.callsOrException();
    }
}
