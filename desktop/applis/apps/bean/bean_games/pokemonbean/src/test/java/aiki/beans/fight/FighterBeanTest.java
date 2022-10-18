package aiki.beans.fight;

import aiki.beans.InitDbFight;
import aiki.beans.PkFight;
import aiki.facade.FacadeGame;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import org.junit.Test;

public final class FighterBeanTest extends InitDbFight {

    @Test
    public void name1() {
        assertEq(PIKACHU_TR,callFighterBeanNameGet(playerPath(0)));
    }

    @Test
    public void name2() {
        assertEq(PIKACHU_TR,callFighterBeanNameGet(foePath(0)));
    }

    @Test
    public void belong1() {
        assertTrue(callFighterBeanBelongingToPlayerGet(playerPath(0)));
    }

    @Test
    public void belong2() {
        assertFalse(callFighterBeanBelongingToPlayerGet(foePath(0)));
    }

    @Test
    public void back1() {
        assertTrue(callFighterBeanIsBack(playerPath(4)));
    }

    @Test
    public void back2() {
        assertFalse(callFighterBeanIsBack(playerPath(0)));
    }
    @Test
    public void backSubs1() {
        assertTrue(callFighterBeanIsBackSubst(playerPath(4)));
    }

    @Test
    public void backSubs2() {
        assertFalse(callFighterBeanIsBackSubst(playerPath(0)));
    }
    @Test
    public void enStMove1() {
        assertTrue(callFighterBeanIsEnabled(playerPath(0),8));
    }

    @Test
    public void enStMove2() {
        assertFalse(callFighterBeanIsEnabled(playerPath(1),0));
    }

    @Test
    public void changed1() {
        assertTrue(callFighterBeanChangedGet(playerPath(0)));
    }

    @Test
    public void changed2() {
        assertFalse(callFighterBeanChangedGet(foePath(0)));
    }
    @Test
    public void acted1() {
        assertTrue(callFighterBeanActedGet(playerPath(0)));
    }

    @Test
    public void acted2() {
        assertFalse(callFighterBeanActedGet(foePath(0)));
    }
    @Test
    public void sucMove1() {
        assertTrue(callFighterBeanSuccessfulMoveGet(playerPath(0)));
    }

    @Test
    public void sucMove2() {
        assertFalse(callFighterBeanSuccessfulMoveGet(foePath(0)));
    }
    @Test
    public void useItem1() {
        assertTrue(callFighterBeanUsingItemGet(playerPath(0)));
    }

    @Test
    public void useItem2() {
        assertFalse(callFighterBeanUsingItemGet(foePath(0)));
    }
    @Test
    public void disappeared1() {
        assertTrue(callFighterBeanDisappearedGet(playerPath(0)));
    }

    @Test
    public void disappeared2() {
        assertFalse(callFighterBeanDisappearedGet(foePath(0)));
    }
    @Test
    public void needRec1() {
        assertTrue(callFighterBeanNeedingToRechargeGet(playerPath(0)));
    }

    @Test
    public void needRec2() {
        assertFalse(callFighterBeanNeedingToRechargeGet(foePath(0)));
    }
    private Struct foePath(long..._args) {
        return beanFighter(clickFoeCaller(),_args);
    }

    private Struct playerPath(long..._args) {
        return beanFighter(clickPlayerCaller(),_args);
    }

    private Struct beanFighter(NatCaller _caller,long..._args) {
        FacadeGame facade_ = facadeFighters(dbFighter());
        PkFight stds_ = new PkFight();
        Struct bFighter_ = beanFighter(stds_,EN, facade_);
        Struct bTeam_ = beanTeam(stds_,_caller,facade_);
        transit(stds_, clickTeamFighterCaller(),bTeam_,bFighter_,_args);
        return bFighter_;
    }
}
