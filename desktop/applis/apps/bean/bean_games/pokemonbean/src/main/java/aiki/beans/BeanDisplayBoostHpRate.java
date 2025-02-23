package aiki.beans;

import aiki.fight.util.*;

public final class BeanDisplayBoostHpRate implements BeanDisplay<BoostHpRate> {
    @Override
    public int display(CommonBean _rend, BoostHpRate _info, int _index) {
        _rend.formatMessageDirCts(_info.getHpRate().toNumberString());
        _rend.formatMessageDirCts(Long.toString(_info.getBoost()));
        return 2;
    }
}
