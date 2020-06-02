package code.formathtml.stacks;

import code.expressionlanguage.structs.Struct;
import code.formathtml.RendCallingFinally;

public final class RendExceptionCallingFinally extends RendAbruptCallingFinally {
    private final Struct exception;
    public RendExceptionCallingFinally(RendCallingFinally _callingFinally, Struct _exception) {
        super(_callingFinally);
        exception = _exception;
    }

    public Struct getException() {
        return exception;
    }
}
