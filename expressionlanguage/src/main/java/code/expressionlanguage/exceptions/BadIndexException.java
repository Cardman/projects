package code.expressionlanguage.exceptions;

import code.util.StringList;

public class BadIndexException extends RuntimeException {

    private static final String SEPARATOR = ":";

    public BadIndexException(String _container) {
        super(_container);
    }

    public BadIndexException(String _container, String _message) {
        super(StringList.concat(_container,SEPARATOR,_message));
    }

}
