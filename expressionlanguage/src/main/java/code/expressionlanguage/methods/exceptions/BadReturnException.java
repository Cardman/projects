package code.expressionlanguage.methods.exceptions;

public class BadReturnException extends RuntimeException {

    public BadReturnException() {
    }

    public BadReturnException(String _message) {
        super(_message);
    }
}
