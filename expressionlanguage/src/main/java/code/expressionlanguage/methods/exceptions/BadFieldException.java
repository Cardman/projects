package code.expressionlanguage.methods.exceptions;

public class BadFieldException extends RuntimeException {

    public BadFieldException() {
    }

    public BadFieldException(String _message) {
        super(_message);
    }
}
