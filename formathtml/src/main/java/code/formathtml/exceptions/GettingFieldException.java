package code.formathtml.exceptions;

public class GettingFieldException extends RuntimeException {

    public GettingFieldException() {
    }

    public GettingFieldException(String _message) {
        super(_message);
    }

    public GettingFieldException(Throwable _cause) {
        super(_cause);
    }

    public GettingFieldException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
