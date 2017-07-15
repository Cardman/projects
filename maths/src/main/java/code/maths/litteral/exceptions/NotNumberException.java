package code.maths.litteral.exceptions;

public class NotNumberException extends IllegalArgumentException {

    public NotNumberException() {
    }

    public NotNumberException(String _s) {
        super(_s);
    }

    public NotNumberException(Throwable _cause) {
        super(_cause);
    }

    public NotNumberException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
