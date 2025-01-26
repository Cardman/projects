package code.gui.document;

import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;

public final class BeanDisplayLgInt implements BeanDisplay<LgInt> {
    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, LgInt _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),_info.toNumberString());
        return 1;
    }

}
