package code.expressionlanguage.methods.exceptions;

public class BadSwitchException extends RuntimeException {

    public BadSwitchException() {
    }

    public BadSwitchException(String _message) {
        super(_message);
    }
}
