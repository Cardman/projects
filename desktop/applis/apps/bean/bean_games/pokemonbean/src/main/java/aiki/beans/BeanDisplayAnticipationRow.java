package aiki.beans;

import aiki.beans.fight.*;
import aiki.game.fight.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayAnticipationRow implements BeanDisplayEltGrid<AbsRowGridPkBean<Anticipation>> {
    @Override
    public int displayEltGrid(CommonBean _rend, AbsRowGridPkBean<Anticipation> _info) {
        _rend.formatMessageDirCts(_info.getFirst());
        _rend.formatMessageDirCts(Long.toString(_info.getSecond()));
        if (_info.getInfo().getTargetPosition().getTeam() == Fight.CST_FOE) {
            _rend.formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_FOE);
        } else if (_info.getInfo().getTargetPosition().getTeam() == Fight.CST_PLAYER) {
            _rend.formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_PLAYER);
        } else {
            _rend.formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
        }
        enabledAnt(_rend,_info.getInfo());
        _rend.formatMessageDirCts(_info.getInfo().getDamage().toNumberString());
        if (_info.getInfo().isIncrementing()) {
            _rend.formatMessageDirCts(Long.toString(_info.getInfo().getNbRounds()));
        } else {
            _rend.formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
        }
        return 6;
    }

    private void enabledAnt(CommonBean _rend, Anticipation _f) {
        if (_f.getTargetPosition().isEnabled()) {
            _rend.formatMessageDirCts(Long.toString(_f.getTargetPosition().getPosition()));
        } else {
            _rend.formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
        }
    }
}
