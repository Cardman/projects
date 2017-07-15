package code.expressionlanguage.methods.exceptions;

public class BadStaticException extends RuntimeException {

    public BadStaticException() {
    }

    public BadStaticException(String _message) {
        super(_message);
    }
}
