package code.sml;

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

    static String encodeCommon(String _htmlText, StringMap<String> _map, int _length, AbstractEncodingText _incr) {
        while (_incr.index < _length) {
            if (_incr.exit(_htmlText, _map)) {
                break;
            }
        }
        return _incr.str.toString();
    }
    private boolean exit(String _htmlText, StringMap<String> _map) {
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
        incr(_htmlText, _map, str, iBegin_, index);
        index++;
        return false;
    }

    protected abstract void incr(String _htmlText, StringMap<String> _map, StringBuilder _str, int _iBegin, int _index);

    protected static boolean matchRegion(String _htmlText, int _iBegin, int _i, String _key) {
        boolean equals_ = true;
        int j_ = 0;
        for (int i = _iBegin; i <= _i; i++) {
            if (_htmlText.charAt(i) != _key.charAt(j_)) {
                equals_ = false;
                break;
            }
            j_++;
        }
        return equals_;
    }

}
