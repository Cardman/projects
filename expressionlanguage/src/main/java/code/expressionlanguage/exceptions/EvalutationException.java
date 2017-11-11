package code.expressionlanguage.exceptions;

public class EvalutationException extends RuntimeException {

    public EvalutationException() {
    }

    public EvalutationException(String _message) {
        super(_message);
    }

}
