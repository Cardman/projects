package code.expressionlanguage.exceptions;

public class BadNumberValuesException extends RuntimeException {

    public BadNumberValuesException() {
    }

    public BadNumberValuesException(String _message) {
        super(_message);
    }
}
