package code.expressionlanguage.exceptions;

public class BadNumberArgumentException extends RuntimeException {

    public BadNumberArgumentException() {
    }

    public BadNumberArgumentException(String _message) {
        super(_message);
    }

    public BadNumberArgumentException(Throwable _cause) {
        super(_cause);
    }

    public BadNumberArgumentException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
