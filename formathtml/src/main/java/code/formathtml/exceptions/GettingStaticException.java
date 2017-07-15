package code.formathtml.exceptions;

public class GettingStaticException extends RuntimeException {

    public GettingStaticException() {
    }

    public GettingStaticException(String _message) {
        super(_message);
    }

    public GettingStaticException(Throwable _cause) {
        super(_cause);
    }

    public GettingStaticException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
