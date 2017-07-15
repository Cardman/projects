package code.expressionlanguage.methods.exceptions;

public class BadIfException extends RuntimeException {

    public BadIfException() {
    }

    public BadIfException(String _message) {
        super(_message);
    }

    public BadIfException(Throwable _cause) {
        super(_cause);
    }

    public BadIfException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
