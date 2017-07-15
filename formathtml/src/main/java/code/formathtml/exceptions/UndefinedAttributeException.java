package code.formathtml.exceptions;

public class UndefinedAttributeException extends RuntimeException {

    private static final String SEP = "\n";

    public UndefinedAttributeException(String _attribute, String _message) {
        super(_attribute+SEP+_message);
    }
}
