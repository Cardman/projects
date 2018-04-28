package code.expressionlanguage;


public final class CustomError {

    private final String message;

    public CustomError() {
        this("");
    }

    public CustomError(String _message) {
        message = _message;
    }

    public String getMessage() {
        return message;
    }
}
