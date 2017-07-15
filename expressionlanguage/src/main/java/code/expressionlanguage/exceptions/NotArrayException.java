package code.expressionlanguage.exceptions;

public class NotArrayException extends IllegalArgumentException {

    public NotArrayException() {
    }

    public NotArrayException(String _s) {
        super(_s);
    }

    public NotArrayException(Throwable _cause) {
        super(_cause);
    }

    public NotArrayException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
