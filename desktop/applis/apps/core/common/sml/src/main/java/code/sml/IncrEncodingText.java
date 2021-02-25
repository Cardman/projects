package code.sml;

import code.util.EntryCust;
import code.util.StringMap;

public final class IncrEncodingText extends AbstractEncodingText {
    IncrEncodingText(int _index, StringBuilder _str) {
        super(_index, _str);
    }

    @Override
    protected void incr(String _htmlText, StringMap<String> _map, StringBuilder _str, int _iEncode, int _index) {
        incrInner(_htmlText, _map, _str, _iEncode, _index);
    }

    private static void incrInner(String _htmlText, StringMap<String> _map, StringBuilder _str, int _iEncode, int _index) {
        boolean added_ = false;
        for (EntryCust<String,String> k: _map.entryList()) {
            if (matchRegion(_htmlText, _iEncode, _index, k.getKey())) {
                _str.append(k.getValue());
                added_ = true;
                break;
            }
        }
        if (!added_) {
            _str.append(_htmlText, _iEncode, _index + 1);
        }
    }

}
