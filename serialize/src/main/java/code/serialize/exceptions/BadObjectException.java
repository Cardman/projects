package code.serialize.exceptions;

public class BadObjectException extends RuntimeException {

    public BadObjectException() {
    }

    public BadObjectException(String _message) {
        super(_message);
    }

    public BadObjectException(Throwable _e) {
        super(_e);
    }
}
