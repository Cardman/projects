package code.sml;

import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.NumberUtil;

public final class TryIncrEncodingText extends AbstractEncodingText {
    private static final char NUMBERED_CHAR = '#';
    TryIncrEncodingText(int _index, StringBuilder _str) {
        super(_index, _str);
    }

    @Override
    protected int incr(String _htmlText, StringMap<String> _map, StringBuilder _str, int _index, int _iBegin) {
        return tryIncr(_htmlText, _map, _str, _index, _iBegin);
    }


    private static int tryIncr(String _htmlText, StringMap<String> _map, StringBuilder _str, int _i, int _iBegin) {
        int i_ = _i;
        int next_ = next(_htmlText, _str, i_, _map, _iBegin);
        if (next_ == i_) {
            i_ = tryApp(_htmlText, _str, next_, _iBegin);
        } else {
            i_ = next_;
        }
        return i_;
    }

    private static int next(String _htmlText, StringBuilder _str, int _i,StringMap<String> _map, int _iBegin) {
        int i_ = _i;
        for (EntryCust<String,String> k: _map.entryList()) {
            boolean equals_ = eq(_htmlText, i_, _iBegin, k);
            if (equals_) {
                String strValue_ = k.getValue();
                strValue_ = strValue_.substring(2, strValue_.length() - 1);
                int ascii_ = NumberUtil.parseInt(strValue_);
                char char_ = (char) ascii_;
                _str.append(char_);
                i_++;
                break;
            }
        }
        return i_;
    }
    private static int tryApp(String _htmlText, StringBuilder _str, int _i, int _iBegin) {
        int i_ = _i;
        if (_htmlText.charAt(_iBegin + 1) == NUMBERED_CHAR) {
            String strValue_ = _htmlText.substring(_iBegin + 2, i_);
            int ascii_ = (int) NumberUtil.parseLongZero(strValue_);
            char char_ = (char) ascii_;
            _str.append(char_);
            i_++;
            return i_;
        }
        _str.append(_htmlText, _iBegin, i_ + 1);
        i_++;
        return i_;
    }

}
