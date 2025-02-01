package code.gui.document;

import aiki.beans.facade.fight.*;

public final class BeanDisplayMultPowerMoves implements BeanDisplay<MultPowerMoves> {
    @Override
    public int display(AbsBeanRender _rend, MultPowerMoves _info, int _index) {
        _rend.formatMessageDirCts(_info.getMultInflicted().toNumberString());
        _rend.formatMessageDirCts(_info.getMultSuffering().toNumberString());
        return 2;
    }

}
