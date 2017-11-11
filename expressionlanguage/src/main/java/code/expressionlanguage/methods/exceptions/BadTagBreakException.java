package code.expressionlanguage.methods.exceptions;

public class BadTagBreakException extends RuntimeException {

    public BadTagBreakException() {
    }

    public BadTagBreakException(String _message) {
        super(_message);
    }
}
