package code.expressionlanguage.exceptions;

public class NotComparableException extends IllegalArgumentException {

    public NotComparableException() {
    }

    public NotComparableException(String _s) {
        super(_s);
    }

    public NotComparableException(Throwable _cause) {
        super(_cause);
    }

    public NotComparableException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
