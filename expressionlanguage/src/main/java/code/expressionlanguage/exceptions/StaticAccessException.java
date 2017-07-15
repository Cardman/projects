package code.expressionlanguage.exceptions;

public class StaticAccessException extends RuntimeException {

    public StaticAccessException() {
    }

    public StaticAccessException(String _message) {
        super(_message);
    }

    public StaticAccessException(Throwable _cause) {
        super(_cause);
    }

    public StaticAccessException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
