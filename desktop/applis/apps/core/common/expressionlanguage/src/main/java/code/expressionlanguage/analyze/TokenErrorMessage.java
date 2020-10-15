package code.expressionlanguage.analyze;

public final class TokenErrorMessage {
    private final String message;
    private final boolean error;

    public TokenErrorMessage(String _message, boolean _error) {
        this.message = _message;
        this.error = _error;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }
}
