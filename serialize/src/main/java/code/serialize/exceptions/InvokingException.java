package code.serialize.exceptions;

public class InvokingException extends RuntimeException {

    public InvokingException(Throwable _t) {
        super(_t);
    }

    public InvokingException(Throwable _t, String _message) {
        super(_message, _t);
    }
}
