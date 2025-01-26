package code.gui.document;

import aiki.beans.facade.fight.*;
import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayMultPowerMoves implements BeanDisplay<MultPowerMoves> {
    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, MultPowerMoves _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),_info.getMultInflicted().toNumberString());
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index+1,_count),_info.getMultSuffering().toNumberString());
        return 2;
    }

}
