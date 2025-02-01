package code.gui.document;

import aiki.beans.CommonBean;

public final class BeanDisplayBool implements BeanDisplay<Integer> {

    private final String valueTrue;
    private final String valueFalse;

    public BeanDisplayBool(String _t, String _f) {
        this.valueTrue = _t;
        this.valueFalse = _f;
    }

    @Override
    public int display(AbsBeanRender _rend, Integer _info, int _index) {
        if (_info == CommonBean.TRUE_VALUE) {
            _rend.formatMessageDirCts(valueTrue);
        } else {
            _rend.formatMessageDirCts(valueFalse);
        }
        return 1;
    }
}
