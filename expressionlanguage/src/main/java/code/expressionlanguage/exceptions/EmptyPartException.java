package code.expressionlanguage.exceptions;

public class EmptyPartException extends RuntimeException {

    public EmptyPartException() {
    }

    public EmptyPartException(String _message) {
        super(_message);
    }

    public EmptyPartException(Throwable _cause) {
        super(_cause);
    }

    public EmptyPartException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
