package code.sml;

import code.util.EntryCust;
import code.util.StringMap;

public final class IncrEncodingText extends AbstractEncodingText {
    IncrEncodingText(int _index, StringBuilder _str) {
        super(_index, _str);
    }

    @Override
    protected int incr(String _htmlText, StringMap<String> _map, StringBuilder _str, int _index, int _iBegin) {
        return incrInner(_htmlText, _map, _str, _index, _iBegin);
    }

    private static int incrInner(String _htmlText, StringMap<String> _map, StringBuilder _str, int _i, int _iBegin) {
        int i_ = _i;
        boolean add_ = false;
        for (EntryCust<String,String> k: _map.entryList()) {
            boolean equals_ = eq(_htmlText, i_, _iBegin, k);
            if (equals_) {
                _str.append(k.getValue());
                i_++;
                add_ = true;
                break;
            }
        }
        if (!add_) {
            _str.append(_htmlText, _iBegin, i_ + 1);
            i_++;
        }
        return i_;
    }

}
