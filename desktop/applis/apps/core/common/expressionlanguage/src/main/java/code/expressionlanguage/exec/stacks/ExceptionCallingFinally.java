package code.expressionlanguage.exec.stacks;

import code.expressionlanguage.exec.blocks.CallingFinally;
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
