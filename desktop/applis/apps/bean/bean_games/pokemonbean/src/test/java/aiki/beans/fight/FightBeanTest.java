package aiki.beans.fight;

import aiki.beans.InitDbFight;
import aiki.game.fight.ActivityOfMove;
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
}
