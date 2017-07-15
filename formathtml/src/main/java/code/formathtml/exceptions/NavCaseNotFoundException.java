package code.formathtml.exceptions;

public class NavCaseNotFoundException extends RuntimeException {

    public NavCaseNotFoundException(String _message) {
        super(_message);
    }

    public NavCaseNotFoundException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
