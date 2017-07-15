package code.serialize.exceptions;

public class BadAccessException extends RuntimeException {

    private static final String SEP = ":";

    public BadAccessException(Throwable _t, String _field) {
        super(_t.getMessage()+SEP+_field);
    }

    public BadAccessException(String _field) {
        super(_field);
    }
}
