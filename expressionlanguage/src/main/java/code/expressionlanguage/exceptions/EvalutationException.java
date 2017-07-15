package code.expressionlanguage.exceptions;

public class EvalutationException extends RuntimeException {

    public EvalutationException() {
    }

    public EvalutationException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

    public EvalutationException(String _message) {
        super(_message);
    }

    public EvalutationException(Throwable _cause) {
        super(_cause);
    }

}
