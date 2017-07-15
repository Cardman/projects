package code.expressionlanguage.exceptions;

public class BadIndexException extends RuntimeException {

    private static final String SEPARATOR = ":";

    public BadIndexException(String _container) {
        super(_container);
    }

    public BadIndexException(String _container,String _message, Throwable _cause) {
        super(_container+SEPARATOR+_message, _cause);
    }

    public BadIndexException(String _container, String _message) {
        super(_container+SEPARATOR+_message);
    }

    public BadIndexException(String _container, Throwable _cause) {
        super(_container, _cause);
    }

}
