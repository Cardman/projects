package code.formathtml.exceptions;

public class CharacterFormatException extends RuntimeException {

    public CharacterFormatException() {
    }

    public CharacterFormatException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

    public CharacterFormatException(String _message) {
        super(_message);
    }

    public CharacterFormatException(Throwable _cause) {
        super(_cause);
    }

}
