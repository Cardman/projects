package code.sml;

import code.util.CustList;
import code.util.IntTreeMap;
import code.util.core.StringUtil;

abstract class AbstractEncodingText {
    private static final char ENCODED = '&';

    private static final char END_ESCAPED = ';';
    private int index;
    private final StringBuilder str;
    AbstractEncodingText(int _index,StringBuilder _str) {
        index = _index;
        str = _str;
    }

    static String encodeCommon(int _from, String _htmlText, CustList<EncodedChar> _map, IntTreeMap<Integer> _found, int _length, AbstractEncodingText _incr) {
        while (_incr.index < _length) {
            if (_incr.exit(_from,_htmlText, _map,_found)) {
                break;
            }
        }
        return _incr.str.toString();
    }
    private boolean exit(int _from, String _htmlText, CustList<EncodedChar> _map, IntTreeMap<Integer> _found) {
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
        if (incr(_htmlText, _map, str, iEncode_, index)){
            int beginEsc_ = iEncode_ + _from;
            _found.put(beginEsc_, index - iEncode_);
        }
        index++;
        return false;
    }

    protected abstract boolean incr(String _htmlText, CustList<EncodedChar> _map, StringBuilder _str, int _iEncode, int _index);

    protected static boolean matchRegion(String _htmlText, int _iEncode, int _index, String _key) {
        boolean equals_ = true;
        int from_ = _iEncode+1;
        int to_ = _index-1;
        while (from_ <= to_) {
            if (!StringUtil.isWhitespace(_htmlText.charAt(from_))) {
                break;
            }
            from_++;
        }
        while (to_ >= from_) {
            if (!StringUtil.isWhitespace(_htmlText.charAt(to_))) {
                break;
            }
            to_--;
        }
        if (to_ < from_) {
            return false;
        }
        int j_ = 1;
        for (int i = from_; i <= to_; i++) {
            if (_htmlText.charAt(i) != _key.charAt(j_)) {
                equals_ = false;
                break;
            }
            j_++;
        }
        return equals_;
    }

}
