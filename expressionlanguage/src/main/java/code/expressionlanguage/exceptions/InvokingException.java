package code.expressionlanguage.exceptions;

public class InvokingException extends RuntimeException {

    private final Throwable target;

    public InvokingException(Throwable _t) {
        target = _t;
    }

    public InvokingException(Throwable _t, String _message) {
        super(_message);
        target = _t;
    }

    public Throwable getTarget() {
        return target;
    }
}
