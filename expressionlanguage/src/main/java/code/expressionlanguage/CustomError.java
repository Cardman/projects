package code.expressionlanguage;

public final class CustomError {

    private String message = "";

    public CustomError() {
    }

    public CustomError(String _message) {
        message = _message;
    }

    public String getMessage() {
        return message;
    }
}
