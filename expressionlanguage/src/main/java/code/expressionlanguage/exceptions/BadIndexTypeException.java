package code.expressionlanguage.exceptions;

import code.util.StringList;

public class BadIndexTypeException extends RuntimeException {

    private static final String SEPARATOR = ":";

    public BadIndexTypeException(String _container) {
        super(_container);
    }

    public BadIndexTypeException(String _container, String _message) {
        super(StringList.concat(_container,SEPARATOR,_message));
    }

}
