package code.expressionlanguage.exceptions;
import code.expressionlanguage.opers.util.Struct;

public abstract class IndirectException extends RuntimeException {

    private final Struct cause;

    public IndirectException() {
        cause = new Struct();
    }

    public IndirectException(String _message) {
        super(_message);
        cause = new Struct();
    }

    public IndirectException(Struct _cause) {
        cause = _cause;
    }

    public IndirectException(String _message, Struct _cause) {
        cause = _cause;
    }

    public Struct getCustCause() {
        return cause;
    }
}
