package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.threads.ConcreteBoolean;

public final class TestedContextEl extends ContextEl {
    public TestedContextEl(CommonExecutionInfos _executionInfos) {
        super(new ConcreteBoolean(),_executionInfos);
    }

    @Override
    public ContextEl copy(Struct _state) {
        return null;
    }

    @Override
    public ContextEl copy(AbstractAtomicBoolean _i, Struct _state) {
        return null;
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        return _stack.callsOrException();
    }
}
