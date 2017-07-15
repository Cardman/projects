package aiki.exceptions;

public class NotEmptyLinkException extends RuntimeException {

    public NotEmptyLinkException() {
    }

    public NotEmptyLinkException(String _message) {
        super(_message);
    }

}
