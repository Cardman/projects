package code.maths.litteral.exceptions;

public class NotEqualableException extends IllegalArgumentException {

    public NotEqualableException() {
    }

    public NotEqualableException(String _s) {
        super(_s);
    }

    public NotEqualableException(Throwable _cause) {
        super(_cause);
    }

    public NotEqualableException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
