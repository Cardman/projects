package code.formathtml.exceptions;

import code.util.StringList;

public class BadEnumeratingException extends RuntimeException {

    private static final String SEP = "\n";

    public BadEnumeratingException(StringList _li, String _message) {
        super(StringList.concat(_li.display(),SEP,_message));
    }
}
