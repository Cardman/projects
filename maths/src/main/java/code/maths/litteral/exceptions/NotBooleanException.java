package code.maths.litteral.exceptions;

public class NotBooleanException extends IllegalArgumentException {

    public NotBooleanException() {
    }

    public NotBooleanException(String _s) {
        super(_s);
    }

    public NotBooleanException(Throwable _cause) {
        super(_cause);
    }

    public NotBooleanException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
