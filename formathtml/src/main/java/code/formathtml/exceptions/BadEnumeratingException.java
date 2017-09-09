package code.formathtml.exceptions;

public class BadEnumeratingException extends RuntimeException {

    private static final String SEP = "\n";

    public BadEnumeratingException(Object _li, String _message) {
        super(_li+SEP+_message);
    }
}
