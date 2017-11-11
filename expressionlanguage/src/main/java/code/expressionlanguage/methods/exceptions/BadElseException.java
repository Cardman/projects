package code.expressionlanguage.methods.exceptions;

public class BadElseException extends RuntimeException {

    public BadElseException() {
    }

    public BadElseException(String _message) {
        super(_message);
    }
}
