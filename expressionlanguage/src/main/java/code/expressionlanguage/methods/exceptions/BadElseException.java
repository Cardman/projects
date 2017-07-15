package code.expressionlanguage.methods.exceptions;

public class BadElseException extends RuntimeException {

    public BadElseException() {
    }

    public BadElseException(String _message) {
        super(_message);
    }

    public BadElseException(Throwable _cause) {
        super(_cause);
    }

    public BadElseException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
