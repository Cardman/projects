package code.gui.document;

import aiki.game.*;
import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayUsesOfMove implements BeanDisplay<UsesOfMove> {
    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, UsesOfMove _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),Long.toString(_info.getCurrent()));
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index+1,_count),Long.toString(_info.getMax()));
        return 2;
    }

}
