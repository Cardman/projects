package code.expressionlanguage;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.threads.ConcreteBoolean;

public final class SingleContextEl extends ContextEl {

    public SingleContextEl(CommonExecutionInfos _executionInfos) {
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
