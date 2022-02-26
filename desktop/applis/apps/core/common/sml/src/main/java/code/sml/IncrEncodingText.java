package code.sml;

import code.util.CustList;

public final class IncrEncodingText extends AbstractEncodingText {
    IncrEncodingText(int _index, StringBuilder _str) {
        super(_index, _str);
    }

    @Override
    protected void incr(String _htmlText, CustList<EncodedChar> _map, StringBuilder _str, int _iEncode, int _index) {
        incrInner(_htmlText, _map, _str, _iEncode, _index);
    }

    private static void incrInner(String _htmlText, CustList<EncodedChar> _map, StringBuilder _str, int _iEncode, int _index) {
        boolean added_ = false;
        for (EncodedChar k: _map) {
            if (matchRegion(_htmlText, _iEncode, _index, k.getKey())) {
                _str.append("&#").append((int) k.getValue()).append(";");
                added_ = true;
                break;
            }
        }
        if (!added_) {
            _str.append(_htmlText, _iEncode, _index + 1);
        }
    }

}
