package code.expressionlanguage.methods.exceptions;

public class BadCaseException extends RuntimeException {

    public BadCaseException() {
    }

    public BadCaseException(String _message) {
        super(_message);
    }
}
