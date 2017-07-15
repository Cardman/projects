package code.expressionlanguage.exceptions;

public class NotStringException extends IllegalArgumentException {

    public NotStringException() {
    }

    public NotStringException(String _s) {
        super(_s);
    }

    public NotStringException(Throwable _cause) {
        super(_cause);
    }

    public NotStringException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
