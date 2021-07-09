package code.expressionlanguage.analyze;

public final class InfoErrorDto {
    private final String message;
    private final boolean warn;
    private final int begin;
    private final int length;
    public InfoErrorDto(String _message){
        this(_message, 0, 0);
    }
    public InfoErrorDto(String _message, int _begin, int _length){
        this(_message, _begin, _length, false);
    }
    public InfoErrorDto(String _message, int _begin, int _length, boolean _warn){
        message = _message;
        begin = _begin;
        length = _length;
        warn = _warn;
    }

    public boolean isWarn() {
        return warn;
    }

    public int getLength() {
        return length;
    }

    public int getBegin() {
        return begin;
    }

    public String getMessage() {
        return message;
    }
}
