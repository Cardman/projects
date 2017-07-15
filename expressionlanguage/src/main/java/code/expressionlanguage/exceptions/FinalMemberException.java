package code.expressionlanguage.exceptions;

public class FinalMemberException extends RuntimeException {

    public FinalMemberException() {
    }

    public FinalMemberException(String _message) {
        super(_message);
    }

    public FinalMemberException(Throwable _cause) {
        super(_cause);
    }

    public FinalMemberException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
