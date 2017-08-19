package code.formathtml;

public class PairTokenType {

    private String token;

    private TokenType type;

    public PairTokenType() {
    }

    public PairTokenType(String _token, TokenType _type) {
        token = _token;
        type = _type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String _token) {
        token = _token;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType _type) {
        type = _type;
    }
}
