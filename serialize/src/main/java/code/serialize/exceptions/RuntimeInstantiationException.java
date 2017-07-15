package code.serialize.exceptions;

public class RuntimeInstantiationException extends RuntimeException {

    public RuntimeInstantiationException(Throwable _t) {
        super(_t);
    }

    public RuntimeInstantiationException(Throwable _t, String _message) {
        super(_message, _t);
    }

    public RuntimeInstantiationException(String _string) {
        super(_string);
    }
}
