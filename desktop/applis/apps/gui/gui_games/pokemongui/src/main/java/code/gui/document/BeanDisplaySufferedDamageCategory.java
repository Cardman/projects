package code.gui.document;

import aiki.beans.facade.fight.*;
import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplaySufferedDamageCategory implements BeanDisplay<SufferedDamageCategory> {
    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, SufferedDamageCategory _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),_info.getRound().toNumberString());
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index+1,_count),_info.getUsing().toNumberString());
        return 2;
    }

}
