package code.formathtml.exceptions;

import code.expressionlanguage.opers.util.Struct;

public class RenderingException extends RuntimeException {

    private Struct custCause;
    public RenderingException(Struct _cause) {
        super((Throwable)_cause.getInstance());
        custCause = _cause;
    }

    public Struct getCustCause() {
        return custCause;
    }
}
