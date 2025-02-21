package aiki.beans;

import code.maths.*;

public final class BeanDisplayRateAbs implements BeanDisplay<Rate> {
    private final String file;
    private final String neg;
    private final String pos;

    public BeanDisplayRateAbs(String _f, String _n, String _p) {
        this.file = _f;
        this.neg = _n;
        this.pos = _p;
    }

    @Override
    public int display(CommonBean _rend, Rate _info, int _index) {
        if (_info.isZeroOrGt()) {
            _rend.formatMessageCts(file,pos,_info.absNb().toNumberString());
        } else {
            _rend.formatMessageCts(file,neg,_info.absNb().toNumberString());
        }
        return 1;
    }

}
