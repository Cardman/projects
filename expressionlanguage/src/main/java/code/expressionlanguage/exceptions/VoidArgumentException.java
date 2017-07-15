package code.expressionlanguage.exceptions;

public class VoidArgumentException extends RuntimeException {

    public VoidArgumentException() {
    }

    public VoidArgumentException(String _message) {
        super(_message);
    }

    public VoidArgumentException(Throwable _cause) {
        super(_cause);
    }

    public VoidArgumentException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
