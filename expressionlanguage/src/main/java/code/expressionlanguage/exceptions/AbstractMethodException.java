package code.expressionlanguage.exceptions;

public class AbstractMethodException extends RuntimeException {

    public AbstractMethodException() {
    }

    public AbstractMethodException(String _message) {
        super(_message);
    }

    public AbstractMethodException(Throwable _cause) {
        super(_cause);
    }

    public AbstractMethodException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
