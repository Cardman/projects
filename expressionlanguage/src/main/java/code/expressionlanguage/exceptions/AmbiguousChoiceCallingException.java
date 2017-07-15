package code.expressionlanguage.exceptions;

public class AmbiguousChoiceCallingException extends RuntimeException {

    public AmbiguousChoiceCallingException() {
    }

    public AmbiguousChoiceCallingException(String _message) {
        super(_message);
    }

    public AmbiguousChoiceCallingException(Throwable _cause) {
        super(_cause);
    }

    public AmbiguousChoiceCallingException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
