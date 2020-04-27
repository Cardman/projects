package code.expressionlanguage.files;

public final class CommentDelimiters {
    private final String begin;
    private final String end;
    public CommentDelimiters(String _begin, String _end) {
        begin = _begin;
        end = _end;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }
}
