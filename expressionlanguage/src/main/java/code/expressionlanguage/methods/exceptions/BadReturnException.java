package code.expressionlanguage.methods.exceptions;

public class BadReturnException extends RuntimeException {

    public BadReturnException() {
    }

    public BadReturnException(String _message) {
        super(_message);
    }

    public BadReturnException(Throwable _cause) {
        super(_cause);
    }

    public BadReturnException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
