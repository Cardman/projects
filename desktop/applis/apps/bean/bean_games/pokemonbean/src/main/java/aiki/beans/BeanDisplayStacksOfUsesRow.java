package aiki.beans;

import aiki.beans.fight.*;
import aiki.game.fight.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayStacksOfUsesRow implements BeanDisplayEltGrid<AbsRowGridPkBean<StacksOfUses>> {
    @Override
    public int displayEltGrid(CommonBean _rend, AbsRowGridPkBean<StacksOfUses> _info) {
        _rend.formatMessageDirCts(_info.getFirst());
        _rend.formatMessageDirCts(Long.toString(_info.getSecond()));
        _rend.formatMessageDirCts(Long.toString(_info.getInfo().getNbRounds()));
        if (_info.getInfo().isFirstStacked()) {
            _rend.formatMessageCts(MessagesPkBean.TEAM, MessagesFightTeam.M_P_92_HEAL_AFTER_Y);
        } else {
            _rend.formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_N);
        }
        if (_info.getInfo().isLastStacked()) {
            _rend.formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_Y);
        } else {
            _rend.formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_N);
        }
        return 5;
    }
}
