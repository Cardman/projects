package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.structs.Struct;

public final class CustomFoundExc implements CallingState {
    private final Struct struct;

    public CustomFoundExc(Struct _struct) {
        struct = Argument.getNull(_struct);
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return null;
    }

    public Struct getStruct() {
        return struct;
    }
}
