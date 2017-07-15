package code.expressionlanguage.methods.exceptions;

public class BadTagBreakException extends RuntimeException {

    public BadTagBreakException() {
    }

    public BadTagBreakException(String _message) {
        super(_message);
    }

    public BadTagBreakException(Throwable _cause) {
        super(_cause);
    }

    public BadTagBreakException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
