package code.expressionlanguage.stacks;

import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.structs.Struct;

public final class ExceptionCallingFinally extends AbruptCallingFinally {
    private final Struct exception;
    public ExceptionCallingFinally(CallingFinally _callingFinally, Struct _exception) {
        super(_callingFinally);
        exception = _exception;
    }

    public Struct getException() {
        return exception;
    }
}
