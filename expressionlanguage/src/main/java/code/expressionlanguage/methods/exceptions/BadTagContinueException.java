package code.expressionlanguage.methods.exceptions;

public class BadTagContinueException extends RuntimeException {

    public BadTagContinueException() {
    }

    public BadTagContinueException(String _message) {
        super(_message);
    }

    public BadTagContinueException(Throwable _cause) {
        super(_cause);
    }

    public BadTagContinueException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
