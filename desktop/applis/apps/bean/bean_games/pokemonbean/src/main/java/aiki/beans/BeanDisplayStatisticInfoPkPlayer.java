package aiki.beans;

import aiki.beans.facade.game.dto.*;

public final class BeanDisplayStatisticInfoPkPlayer implements BeanDisplayEltGrid<StatisticInfoPkPlayer> {
    @Override
    public int displayEltGrid(CommonBean _rend, StatisticInfoPkPlayer _info) {
        _rend.formatMessageDirCts(_info.getName());
        _rend.formatMessageDirCts(Long.toString(_info.getEv()));
        _rend.formatMessageDirCts(Long.toString(_info.getIv()));
        _rend.formatMessageDirCts(_info.getRate().toNumberString());
        return 4;
    }
}
