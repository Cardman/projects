package code.formathtml.exceptions;

public class KeyValueException extends RuntimeException {

    private static final String SEP = "\n";

    public KeyValueException(String _keyValue, String _message) {
        super(_keyValue+SEP+_message);
    }
}
