package code.expressionlanguage.methods.exceptions;

public class BadTryException extends RuntimeException {

    public BadTryException() {
    }

    public BadTryException(String _message) {
        super(_message);
    }
}
