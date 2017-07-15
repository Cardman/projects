package code.expressionlanguage.exceptions;

public class DynamicCastClassException extends RuntimeException {

    public DynamicCastClassException() {
    }

    public DynamicCastClassException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

    public DynamicCastClassException(String _message) {
        super(_message);
    }

    public DynamicCastClassException(Throwable _cause) {
        super(_cause);
    }

}
