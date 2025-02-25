package aiki.beans;

import aiki.beans.fight.*;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import code.scripts.pages.aiki.*;

public final class BeanDisplayTrPkMoveTarget implements BeanDisplay<TrPkMoveTarget> {
    @Override
    public int display(CommonBean _rend, TrPkMoveTarget _info, int _index) {
        displayTrPkMoveTarget(_rend,_info);
        return 4;
    }

    private void displayTrPkMoveTarget(CommonBean _rend, TrPkMoveTarget _value) {
        _rend.formatMessageDirCts(_value.getMoveTarget().getMove());
        if (_value.getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
            _rend.formatMessageCts(MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_ALLY_CHOICES_FOE);
        } else {
            _rend.formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_ALLY_CHOICES_PLAYER);
        }
        if (_value.getMoveTarget().getTarget().getPosition() != Fighter.BACK) {
            _rend.formatMessageDirCts(Long.toString(_value.getMoveTarget().getTarget().getPosition()));
            _rend.formatMessageDirCts(_value.getTranslation());
        } else {
            _rend.formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
            _rend.formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
        }
    }
}
