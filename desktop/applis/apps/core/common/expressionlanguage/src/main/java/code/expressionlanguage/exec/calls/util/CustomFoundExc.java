package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.structs.Struct;

public final class CustomFoundExc implements CallingState {
    private final Struct struct;
    private final boolean failInit;

    public CustomFoundExc(Struct _struct) {
        this(_struct,false);
    }

    public CustomFoundExc(Struct _struct, StackCall _stack) {
        this(_struct,_stack.isFailInit());
    }

    public CustomFoundExc(Struct _struct, boolean _fail) {
        struct = Argument.getNull(_struct);
        failInit = _fail;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return null;
    }

    public Struct getStruct() {
        return struct;
    }

    public boolean isFailInit() {
        return failInit;
    }
}
