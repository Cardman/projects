package code.expressionlanguage.methods.exceptions;

public class BadElseIfException extends RuntimeException {

    public BadElseIfException() {
    }

    public BadElseIfException(String _message) {
        super(_message);
    }

    public BadElseIfException(Throwable _cause) {
        super(_cause);
    }

    public BadElseIfException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
