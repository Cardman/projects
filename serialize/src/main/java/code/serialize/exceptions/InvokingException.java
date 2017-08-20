package code.serialize.exceptions;

public class InvokingException extends RuntimeException {

    private final Throwable target;

    public InvokingException(Throwable _t) {
        super(_t);
        target = _t;
    }

    public InvokingException(Throwable _t, String _message) {
        super(_message, _t);
        target = _t;
    }

    public InvokingException(Throwable _t, Throwable _target) {
        super(_t);
        target = _target;
    }

    public Throwable getTarget() {
        return target;
    }
}
