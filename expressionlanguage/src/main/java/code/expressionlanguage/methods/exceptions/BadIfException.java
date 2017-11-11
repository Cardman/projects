package code.expressionlanguage.methods.exceptions;

public class BadIfException extends RuntimeException {

    public BadIfException() {
    }

    public BadIfException(String _message) {
        super(_message);
    }
}
