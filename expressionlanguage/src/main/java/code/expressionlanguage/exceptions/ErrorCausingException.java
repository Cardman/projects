package code.expressionlanguage.exceptions;
import code.expressionlanguage.opers.util.Struct;

public class ErrorCausingException extends IndirectException {

    public ErrorCausingException() {
    }

    public ErrorCausingException(String _message, Struct _cause) {
        super(_message, _cause);
    }

    public ErrorCausingException(String _message) {
        super(_message);
    }

    public ErrorCausingException(Struct _cause) {
        super(_cause);
    }

}
