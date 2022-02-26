package code.sml;

import code.util.CustList;
import code.util.core.NumberUtil;

public final class TryIncrEncodingText extends AbstractEncodingText {
    private static final char NUMBERED_CHAR = '#';
    TryIncrEncodingText(int _index, StringBuilder _str) {
        super(_index, _str);
    }

    @Override
    protected void incr(String _htmlText, CustList<EncodedChar> _map, StringBuilder _str, int _iEncode, int _index) {
        tryIncr(_htmlText, _map, _str, _iEncode, _index);
    }


    private static void tryIncr(String _htmlText, CustList<EncodedChar> _map, StringBuilder _str, int _iEncode, int _index) {
        if (!next(_htmlText, _str, _iEncode, _index, _map)) {
            tryApp(_htmlText, _str, _iEncode, _index);
        }
    }

    private static boolean next(String _htmlText, StringBuilder _str, int _iEncode, int _index, CustList<EncodedChar> _map) {
        boolean incr_ = false;
        for (EncodedChar k: _map) {
            if (matchRegion(_htmlText, _iEncode, _index, k.getKey())) {
                _str.append(k.getValue());
                incr_ = true;
                break;
            }
        }
        return incr_;
    }
    private static void tryApp(String _htmlText, StringBuilder _str, int _iEncode, int _index) {
        if (_htmlText.charAt(_iEncode + 1) == NUMBERED_CHAR) {
            String strValue_ = _htmlText.substring(_iEncode + 2, _index);
            int ascii_ = (int) NumberUtil.parseLongZero(strValue_);
            char char_ = (char) ascii_;
            _str.append(char_);
            return;
        }
        _str.append(_htmlText, _iEncode, _index + 1);
    }

}
