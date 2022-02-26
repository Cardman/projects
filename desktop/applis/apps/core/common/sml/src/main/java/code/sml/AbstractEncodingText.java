package code.sml;

import code.util.CustList;

abstract class AbstractEncodingText {
    private static final char ENCODED = '&';

    private static final char END_ESCAPED = ';';
    private int index;
    private final StringBuilder str;
    AbstractEncodingText(int _index,StringBuilder _str) {
        index = _index;
        str = _str;
    }

    static String encodeCommon(String _htmlText, CustList<EncodedChar> _map, int _length, AbstractEncodingText _incr) {
        while (_incr.index < _length) {
            if (_incr.exit(_htmlText, _map)) {
                break;
            }
        }
        return _incr.str.toString();
    }
    private boolean exit(String _htmlText, CustList<EncodedChar> _map) {
        int length_ = _htmlText.length();
        char ch_ = _htmlText.charAt(index);
        if (ch_ != ENCODED) {
            str.append(ch_);
            index++;
            return false;
        }
        int iEncode_ = index;
        index++;
        while (index < length_ && _htmlText.charAt(index) != END_ESCAPED) {
            index++;
        }
        if (index >= length_) {
            str.append(_htmlText.substring(iEncode_));
            return true;
        }
        incr(_htmlText, _map, str, iEncode_, index);
        index++;
        return false;
    }

    protected abstract void incr(String _htmlText, CustList<EncodedChar> _map, StringBuilder _str, int _iEncode, int _index);

    protected static boolean matchRegion(String _htmlText, int _iEncode, int _index, String _key) {
        boolean equals_ = true;
        int j_ = 0;
        for (int i = _iEncode; i <= _index; i++) {
            if (_htmlText.charAt(i) != _key.charAt(j_)) {
                equals_ = false;
                break;
            }
            j_++;
        }
        return equals_;
    }

}
