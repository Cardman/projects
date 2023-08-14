package code.expressionlanguage;

import code.expressionlanguage.exec.*;
import code.threads.ConcreteBoolean;

public final class SingleContextEl extends ContextEl {

    public SingleContextEl(CommonExecutionInfos _executionInfos) {
        super(new ConcreteBoolean(),_executionInfos);
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        return _stack.callsOrException();
    }
}
