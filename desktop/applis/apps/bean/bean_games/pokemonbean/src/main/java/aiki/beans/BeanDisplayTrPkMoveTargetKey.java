package aiki.beans;

import aiki.beans.fight.*;

public final class BeanDisplayTrPkMoveTargetKey implements BeanDisplay<TrPkMoveTarget> {
    @Override
    public int display(CommonBean _rend, TrPkMoveTarget _info, int _index) {
        _rend.formatMessageDirCts(Long.toString(_info.getIndex()));
        _rend.formatMessageDirCts(_info.getTranslation());
        return 2;
    }

}
