package code.expressionlanguage.methods.exceptions;

public class BadCaseException extends RuntimeException {

    public BadCaseException() {
    }

    public BadCaseException(String _message) {
        super(_message);
    }

    public BadCaseException(Throwable _cause) {
        super(_cause);
    }

    public BadCaseException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
