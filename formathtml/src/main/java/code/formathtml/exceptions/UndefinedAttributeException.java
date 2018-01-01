package code.formathtml.exceptions;

import code.util.StringList;

public class UndefinedAttributeException extends RuntimeException {

    private static final String SEP = "\n";

    public UndefinedAttributeException(String _attribute, String _message) {
        super(StringList.concat(_attribute,SEP,_message));
    }
}
