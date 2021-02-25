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
    protected void incr(String _htmlText, StringMap<String> _map, StringBuilder _str, int _iEncode, int _index) {
        tryIncr(_htmlText, _map, _str, _iEncode, _index);
    }


    private static void tryIncr(String _htmlText, StringMap<String> _map, StringBuilder _str, int _iEncode, int _index) {
        if (!next(_htmlText, _str, _iEncode, _index, _map)) {
            tryApp(_htmlText, _str, _iEncode, _index);
        }
    }

    private static boolean next(String _htmlText, StringBuilder _str, int _iEncode, int _index, StringMap<String> _map) {
        boolean incr_ = false;
        for (EntryCust<String,String> k: _map.entryList()) {
            if (matchRegion(_htmlText, _iEncode, _index, k.getKey())) {
                String strValue_ = k.getValue();
                strValue_ = strValue_.substring(2, strValue_.length() - 1);
                int ascii_ = NumberUtil.parseInt(strValue_);
                char char_ = (char) ascii_;
                _str.append(char_);
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
