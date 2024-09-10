package aiki.beans.moves.effects;

import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class EffectTeamBeanTest extends InitDbMoveEffectTeam {
    @Test
    public void getForbiddingHealing1() {
        assertTrue(callEffectTeamBeanForbiddingHealingGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getForbiddingHealing2() {
        assertFalse(callEffectTeamBeanForbiddingHealingGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(false,true)),0)));
    }
    @Test
    public void getProtectAgainstCh1() {
        assertTrue(callEffectTeamBeanProtectAgainstChGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getProtectAgainstCh2() {
        assertFalse(callEffectTeamBeanProtectAgainstChGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,false)),0)));
    }
    @Test
    public void getDefaultBoost() {
        assertEq(1,callEffectTeamBeanDefaultBoostGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getForbiddenBoost1() {
        assertSizeEq(1,callEffectTeamBeanForbiddenBoostGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getForbiddenBoost2() {
        assertEq(ST_SPEED_TR,elt(callEffectTeamBeanForbiddenBoostGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0));
    }
    @Test
    public void getCancelChgtStatTeam1() {
        assertSizeEq(1,callEffectTeamBeanCancelChgtStatTeamGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getCancelChgtStatTeam2() {
        assertEq(ST_SPEED_TR,elt(callEffectTeamBeanCancelChgtStatTeamGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0));
    }
    @Test
    public void getCancelChgtStatFoeTeam1() {
        assertSizeEq(1,callEffectTeamBeanCancelChgtStatFoeTeamGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getCancelChgtStatFoeTeam2() {
        assertEq(ST_SPEED_TR,elt(callEffectTeamBeanCancelChgtStatFoeTeamGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0));
    }
    @Test
    public void getProtectAgainstLowStat1() {
        assertSizeEq(1,callEffectTeamBeanProtectAgainstLowStatGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getProtectAgainstLowStat2() {
        assertEq(ST_SPEED_TR,elt(callEffectTeamBeanProtectAgainstLowStatGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0));
    }
    @Test
    public void getProtectAgainstStatus1() {
        assertSizeEq(1,callEffectTeamBeanProtectAgainstStatusGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getProtectAgainstStatus2() {
        assertEq(S_STA_SIM,elt(callEffectTeamBeanProtectAgainstStatusGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0));
    }
    @Test
    public void getDisableFoeTeamStatus1() {
        assertSizeEq(1,callEffectTeamBeanDisableFoeTeamStatusGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getDisableFoeTeamStatus2() {
        assertEq(S_STA_SIM,elt(callEffectTeamBeanDisableFoeTeamStatusGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0));
    }
    @Test
    public void getUnusableMoves1() {
        assertSizeEq(1,callEffectTeamBeanUnusableMovesGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getUnusableMoves2() {
        assertEq(M_STA,elt(callEffectTeamBeanUnusableMovesGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0));
    }
    @Test
    public void getDisableFoeTeamEffects1() {
        assertSizeEq(1,callEffectTeamBeanDisableFoeTeamEffectsGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getDisableFoeTeamEffects2() {
        assertEq(M_STA,elt(callEffectTeamBeanDisableFoeTeamEffectsGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0));
    }
    @Test
    public void getMultStatistic1() {
        assertSizeEq(1,callEffectTeamBeanMultStatisticGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getMultStatistic2() {
        assertEq(ST_SPEED_TR,first(elt(callEffectTeamBeanMultStatisticGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0)));
    }
    @Test
    public void getMultStatistic3() {
        assertEq(Rate.one(),second(elt(callEffectTeamBeanMultStatisticGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0)));
    }
    @Test
    public void getMultStatisticFoe1() {
        assertSizeEq(1,callEffectTeamBeanMultStatisticFoeGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getMultStatisticFoe2() {
        assertEq(ST_SPEED_TR,first(elt(callEffectTeamBeanMultStatisticFoeGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0)));
    }
    @Test
    public void getMultStatisticFoe3() {
        assertEq(Rate.one(),second(elt(callEffectTeamBeanMultStatisticFoeGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0)));
    }
    @Test
    public void getMultDamage1() {
        assertSizeEq(1,callEffectTeamBeanMultDamageGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)));
    }
    @Test
    public void getMultDamage2() {
        assertEq(C_CAT2_TR,callCategoryMultGetCategory(first(elt(callEffectTeamBeanMultDamageGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0))));
    }
    @Test
    public void getMultDamage3() {
        assertEq(1,callCategoryMultGetMult(first(elt(callEffectTeamBeanMultDamageGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0))));
    }
    @Test
    public void getMultDamage4() {
        assertEq(Rate.one(),second(elt(callEffectTeamBeanMultDamageGet(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0)),0)));
    }
    @Test
    public void getTrStatus() {
        assertEq(S_STA_SIM_TR,callEffectTeamBeanGetTrStatus(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0));
    }
    @Test
    public void clickStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectTeamBeanClickStatus(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0,0));
    }
    @Test
    public void clickStatus2() {
        assertEq(S_STA_SIM,callEffectTeamBeanClickStatusId(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0,0));
    }
    @Test
    public void getTrDisableFoeTeamStatus() {
        assertEq(S_STA_SIM_TR,callEffectTeamBeanGetTrDisableFoeTeamStatus(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0));
    }
    @Test
    public void clickDisableFoeTeamStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectTeamBeanClickDisableFoeTeamStatus(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0,0));
    }
    @Test
    public void clickDisableFoeTeamStatus2() {
        assertEq(S_STA_SIM,callEffectTeamBeanClickDisableFoeTeamStatusId(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0,0));
    }
    @Test
    public void getTrUnusableMove() {
        assertEq(M_STA_TR,callEffectTeamBeanGetTrUnusableMove(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0));
    }
    @Test
    public void clickUnusableMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectTeamBeanClickUnusableMove(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0,0));
    }
    @Test
    public void clickUnusableMove2() {
        assertEq(M_STA,callEffectTeamBeanClickUnusableMoveId(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0,0));
    }
    @Test
    public void getTrDisableFoeTeamEffects() {
        assertEq(M_STA_TR,callEffectTeamBeanGetTrDisableFoeTeamEffects(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0));
    }
    @Test
    public void clickDisableFoeTeamEffects1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectTeamBeanClickDisableFoeTeamEffects(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0,0));
    }
    @Test
    public void clickDisableFoeTeamEffects2() {
        assertEq(M_STA,callEffectTeamBeanClickDisableFoeTeamEffectsId(dispMoveEffTeam(feedDbMoveEffDataDam(eff(true,true)),0),0,0));
    }
}

