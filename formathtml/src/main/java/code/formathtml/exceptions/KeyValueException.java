package code.formathtml.exceptions;

import code.util.StringList;

public class KeyValueException extends RuntimeException {

    private static final String SEP = "\n";

    public KeyValueException(String _keyValue, String _message) {
        super(StringList.concat(_keyValue,SEP,_message));
    }
}
