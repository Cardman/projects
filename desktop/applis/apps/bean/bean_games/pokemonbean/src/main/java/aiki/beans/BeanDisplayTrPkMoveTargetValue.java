package aiki.beans;

import aiki.beans.fight.*;
import aiki.game.fight.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayTrPkMoveTargetValue implements BeanDisplay<TrPkMoveTarget> {

    @Override
    public int display(CommonBean _rend, TrPkMoveTarget _info, int _index) {
        _rend.formatMessageDirCts(_info.getMoveTarget().getMove());
        if (_info.isChosen()) {
            if (_info.getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
                _rend.formatMessageCts(MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_FOE_CHOICES_FOE);
            } else {
                _rend.formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_PLAYER);
            }
            _rend.formatMessageDirCts(Long.toString(_info.getMoveTarget().getTarget().getPosition()));
            _rend.formatMessageDirCts(_info.getTranslation());
        } else {
            _rend.formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
            _rend.formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
            _rend.formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
        }
        return 4;
    }

}
