package code.expressionlanguage.exceptions;

public class EmptyPartException extends RuntimeException {

    public EmptyPartException() {
    }

    public EmptyPartException(String _message) {
        super(_message);
    }
}
