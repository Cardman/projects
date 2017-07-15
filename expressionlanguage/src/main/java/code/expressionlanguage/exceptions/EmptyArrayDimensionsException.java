package code.expressionlanguage.exceptions;

public class EmptyArrayDimensionsException extends RuntimeException {

    public EmptyArrayDimensionsException() {
    }

    public EmptyArrayDimensionsException(String _message) {
        super(_message);
    }

    public EmptyArrayDimensionsException(Throwable _cause) {
        super(_cause);
    }

    public EmptyArrayDimensionsException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
