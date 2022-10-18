package aiki.beans.fight;

import aiki.beans.InitDbFight;
import aiki.beans.PkFight;
import aiki.facade.FacadeGame;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.maths.Rate;
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
    @Test
    public void enAlly1() {
        assertSizeEq(1,callFighterBeanEnabledMovesForAllyGet(playerPath(0)));
    }

    @Test
    public void enAlly2() {
        assertSizeEq(1,callFighterBeanEnabledMovesForAllyGet(foePath(0)));
    }
    @Test
    public void enAlly3() {
        assertEq(M_ALLY_TR,first(elt(callFighterBeanEnabledMovesForAllyGet(playerPath(0)),0)));
    }

    @Test
    public void enAlly4() {
        assertEq(M_ALLY_TR,first(elt(callFighterBeanEnabledMovesForAllyGet(foePath(0)),0)));
    }
    @Test
    public void enAlly5() {
        assertTrue(second(elt(callFighterBeanEnabledMovesForAllyGet(playerPath(0)),0)));
    }

    @Test
    public void enAlly6() {
        assertFalse(second(elt(callFighterBeanEnabledMovesForAllyGet(foePath(0)),0)));
    }
    @Test
    public void enAcc1() {
        assertSizeEq(16,callFighterBeanIncrUserAccuracyGet(playerPath(0)));
    }

    @Test
    public void enAcc2() {
        assertSizeEq(16,callFighterBeanIncrUserAccuracyGet(foePath(0)));
    }
    @Test
    public void enAcc3() {
        assertEq(M_ACC_TR,callMoveTeamPositionGetMove(first(elt(callFighterBeanIncrUserAccuracyGet(playerPath(0)),0))));
    }

    @Test
    public void enAcc4() {
        assertEq(M_ACC_TR,callMoveTeamPositionGetMove(first(elt(callFighterBeanIncrUserAccuracyGet(foePath(0)),8))));
    }
    @Test
    public void enAcc5() {
        assertTrue(second(elt(callFighterBeanIncrUserAccuracyGet(playerPath(0)),8)));
    }

    @Test
    public void enAcc6() {
        assertFalse(second(elt(callFighterBeanIncrUserAccuracyGet(foePath(0)),0)));
    }

    @Test
    public void curName() {
        assertEq(PIKACHU_TR,callFighterBeanCurrentNameGet(playerPath(0)));
    }

    @Test
    public void curGender() {
        assertEq(NO_G,callFighterBeanCurrentGenderGet(playerPath(0)));
    }

    @Test
    public void gender() {
        assertEq(NO_G,callFighterBeanGenderGet(playerPath(0)));
    }

    @Test
    public void curAbility() {
        assertEq(PARATONNERRE_TR,callFighterBeanCurrentAbilityGet(playerPath(1)));
    }

    @Test
    public void ability() {
        assertEq(PARATONNERRE_TR,callFighterBeanAbilityGet(playerPath(0)));
    }

    @Test
    public void ball() {
        assertEq(I_SAMPLE_TR,callFighterBeanUsedBallCatchingGet(playerPath(0)));
    }

    @Test
    public void ground() {
        assertEq(0,callFighterBeanGroundPlaceGet(playerPath(0)));
    }

    @Test
    public void groundSubst() {
        assertEq(0,callFighterBeanGroundPlaceSubstGet(playerPath(0)));
    }

    @Test
    public void nickname() {
        assertEq(NICK_NA,callFighterBeanNicknameGet(playerPath(0)));
    }

    @Test
    public void item() {
        assertEq(I_SAMPLE_TR,callFighterBeanItemGet(playerPath(0)));
    }

    @Test
    public void lastItem() {
        assertEq(I_SAMPLE_TR,callFighterBeanLastUsedItemGet(playerPath(0)));
    }

    @Test
    public void expItem() {
        assertEq(I_SAMPLE_TR,callFighterBeanExpItemGet(playerPath(0)));
    }

    @Test
    public void lastSuf() {
        assertEq(M_TEAM_TR,callFighterBeanLastSufferedMoveGet(playerPath(0)));
    }

    @Test
    public void lastUsed() {
        assertEq(M_TEAM_TR,callFighterBeanLastUsedMoveGet(playerPath(0)));
    }

    @Test
    public void lastSucc() {
        assertEq(M_TEAM_TR,callFighterBeanLastSuccessfulMoveGet(playerPath(0)));
    }

    @Test
    public void usedMove() {
        assertEq(M_TEAM_TR,callFighterBeanUsedMoveLastRoundGet(playerPath(0)));
    }

    @Test
    public void prepaRounds() {
        assertEq(0,callFighterBeanNbPrepaRoundGet(playerPath(0)));
    }

    @Test
    public void nbRounds() {
        assertEq(LgInt.zero(),callFighterBeanNbRoundsGet(playerPath(0)));
    }

    @Test
    public void repeated() {
        assertEq(LgInt.zero(),callFighterBeanNbRepeatingSuccessfulMovesGet(playerPath(0)));
    }

    @Test
    public void level() {
        assertEq(3,callFighterBeanLevelGet(playerPath(0)));
    }

    @Test
    public void happiness() {
        assertEq(1,callFighterFighterBeanHappinessGet(playerPath(0)));
    }

    @Test
    public void weStr() {
        assertEq("1.0E0",callFighterBeanWeightStrGet(playerPath(0)));
    }

    @Test
    public void we() {
        assertEq(Rate.one(),callFighterBeanWeightGet(playerPath(0)));
    }

    @Test
    public void heStr() {
        assertEq("1.0E0",callFighterBeanHeightStrGet(playerPath(0)));
    }

    @Test
    public void he() {
        assertEq(Rate.one(),callFighterBeanHeightGet(playerPath(0)));
    }

    @Test
    public void clStr() {
        assertEq("0",callFighterBeanCloneStrGet(playerPath(0)));
    }

    @Test
    public void cl() {
        assertEq(Rate.zero(),callFighterBeanCloneGet(playerPath(0)));
    }

    @Test
    public void reStr() {
        assertEq("1.3E1",callFighterBeanRemainingHpStrGet(playerPath(0)));
    }

    @Test
    public void re() {
        assertEq(Rate.newRate("1399/100"),callFighterBeanRemainingHpGet(playerPath(0)));
    }

    @Test
    public void rePerStr() {
        assertEq("1.0E2",callFighterBeanRemainingHpStrPerCentGet(playerPath(0)));
    }

    @Test
    public void wonExp() {
        assertEq(Rate.newRate("0"),callFighterBeanWonExpSinceLastLevelGet(playerPath(0)));
    }

    @Test
    public void necessary() {
        assertEq(Rate.newRate("7"),callFighterBeanNecessaryPointsNextLevelGet(playerPath(0)));
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
