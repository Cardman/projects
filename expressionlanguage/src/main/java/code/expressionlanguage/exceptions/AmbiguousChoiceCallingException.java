package code.expressionlanguage.exceptions;

public class AmbiguousChoiceCallingException extends RuntimeException {

    public AmbiguousChoiceCallingException() {
    }

    public AmbiguousChoiceCallingException(String _message) {
        super(_message);
    }
}
