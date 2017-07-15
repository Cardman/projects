package code.expressionlanguage.methods.exceptions;

public class BadTryException extends RuntimeException {

    public BadTryException() {
    }

    public BadTryException(String _message) {
        super(_message);
    }

    public BadTryException(Throwable _cause) {
        super(_cause);
    }

    public BadTryException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
