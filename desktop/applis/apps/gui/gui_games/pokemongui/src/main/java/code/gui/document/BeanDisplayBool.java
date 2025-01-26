package code.gui.document;

import aiki.beans.CommonBean;
import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayBool implements BeanDisplay<Integer> {

    private final String valueTrue;
    private final String valueFalse;

    public BeanDisplayBool(String _t, String _f) {
        this.valueTrue = _t;
        this.valueFalse = _f;
    }

    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, Integer _info, int _index, int _count) {
        if (_info == CommonBean.TRUE_VALUE) {
            _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),valueTrue);
        } else {
            _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),valueFalse);
        }
        return 1;
    }
}
