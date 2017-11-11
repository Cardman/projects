package code.expressionlanguage.methods.exceptions;

public class BadTagContinueException extends RuntimeException {

    public BadTagContinueException() {
    }

    public BadTagContinueException(String _message) {
        super(_message);
    }
}
