package code.expressionlanguage.exceptions;

public class DynamicArrayStoreException extends RuntimeException {

    private static final String SEP = " != ";

    private static final String RETURN_LINE = "\n";

    public DynamicArrayStoreException(String _expected, String _found, String _addon) {
        super(_expected+SEP+_found+RETURN_LINE+_addon);
    }
}
