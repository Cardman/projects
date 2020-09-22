package code.expressionlanguage.analyze.files;

import code.util.StringList;

public final class CommentDelimiters {
    private final String begin;
    private final StringList end;
    public CommentDelimiters(String _begin, StringList _end) {
        begin = _begin;
        end = _end;
    }

    public String getBegin() {
        return begin;
    }

    public StringList getEnd() {
        return end;
    }
}
