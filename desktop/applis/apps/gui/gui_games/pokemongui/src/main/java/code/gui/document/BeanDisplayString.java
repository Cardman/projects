package code.gui.document;

import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayString implements BeanDisplay<String> {

    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, String _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),_info);
        return 1;
    }
}
