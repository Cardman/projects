package aiki.beans.moves.effects;

import aiki.beans.status.AikiBeansStatusStd;
import aiki.game.fight.Fight;
import org.junit.Test;

public final class EffectTeamWhileSendFoeBeanTest extends InitDbMoveEffectTeamWhileSendFoe {
    @Test
    public void getDamageRateAgainstFoe1() {
        assertEq(Fight.TEMPS_TOUR,callEffectTeamWhileSendFoeBeanDamageRateAgainstFoeGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)));
    }
    @Test
    public void getStatusByNbUses1() {
        assertSizeEq(1,callEffectTeamWhileSendFoeBeanStatusByNbUsesGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)));
    }
    @Test
    public void getStatusByNbUses2() {
        assertEq(1,first(elt(callEffectTeamWhileSendFoeBeanStatusByNbUsesGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0)));
    }
    @Test
    public void getStatusByNbUses3() {
        assertEq(S_STA_SIM,second(elt(callEffectTeamWhileSendFoeBeanStatusByNbUsesGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0)));
    }
    @Test
    public void getTranslatedStatus() {
        assertEq(S_STA_SIM_TR,callEffectTeamWhileSendFoeBeanGetTranslatedStatus(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0),0));
    }
    @Test
    public void clickStatus1() {
        assertEq(AikiBeansStatusStd.WEB_HTML_STATUS_DATA_HTML,callEffectTeamWhileSendFoeBeanClickStatus(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0),0,0));
    }
    @Test
    public void clickStatus2() {
        assertEq(S_STA_SIM,callEffectTeamWhileSendFoeBeanClickStatusId(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0),0,0));
    }
}
