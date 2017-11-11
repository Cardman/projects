package code.expressionlanguage.exceptions;

public class BadIndexTypeException extends RuntimeException {

    private static final String SEPARATOR = ":";

    public BadIndexTypeException(String _container) {
        super(_container);
    }

    public BadIndexTypeException(String _container, String _message) {
        super(_container+SEPARATOR+_message);
    }

}
