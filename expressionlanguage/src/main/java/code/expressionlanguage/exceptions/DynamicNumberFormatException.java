package code.expressionlanguage.exceptions;

public class DynamicNumberFormatException extends RuntimeException {

    public DynamicNumberFormatException() {
    }

    public DynamicNumberFormatException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

    public DynamicNumberFormatException(String _message) {
        super(_message);
    }

    public DynamicNumberFormatException(Throwable _cause) {
        super(_cause);
    }

}
