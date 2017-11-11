package code.expressionlanguage.methods.exceptions;

public class BadCatchException extends RuntimeException {

    public BadCatchException() {
    }

    public BadCatchException(String _message) {
        super(_message);
    }
}
