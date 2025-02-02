package aiki.beans.fight;

import aiki.beans.BeanDisplay;
import aiki.beans.CommonBean;
import aiki.beans.facade.fight.*;

public final class BeanDisplayMultPowerMoves implements BeanDisplay<MultPowerMoves> {
    @Override
    public int display(CommonBean _rend, MultPowerMoves _info, int _index) {
        _rend.formatMessageDirCts(_info.getMultInflicted().toNumberString());
        _rend.formatMessageDirCts(_info.getMultSuffering().toNumberString());
        return 2;
    }

}
