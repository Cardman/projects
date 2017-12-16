package code.expressionlanguage.exceptions;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.Struct;

public abstract class IndirectException extends RuntimeException {

    private final Struct cause;

    public IndirectException() {
        cause = NullStruct.NULL_VALUE;
    }

    public IndirectException(String _message) {
        super(_message);
        cause = NullStruct.NULL_VALUE;
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
