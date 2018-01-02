package code.serialize.exceptions;

import code.util.StringList;

public class BadAccessException extends RuntimeException {

    private static final String SEP = ":";

    public BadAccessException(Throwable _t, String _field) {
        super(StringList.concat(_t.getMessage(),SEP,_field));
    }

    public BadAccessException(String _field) {
        super(_field);
    }
}
