package code.serialize.exceptions;

public class BadObjectException extends RuntimeException {

    public BadObjectException() {
    }

    public BadObjectException(String _message) {
        super(_message);
    }

    public BadObjectException(Exception _e) {
        super(_e);
    }
}
