package code.expressionlanguage.classes;

public class BadRateException extends RuntimeException {

    public BadRateException() {
    }

    public BadRateException(String _message) {
        super(_message);
    }
}
