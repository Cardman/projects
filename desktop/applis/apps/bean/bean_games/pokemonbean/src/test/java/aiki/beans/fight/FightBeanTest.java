package aiki.beans.fight;

import aiki.facade.*;
import aiki.game.fight.*;
import code.maths.*;
import code.scripts.confs.*;
import org.junit.Test;

public final class FightBeanTest extends InitDbFight {

    @Test
    public void mult() {
        assertEq(2,callFightBeanMultGet(dis(facade(db()))));
    }

    @Test
    public void enMoves() {
        assertSizeEq(2,callFightBeanEnabledMovesGet(dis(facade(db()))));
    }

    @Test
    public void enMoves1() {
        assertEq(CHARGE_TR2,firstActivityOfMove(eltActivityOfMove(callFightBeanEnabledMovesGet(dis(facade(db()))),0)));
    }

    @Test
    public void enMoves2() {
        assertEq(CHARGE_TR,firstActivityOfMove(eltActivityOfMove(callFightBeanEnabledMovesGet(dis(facade(db()))),1)));
    }

    @Test
    public void still1() {
        assertFalse(callFightBeanIsStillEnabled(dis(facade(db())),0));
    }

    @Test
    public void still2() {
        assertTrue(callFightBeanIsStillEnabled(dis(facade(db())),1));
    }

    @Test
    public void roundsCount() {
        assertEq(LgInt.zero(),callFightBeanNbRoundsGet(dis(facade(db()))));
    }

    @Test
    public void winningMoney() {
        assertEq(Rate.zero(),callFightBeanWinningMoneyGet(dis(facade(db()))));
    }

    @Test
    public void nbFlees() {
        assertEq(0,callFightBeanNbFleeAttemptGet(dis(facade(db()))));
    }

    @Test
    public void clickPlayer() {
        FightBean fBean_ = dis(facade(db()));
        assertEq(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,navigateFightPlayer(fBean_));
        assertTrue(fBean_.getForms().contains(NO_TEAM));
        assertEq(Fight.CST_PLAYER,fBean_.getForms().getValInt(NO_TEAM));
    }


    @Test
    public void clickFoe() {
        FightBean fBean_ = dis(facade(db()));
        assertEq(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,navigateFightFoe(fBean_));
        assertTrue(fBean_.getForms().contains(NO_TEAM));
        assertEq(Fight.CST_FOE,fBean_.getForms().getValInt(NO_TEAM));
    }

    @Test
    public void enMovesAdv1() {
        assertFalse(callActivityOfMoveIsIncrementCount(secondActivityOfMove(eltActivityOfMove(callFightBeanEnabledMovesGet(dis(facadeEnMoves(db()))),0))));
    }

    @Test
    public void enMovesAdv2() {
        assertTrue(callActivityOfMoveIsIncrementCount(secondActivityOfMove(eltActivityOfMove(callFightBeanEnabledMovesGet(dis(facadeEnMoves(db()))),1))));
    }

    @Test
    public void enMovesAdv3() {
        assertTrue(callActivityOfMoveIsEnabled(secondActivityOfMove(eltActivityOfMove(callFightBeanEnabledMovesGet(dis(facadeEnMoves(db()))),0))));
    }

    @Test
    public void enMovesAdv4() {
        assertFalse(callActivityOfMoveIsEnabled(secondActivityOfMove(eltActivityOfMove(callFightBeanEnabledMovesGet(dis(facadeEnMoves(db()))),1))));
    }

    @Test
    public void enMovesAdv5() {
        assertEq(1,callActivityOfMoveGetNbTurn(secondActivityOfMove(eltActivityOfMove(callFightBeanEnabledMovesGet(dis(facadeEnMovesAct(db()))),1))));
    }

    private FightBean dis(FacadeGame _facade) {
        FightBean b_ = beanFight(EN, _facade);
        b_.build(_facade);
        return b_;
    }
}
