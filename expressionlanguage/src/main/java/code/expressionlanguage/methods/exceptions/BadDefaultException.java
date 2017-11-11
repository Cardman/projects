package code.expressionlanguage.methods.exceptions;

public class BadDefaultException extends RuntimeException {

    public BadDefaultException() {
    }

    public BadDefaultException(String _message) {
        super(_message);
    }
}
