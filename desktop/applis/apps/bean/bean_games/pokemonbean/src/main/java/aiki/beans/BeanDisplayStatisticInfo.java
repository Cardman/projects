package aiki.beans;

import aiki.beans.facade.fight.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayStatisticInfo implements BeanDisplayEltGrid<StatisticInfo> {
    @Override
    public int displayEltGrid(CommonBean _rend, StatisticInfo _info) {
        _rend.formatMessageDirCts(_info.getDisplayStatistic());
        cell(_rend,_info.base(), _info.getStatisBase().toNumberString());
        cell(_rend,_info.base(), Long.toString(_info.getEv()));
        cell(_rend,_info.base(), Long.toString(_info.getIv()));
        cell(_rend,_info.boost(), Long.toString(_info.getStatisBoost()));
        return 5;
    }

    private void cell(CommonBean _rend, int _v, String _txt) {
        if (_v == CommonBean.TRUE_VALUE) {
            _rend.formatMessageDirCts(_txt);
        } else {
            _rend.formatMessageCts(MessagesPkBean.FIGHTER, MessagesFightFighter.M_P_91_STATISTICS_NO);
        }
    }
}
