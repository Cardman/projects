package code.maths.litteral.exceptions;

public class NotSetException extends IllegalArgumentException {

    public NotSetException() {
    }

    public NotSetException(String _s) {
        super(_s);
    }

    public NotSetException(Throwable _cause) {
        super(_cause);
    }

    public NotSetException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
