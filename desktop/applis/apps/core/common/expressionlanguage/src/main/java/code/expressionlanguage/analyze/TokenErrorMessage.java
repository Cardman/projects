package code.expressionlanguage.analyze;

public final class TokenErrorMessage {
    private final String message;
    private final boolean error;

    public TokenErrorMessage(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }
}
