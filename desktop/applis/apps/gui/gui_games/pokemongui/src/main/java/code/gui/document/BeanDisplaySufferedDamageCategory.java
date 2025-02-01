package code.gui.document;

import aiki.beans.facade.fight.*;

public final class BeanDisplaySufferedDamageCategory implements BeanDisplay<SufferedDamageCategory> {
    @Override
    public int display(AbsBeanRender _rend, SufferedDamageCategory _info, int _index) {
        _rend.formatMessageDirCts(_info.getRound().toNumberString());
        _rend.formatMessageDirCts(_info.getUsing().toNumberString());
        return 2;
    }

}
