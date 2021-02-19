package code.sml;

import code.util.EntryCust;
import code.util.StringMap;

abstract class AbstractEncodingText {
    private static final char ENCODED = '&';

    private static final char END_ESCAPED = ';';
    private int index;
    private final StringBuilder str;
    AbstractEncodingText(int _index,StringBuilder _str) {
        index = _index;
        str = _str;
    }
    boolean exit(String _htmlText, StringMap<String> _map) {
        int length_ = _htmlText.length();
        char ch_ = _htmlText.charAt(index);
        if (ch_ != ENCODED) {
            str.append(ch_);
            index++;
            return false;
        }
        int iBegin_ = index;
        index++;
        while (index < length_ && _htmlText.charAt(index) != END_ESCAPED) {
            index++;
        }
        if (index >= length_) {
            str.append(_htmlText.substring(iBegin_));
            return true;
        }
        index = incr(_htmlText, _map, str, index, iBegin_);
        return false;
    }

    protected abstract int incr(String _htmlText, StringMap<String> _map, StringBuilder _str, int _index, int _iBegin);

    protected static boolean eq(String _htmlText, int _i, int _iBegin, EntryCust<String, String> _e) {
        boolean equals_ = true;
        int j_ = 0;
        String key_ = _e.getKey();
        for (int i = _iBegin; i <= _i; i++) {
            if (_htmlText.charAt(i) != key_.charAt(j_)) {
                equals_ = false;
                break;
            }
            j_++;
        }
        return equals_;
    }
    int getIndex() {
        return index;
    }

    StringBuilder getStr() {
        return str;
    }
}
