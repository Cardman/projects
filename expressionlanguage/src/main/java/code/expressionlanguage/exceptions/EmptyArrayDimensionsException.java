package code.expressionlanguage.exceptions;

public class EmptyArrayDimensionsException extends RuntimeException {

    public EmptyArrayDimensionsException() {
    }

    public EmptyArrayDimensionsException(String _message) {
        super(_message);
    }
}
