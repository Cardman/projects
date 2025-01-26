package code.gui.document;

import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayBoolLong implements BeanDisplay<Long> {

    private final String valueTrue;
    private final String valueFalse;

    public BeanDisplayBoolLong(String _t, String _f) {
        this.valueTrue = _t;
        this.valueFalse = _f;
    }

    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, Long _info, int _index, int _count) {
        if (_info > 0) {
            _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),valueTrue);
        } else {
            _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),valueFalse);
        }
        return 1;
    }
}
