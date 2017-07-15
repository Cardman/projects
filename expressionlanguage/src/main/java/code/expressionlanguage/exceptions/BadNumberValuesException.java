package code.expressionlanguage.exceptions;

public class BadNumberValuesException extends RuntimeException {

    public BadNumberValuesException() {
    }

    public BadNumberValuesException(String _message) {
        super(_message);
    }

    public BadNumberValuesException(Throwable _cause) {
        super(_cause);
    }

    public BadNumberValuesException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
