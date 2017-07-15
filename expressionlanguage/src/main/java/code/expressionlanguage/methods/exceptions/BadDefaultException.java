package code.expressionlanguage.methods.exceptions;

public class BadDefaultException extends RuntimeException {

    public BadDefaultException() {
    }

    public BadDefaultException(String _message) {
        super(_message);
    }

    public BadDefaultException(Throwable _cause) {
        super(_cause);
    }

    public BadDefaultException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
