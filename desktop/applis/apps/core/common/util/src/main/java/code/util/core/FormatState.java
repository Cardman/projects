package code.util.core;

final class FormatState {
    private static final char QUOTE = '\'';
    private final int index;
    private final boolean escaped;
    private final boolean iterate;

    FormatState(int _index, boolean _escaped, boolean _iterate) {
        this.index = _index;
        this.escaped = _escaped;
        this.iterate = _iterate;
    }
    static FormatState keep(int _i, boolean _escaped,StringBuilder _str,String _format) {
        int i_ = _i;
        int length_ = _format.length();
        boolean escaped_ = _escaped;
        char cur_ = _format.charAt(i_);
        if (cur_ == QUOTE) {
            escaped_ = !escaped_;
            if (endQuote(_format, length_, i_)) {
                _str.append(QUOTE);
                i_++;
                i_++;
                return new FormatState(i_, false,true);
            }
            i_++;
            return new FormatState(i_,escaped_,true);
        }
        if (escaped_) {
            _str.append(cur_);
            i_++;
            return new FormatState(i_, true,true);
        }
        return new FormatState(i_, false,false);
    }

    private static boolean endQuote(String _format, int _length, int _i) {
        return _i < _length - 1 && _format.charAt(_i + 1) == QUOTE;
    }

    public int getIndex() {
        return index;
    }

    public boolean isEscaped() {
        return escaped;
    }

    public boolean isIterate() {
        return iterate;
    }
}
