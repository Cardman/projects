package aiki.beans.fight;

import aiki.beans.BeanDisplay;
import aiki.beans.CommonBean;
import aiki.beans.facade.fight.*;

public final class BeanDisplaySufferedDamageCategory implements BeanDisplay<SufferedDamageCategory> {
    @Override
    public int display(CommonBean _rend, SufferedDamageCategory _info, int _index) {
        _rend.formatMessageDirCts(_info.getRound().toNumberString());
        _rend.formatMessageDirCts(_info.getUsing().toNumberString());
        return 2;
    }

}
