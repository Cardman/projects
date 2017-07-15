package code.expressionlanguage.methods.exceptions;

public class BadSwitchException extends RuntimeException {

    public BadSwitchException() {
    }

    public BadSwitchException(String _message) {
        super(_message);
    }

    public BadSwitchException(Throwable _cause) {
        super(_cause);
    }

    public BadSwitchException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
