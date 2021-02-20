package code.util.core;

abstract class AbstractEscapingFilter {
    protected static final char ESCAPING_CHAR = '\\';
    String escape(String _input) {
        int length_ = _input.length();
        char[] newArray_ = new char[length_];
        int i_ = IndexConstants.FIRST_INDEX;
        int j_ = IndexConstants.FIRST_INDEX;
        int newLength_ = length_;
        while (i_ < length_) {
            char cSp_ = _input.charAt(i_);
            if (cSp_ == ESCAPING_CHAR) {
                int next_ = i_;
                next_++;
                EscapeState esc_ = new EscapeState(i_,cSp_,newLength_);
                if (next_ < length_ && isEscSpaceChar(_input, next_)) {
                    esc_.apply(_input);
                }
                i_ = esc_.getIndex();
                cSp_ = esc_.getCurChar();
                newLength_ = esc_.getNewLength();
            }
            newArray_[j_] = cSp_;
            j_++;
            i_++;
        }
        return extract(newArray_, newLength_);
    }

    protected abstract boolean isEscSpaceChar(String _input, int _next);

    private static String extract(char[] _arr, int _len) {
        int min_ = Math.min(_arr.length,_len);
        char[] ext_ = new char[min_];
        for (int i = 0; i < min_; i++) {
            set(_arr, ext_, i);
        }
        return String.valueOf(ext_);
    }

    private static void set(char[] _arr, char[] _ext, int _i) {
        _ext[_i] = _arr[_i];
    }
}
