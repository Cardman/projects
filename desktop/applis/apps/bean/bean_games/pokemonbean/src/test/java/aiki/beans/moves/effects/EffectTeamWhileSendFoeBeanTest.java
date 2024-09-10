package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class EffectTeamWhileSendFoeBeanTest extends InitDbMoveEffectTeamWhileSendFoe {
    @Test
    public void getDamageRateAgainstFoe1() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callEffectTeamWhileSendFoeBeanDamageRateAgainstFoeGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)));
    }
    @Test
    public void getMapVarsDamageSentFoe1() {
        assertSizeEq(1,callEffectTeamWhileSendFoeBeanMapVarsDamageSentFoeGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)));
    }
    @Test
    public void getMapVarsDamageSentFoe2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectTeamWhileSendFoeBeanMapVarsDamageSentFoeGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0)));
    }
    @Test
    public void getMapVarsDamageSentFoe3() {
        assertEq(TIME,second(elt(callEffectTeamWhileSendFoeBeanMapVarsDamageSentFoeGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0)));
    }
    @Test
    public void getReasonsSending1() {
        assertSizeEq(1,callEffectTeamWhileSendFoeBeanReasonsSendingGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)));
    }
    @Test
    public void getReasonsSending2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectTeamWhileSendFoeBeanReasonsSendingGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0));
    }
    @Test
    public void getMapVarsFailSending1() {
        assertSizeEq(1,callEffectTeamWhileSendFoeBeanMapVarsFailSendingGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)));
    }
    @Test
    public void getMapVarsFailSending2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectTeamWhileSendFoeBeanMapVarsFailSendingGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0)));
    }
    @Test
    public void getMapVarsFailSending3() {
        assertEq(TIME,second(elt(callEffectTeamWhileSendFoeBeanMapVarsFailSendingGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0)));
    }
    @Test
    public void getDeletedByFoeTypes1() {
        assertSizeEq(1,callEffectTeamWhileSendFoeBeanDeletedByFoeTypesGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)));
    }
    @Test
    public void getDeletedByFoeTypes2() {
        assertEq(T_TYPE1,elt(callEffectTeamWhileSendFoeBeanDeletedByFoeTypesGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0));
    }
    @Test
    public void getTranslatedType() {
        assertEq(T_TYPE1_TR,callEffectTeamWhileSendFoeBeanGetTranslatedType(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0),0));
    }
    @Test
    public void getStatistics1() {
        assertSizeEq(1,callEffectTeamWhileSendFoeBeanStatisticsGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)));
    }
    @Test
    public void getStatistics2() {
        assertEq(1,second(elt(callEffectTeamWhileSendFoeBeanStatisticsGet(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0)),0)));
    }
    @Test
    public void getTranslatedStatistic() {
        assertEq(ST_SPEED_TR,callEffectTeamWhileSendFoeBeanGetTranslatedStatistic(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0),0));
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
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectTeamWhileSendFoeBeanClickStatus(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0),0,0));
    }
    @Test
    public void clickStatus2() {
        assertEq(S_STA_SIM,callEffectTeamWhileSendFoeBeanClickStatusId(dispMoveEffTeamSend(feedDbMoveEffDataDam(),0),0,0));
    }
}
