package code.gui.document;

import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayLong implements BeanDisplay<Long> {
    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, Long _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),Long.toString(_info));
        return 1;
    }

}
