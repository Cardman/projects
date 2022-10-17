package aiki.beans.fight;

import aiki.beans.CommonBean;
import aiki.beans.InitDbFight;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Fight;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public final class FightBeanTest extends InitDbFight {

    @Test
    public void mult() {
        assertEq(2,callFightBeanMultGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void enMoves() {
        assertSizeEq(2,callFightBeanEnabledMovesGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void activityIncr1() {
        assertTrue(callActivityOfMoveIsIncrementCount(new ActivityOfMove(true)));
    }

    @Test
    public void activityIncr2() {
        assertFalse(callActivityOfMoveIsIncrementCount(new ActivityOfMove(false)));
    }

    @Test
    public void activityEn1() {
        ActivityOfMove ac_ = new ActivityOfMove();
        ac_.setEnabled(true);
        assertTrue(callActivityOfMoveIsEnabled(ac_));
    }

    @Test
    public void activityEn2() {
        ActivityOfMove ac_ = new ActivityOfMove();
        ac_.setEnabled(false);
        assertFalse(callActivityOfMoveIsEnabled(ac_));
    }

    @Test
    public void activityRd() {
        ActivityOfMove ac_ = new ActivityOfMove();
        ac_.setNbTurn((short) 1);
        assertEq(1,callActivityOfMoveGetNbTurn(ac_));
    }

    @Test
    public void enMoves1() {
        assertEq(CHARGE_TR2,first(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facade(db())))),0)));
    }

    @Test
    public void enMoves2() {
        assertEq(CHARGE_TR,first(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facade(db())))),1)));
    }

    @Test
    public void still1() {
        assertFalse(callFightBeanIsStillEnabled(displaying(beanFight(EN,facade(db()))),0));
    }

    @Test
    public void still2() {
        assertTrue(callFightBeanIsStillEnabled(displaying(beanFight(EN,facade(db()))),1));
    }

    @Test
    public void roundsCount() {
        assertEq(LgInt.zero(),callFightBeanNbRoundsGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void winningMoney() {
        assertEq(Rate.zero(),callFightBeanWinningMoneyGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void nbFlees() {
        assertEq(0,callFightBeanNbFleeAttemptGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void clickPlayer() {
        Struct fBean_ = displaying(beanFight(EN, facade(db())));
        assertEq(CommonBean.DEST_WEB_FIGHT_HTML_TEAM_HTML,navigateFightPlayer(fBean_));
        assertEq(Fight.CST_PLAYER,forms(fBean_).getValInt(NO_TEAM));
    }

    @Test
    public void clickFoe() {
        Struct fBean_ = displaying(beanFight(EN, facade(db())));
        assertEq(CommonBean.DEST_WEB_FIGHT_HTML_TEAM_HTML,navigateFightFoe(fBean_));
        assertEq(Fight.CST_FOE,forms(fBean_).getValInt(NO_TEAM));
    }

    @Test
    public void enMovesAdv1() {
        assertFalse(callActivityOfMoveIsIncrementCount(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMoves(db())))),0))));
    }

    @Test
    public void enMovesAdv2() {
        assertTrue(callActivityOfMoveIsIncrementCount(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMoves(db())))),1))));
    }

    @Test
    public void enMovesAdv3() {
        assertTrue(callActivityOfMoveIsEnabled(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMoves(db())))),0))));
    }

    @Test
    public void enMovesAdv4() {
        assertFalse(callActivityOfMoveIsEnabled(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMoves(db())))),1))));
    }

    @Test
    public void enMovesAdv5() {
        assertEq(1,callActivityOfMoveGetNbTurn(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMovesAct(db())))),1))));
    }

}
