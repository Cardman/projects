package code.expressionlanguage.exceptions;

public class VoidArgumentException extends RuntimeException {

    public VoidArgumentException() {
    }

    public VoidArgumentException(String _message) {
        super(_message);
    }
}
