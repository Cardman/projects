package code.formathtml.exceptions;

import code.expressionlanguage.opers.util.Struct;

public class RenderingException extends RuntimeException {

    private final Struct custCause;
    public RenderingException(Struct _cause) {
        custCause = _cause;
    }

    public Struct getCustCause() {
        return custCause;
    }
}
