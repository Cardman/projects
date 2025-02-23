package aiki.beans;

import aiki.fight.util.*;

public final class BeanDisplayEfficiencyRate implements BeanDisplay<EfficiencyRate> {
    @Override
    public int display(CommonBean _rend, EfficiencyRate _info, int _index) {
        _rend.formatMessageDirCts(_info.getEff().toNumberString());
        _rend.formatMessageDirCts(_info.getHpRate().toNumberString());
        return 2;
    }
}
