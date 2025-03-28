package aiki.beans.fight;

import aiki.beans.CommonBean;
import aiki.beans.IntBeanAction;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
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
        assertEq(M_ALLY_TR,firstStringInt(eltStringInt(callFighterBeanEnabledMovesForAllyGet(playerPath(0)),0)));
    }

    @Test
    public void enAlly4() {
        assertEq(M_ALLY_TR,firstStringInt(eltStringInt(callFighterBeanEnabledMovesForAllyGet(foePath(0)),0)));
    }
    @Test
    public void enAlly5() {
        assertTrue(secondStringInt(eltStringInt(callFighterBeanEnabledMovesForAllyGet(playerPath(0)),0)));
    }

    @Test
    public void enAlly6() {
        assertFalse(secondStringInt(eltStringInt(callFighterBeanEnabledMovesForAllyGet(foePath(0)),0)));
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
        assertEq(M_ACC_TR,callMoveTeamPositionGetMove(firstActivityInt(eltIntAcc(callFighterBeanIncrUserAccuracyGet(playerPath(0)),0))));
    }

    @Test
    public void enAcc4() {
        assertEq(M_ACC_TR,callMoveTeamPositionGetMove(firstActivityInt(eltIntAcc(callFighterBeanIncrUserAccuracyGet(foePath(0)),8))));
    }
    @Test
    public void enAcc5() {
        assertTrue(secondActivityInt(eltIntAcc(callFighterBeanIncrUserAccuracyGet(playerPath(0)),8)));
    }

    @Test
    public void enAcc6() {
        assertFalse(secondActivityInt(eltIntAcc(callFighterBeanIncrUserAccuracyGet(foePath(0)),0)));
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
        assertEq("1.0"+Rate.POWER+"0",callFighterBeanWeightStrGet(playerPath(0)));
    }

    @Test
    public void we() {
        assertEq(Rate.one(),callFighterBeanWeightGet(playerPath(0)));
    }

    @Test
    public void heStr() {
        assertEq("1.0"+Rate.POWER+"0",callFighterBeanHeightStrGet(playerPath(0)));
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
        assertEq("1.3"+Rate.POWER+"1",callFighterBeanRemainingHpStrGet(playerPath(0)));
    }

    @Test
    public void re() {
        assertEq(Rate.newRate("1399/100"),callFighterBeanRemainingHpGet(playerPath(0)));
    }

    @Test
    public void full() {
        assertEq(Rate.newRate("1399/100"),callFighterBeanFullHpGet(playerPath(0)));
    }

    @Test
    public void rePerStr() {
        assertEq("1.0"+Rate.POWER+"2",callFighterBeanRemainingHpStrPerCentGet(playerPath(0)));
    }

    @Test
    public void wonExp() {
        assertEq(Rate.newRate("0"),callFighterBeanWonExpSinceLastLevelGet(playerPath(0)));
    }

    @Test
    public void necessary() {
        assertEq(Rate.newRate("7"),callFighterBeanNecessaryPointsNextLevelGet(playerPath(0)));
    }
    @Test
    public void types1() {
        assertSizeEq(1,callFighterBeanTypesGet(playerPath(0)));
    }
    @Test
    public void types2() {
        assertEq(ELECTRICK_TR,elt(callFighterBeanTypesGet(playerPath(0)),0));
    }
    @Test
    public void alreadyInvokedMoves1() {
        assertSizeEq(1,callFighterBeanAlreadyInvokedMovesRoundGet(playerPath(0)));
    }
    @Test
    public void alreadyInvokedMoves2() {
        assertEq(M_TEAM_TR,elt(callFighterBeanAlreadyInvokedMovesRoundGet(playerPath(0)),0));
    }
    @Test
    public void typesProt1() {
        assertSizeEq(1,callFighterBeanProtectedAgainstMoveTypesGet(playerPath(0)));
    }
    @Test
    public void typesProt2() {
        assertEq(ELECTRICK_TR,elt(callFighterBeanProtectedAgainstMoveTypesGet(playerPath(0)),0));
    }
    @Test
    public void sufMovesTypes1() {
        assertSizeEq(1,callFighterBeanLastSufferedMoveTypesGet(playerPath(0)));
    }
    @Test
    public void sufMovesTypes2() {
        assertEq(ELECTRICK_TR,elt(callFighterBeanLastSufferedMoveTypesGet(playerPath(0)),0));
    }
    @Test
    public void moves1() {
        assertSizeEq(2,callFighterBeanMovesGet(playerPath(0)));
    }

    @Test
    public void moves2() {
        assertSizeEq(2,callFighterBeanCurrentMovesGet(playerPath(0)));
    }
    @Test
    public void moves3() {
        assertEq(CHARGE_TR2,firstUsesOfMove(eltUsesOfMove(callFighterBeanMovesGet(playerPath(0)),0)));
    }

    @Test
    public void moves4() {
        assertEq(CHARGE_TR2,firstUsesOfMove(eltUsesOfMove(callFighterBeanCurrentMovesGet(playerPath(0)),0)));
    }
    @Test
    public void moves5() {
        assertEq(CHARGE_TR,firstUsesOfMove(eltUsesOfMove(callFighterBeanMovesGet(playerPath(0)),1)));
    }

    @Test
    public void moves6() {
        assertEq(CHARGE_TR,firstUsesOfMove(eltUsesOfMove(callFighterBeanCurrentMovesGet(playerPath(0)),1)));
    }
    @Test
    public void moves7() {
        assertEq(7,callUsesOfMoveGetCurrent(secondUsesOfMove(eltUsesOfMove(callFighterBeanMovesGet(playerPath(0)),0))));
    }
    @Test
    public void moves8() {
        assertEq(8,callUsesOfMoveGetMax(secondUsesOfMove(eltUsesOfMove(callFighterBeanMovesGet(playerPath(0)),0))));
    }
    @Test
    public void moves9() {
        assertEq(5,callUsesOfMoveGetCurrent(secondUsesOfMove(eltUsesOfMove(callFighterBeanMovesGet(playerPath(0)),1))));
    }
    @Test
    public void moves10() {
        assertEq(6,callUsesOfMoveGetMax(secondUsesOfMove(eltUsesOfMove(callFighterBeanMovesGet(playerPath(0)),1))));
    }
    @Test
    public void nbUses1() {
        assertSizeEq(1,callFighterBeanNbUsesMovesGet(playerPath(0)));
    }
    @Test
    public void nbUses2() {
        assertEq(M_NB_FIGHTER_TR,firstStrNb(eltStrNb(callFighterBeanNbUsesMovesGet(playerPath(0)),0)));
    }
    @Test
    public void nbUses3() {
        assertEq(0,secondStrNb(eltStrNb(callFighterBeanNbUsesMovesGet(playerPath(0)),0)));
    }

    @Test
    public void status1() {
        assertSizeEq(1,callFighterBeanStatusGet(playerPath(0)));
    }
    @Test
    public void status2() {
        assertEq(S_SIMPLE_TR,firstStrNb(eltStrNb(callFighterBeanStatusGet(playerPath(0)),0)));
    }
    @Test
    public void status3() {
        assertEq(0,secondStrNb(eltStrNb(callFighterBeanStatusGet(playerPath(0)),0)));
    }

    @Test
    public void enMoves1() {
        assertSizeEq(7,callFighterBeanEnabledMovesGet(playerPath(0)));
    }
    @Test
    public void enMoves2() {
        assertEq(M_COUNTER_TR,firstActivityOfMove(eltActivityOfMove(callFighterBeanEnabledMovesGet(playerPath(0)),0)));
    }
    @Test
    public void enMoves3() {
        assertEq(M_CST_CHOICE_TR,firstActivityOfMove(eltActivityOfMove(callFighterBeanEnabledMovesGet(playerPath(0)),1)));
    }
    @Test
    public void enMoves4() {
        assertEq(M_END_TR,firstActivityOfMove(eltActivityOfMove(callFighterBeanEnabledMovesGet(playerPath(0)),2)));
    }
    @Test
    public void enMoves5() {
        assertEq(M_PROT_TR,firstActivityOfMove(eltActivityOfMove(callFighterBeanEnabledMovesGet(playerPath(0)),3)));
    }
    @Test
    public void enMoves6() {
        assertEq(M_SWITCH_TR,firstActivityOfMove(eltActivityOfMove(callFighterBeanEnabledMovesGet(playerPath(0)),4)));
    }
    @Test
    public void enMoves7() {
        assertEq(M_TEAM_TR,firstActivityOfMove(eltActivityOfMove(callFighterBeanEnabledMovesGet(playerPath(0)),5)));
    }
    @Test
    public void enMoves8() {
        assertEq(M_UNPROT_TR,firstActivityOfMove(eltActivityOfMove(callFighterBeanEnabledMovesGet(playerPath(0)),6)));
    }
    @Test
    public void stRel1() {
        assertSizeEq(16,callFighterBeanStatusRelatGet(playerPath(0)));
    }
    @Test
    public void stRel2() {
        assertEq(S_RELATION_TR,callMoveTeamPositionGetMove(firstActivityLong(eltLong(callFighterBeanStatusRelatGet(playerPath(0)),0))));
    }
    @Test
    public void stRel3() {
        assertEq(1,secondActivityLong(eltLong(callFighterBeanStatusRelatGet(playerPath(0)),8)));
    }
    @Test
    public void pr1() {
        assertSizeEq(16,callFighterBeanPrivateMovesGet(playerPath(0)));
    }
    @Test
    public void pr2() {
        assertEq(M_CST_CHOICE_TR,callMoveTeamPositionGetMove(firstActivityString(eltString(callFighterBeanPrivateMovesGet(playerPath(0)),0))));
    }
    @Test
    public void pr3() {
        assertEq(M_TEAM_TR,secondActivityString(eltString(callFighterBeanPrivateMovesGet(playerPath(0)),8)));
    }
    @Test
    public void trapp1() {
        assertSizeEq(16,callFighterBeanTrappingMovesGet(playerPath(0)));
    }
    @Test
    public void trapp2() {
        assertEq(M_TRACK_TR,callMoveTeamPositionGetMove(firstActivityOfMoveStill(eltActivityOfMoveStill(callFighterBeanTrappingMovesGet(playerPath(0)),0))));
    }
    @Test
    public void track1() {
        assertSizeEq(16,callFighterBeanTrckingMovesGet(playerPath(0)));
    }
    @Test
    public void track2() {
        assertEq(M_USE_ACTION_TR,callMoveTeamPositionGetMove(firstAffectedMove(eltAffectedMove(callFighterBeanTrckingMovesGet(playerPath(0)),0))));
    }
    @Test
    public void track3() {
        assertEq(M_TEAM_TR,callAffectedMoveGetMove(secondAffectedMove(eltAffectedMove(callFighterBeanTrckingMovesGet(playerPath(0)),8))));
    }
    @Test
    public void track4() {
        assertTrue(callActivityOfMoveIsEnabled(callAffectedMoveGetActivity(secondAffectedMove(eltAffectedMove(callFighterBeanTrckingMovesGet(playerPath(0)),8)))));
    }
    @Test
    public void cpMoves1() {
        assertSizeEq(1,callFighterBeanCopiedMovesGet(playerPath(0)));
    }
    @Test
    public void cpMoves2() {
        assertEq(M_COPY_TR,firstCopiedMove(eltCopiedMove(callFighterBeanCopiedMovesGet(playerPath(0)),0)));
    }
    @Test
    public void cpMoves3() {
        assertEq(M_TEAM_TR,callCopiedMoveGetMove(secondCopiedMove(eltCopiedMove(callFighterBeanCopiedMovesGet(playerPath(0)),0))));
    }
    @Test
    public void cpMoves4() {
        assertEq(3,callCopiedMoveGetPp(secondCopiedMove(eltCopiedMove(callFighterBeanCopiedMovesGet(playerPath(0)),0))));
    }
    @Test
    public void multMoves1() {
        assertSizeEq(3,callFighterBeanDamageRateByTypeGet(playerPath(0)));
    }
    @Test
    public void multMoves2() {
        assertEq(ELECTRICK_TR,firstMultPowerMoves(eltMultPowerMoves(callFighterBeanDamageRateByTypeGet(playerPath(0)),0)));
    }
    @Test
    public void multMoves3() {
        assertEq(Rate.one(),callMultPowerMovesGetMultInflicted(secondMultPowerMoves(eltMultPowerMoves(callFighterBeanDamageRateByTypeGet(playerPath(0)),0))));
    }
    @Test
    public void multMoves4() {
        assertEq(Rate.one(),callMultPowerMovesGetMultSuffering(secondMultPowerMoves(eltMultPowerMoves(callFighterBeanDamageRateByTypeGet(playerPath(0)),0))));
    }
    @Test
    public void sufCat1() {
        assertSizeEq(1,callFighterBeanDamageSufferedCategGet(playerPath(0)));
    }
    @Test
    public void sufCat2() {
        assertEq(SPEC_TR,firstSufferedDamageCategory(eltSufferedDamageCategory(callFighterBeanDamageSufferedCategGet(playerPath(0)),0)));
    }
    @Test
    public void sufCat3() {
        assertEq(Rate.zero(),callSufferedDamageCategoryGetRound(secondSufferedDamageCategory(eltSufferedDamageCategory(callFighterBeanDamageSufferedCategGet(playerPath(0)),0))));
    }
    @Test
    public void sufCat4() {
        assertEq(Rate.zero(),callSufferedDamageCategoryGetUsing(secondSufferedDamageCategory(eltSufferedDamageCategory(callFighterBeanDamageSufferedCategGet(playerPath(0)),0))));
    }
    @Test
    public void stats1() {
        assertSizeEq(9,callFighterBeanStatisticsGet(playerPath(0)));
    }
    @Test
    public void stats2() {
        assertEq(SPEED_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),8)));
    }
    @Test
    public void stats3() {
        assertTrue(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),8)));
    }
    @Test
    public void stats4() {
        assertTrue(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),8)));
    }
    @Test
    public void stats5() {
        assertFalse(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),7)));
    }
    @Test
    public void stats6() {
        assertTrue(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),7)));
    }
    @Test
    public void stats7() {
        assertTrue(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),6)));
    }
    @Test
    public void stats8() {
        assertFalse(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),6)));
    }
    @Test
    public void stats9() {
        assertEq(CRIT_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),7)));
    }
    @Test
    public void stats10() {
        assertEq(HP_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),6)));
    }
    @Test
    public void stats11() {
        assertEq(0,callStatisticInfoGetEv(eltStat(callFighterBeanStatisticsGet(playerPath(0)),8)));
    }
    @Test
    public void stats12() {
        assertEq(31,callStatisticInfoGetIv(eltStat(callFighterBeanStatisticsGet(playerPath(0)),8)));
    }
    @Test
    public void stats13() {
        assertEq(0,callStatisticInfoGetStatisBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),8)));
    }
    @Test
    public void stats14() {
        assertEq(Rate.one(),callStatisticInfoGetStatisBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),8)));
    }
    @Test
    public void stats15() {
        assertTrue(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),0)));
    }
    @Test
    public void stats16() {
        assertFalse(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),0)));
    }
    @Test
    public void stats17() {
        assertEq(ACC_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),0)));
    }
    @Test
    public void stats18() {
        assertTrue(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),1)));
    }
    @Test
    public void stats19() {
        assertTrue(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),1)));
    }
    @Test
    public void stats20() {
        assertEq(ATT_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),1)));
    }
    @Test
    public void stats21() {
        assertTrue(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),2)));
    }
    @Test
    public void stats22() {
        assertTrue(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),2)));
    }
    @Test
    public void stats23() {
        assertEq(SPE_DEF_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),2)));
    }
    @Test
    public void stats24() {
        assertTrue(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),3)));
    }
    @Test
    public void stats25() {
        assertTrue(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),3)));
    }
    @Test
    public void stats26() {
        assertEq(SPE_ATT_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),3)));
    }
    @Test
    public void stats27() {
        assertTrue(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),4)));
    }
    @Test
    public void stats28() {
        assertTrue(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),4)));
    }
    @Test
    public void stats29() {
        assertEq(DEF_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),4)));
    }
    @Test
    public void stats30() {
        assertTrue(callStatisticInfoIsBoost(eltStat(callFighterBeanStatisticsGet(playerPath(0)),5)));
    }
    @Test
    public void stats31() {
        assertFalse(callStatisticInfoIsBase(eltStat(callFighterBeanStatisticsGet(playerPath(0)),5)));
    }
    @Test
    public void stats32() {
        assertEq(EVA_TR,callStatisticInfoGetDisplayStatistic(eltStat(callFighterBeanStatisticsGet(playerPath(0)),5)));
    }
    @Test
    public void isFoeIncr1() {
        assertTrue(callFighterBeanIsFoeIncrUserAccuracyTeam(playerPath(0),8));
    }
    @Test
    public void isFoeIncr2() {
        assertFalse(callFighterBeanIsFoeIncrUserAccuracyTeam(foePath(0),0));
    }
    @Test
    public void isFoePr1() {
        assertTrue(callFighterBeanIsFoePrivateMovesTeam(playerPath(0),8));
    }
    @Test
    public void isFoePr2() {
        assertFalse(callFighterBeanIsFoePrivateMovesTeam(foePath(0),0));
    }
    @Test
    public void isFoeSt1() {
        assertTrue(callFighterBeanIsFoeStatusRelatTeam(playerPath(0),8));
    }
    @Test
    public void isFoeSt2() {
        assertFalse(callFighterBeanIsFoeStatusRelatTeam(foePath(0),0));
    }
    @Test
    public void isFoeTk1() {
        assertTrue(callFighterBeanIsFoeTrackingMovesTeam(playerPath(0),8));
    }
    @Test
    public void isFoeTk2() {
        assertFalse(callFighterBeanIsFoeTrackingMovesTeam(foePath(0),0));
    }
    @Test
    public void isFoeTp1() {
        assertTrue(callFighterBeanIsFoeTrappingMovesTeam(playerPath(0),8));
    }
    @Test
    public void isFoeTp2() {
        assertFalse(callFighterBeanIsFoeTrappingMovesTeam(foePath(0),0));
    }
    @Test
    public void getFoeIncr1() {
        assertEq(PIKACHU_TR,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),0));
    }
    @Test
    public void getFoeIncr2() {
        assertEq(PIKA_2_TR,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),1));
    }
    @Test
    public void getFoeIncr3() {
        assertEq(PIKACHU_TR+" "+1,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),2));
    }
    @Test
    public void getFoeIncr4() {
        assertEq(PIKA_2_TR +" "+1,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),3));
    }
    @Test
    public void getFoeIncr5() {
        assertEq(PIKACHU_TR+" "+2,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),4));
    }
    @Test
    public void getFoeIncr6() {
        assertEq(PIKA_2_TR +" "+2,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),5));
    }
    @Test
    public void getFoeIncr7() {
        assertEq(PIKACHU_TR+" "+3,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),6));
    }
    @Test
    public void getFoeIncr8() {
        assertEq(PIKA_2_TR +" "+3,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),7));
    }
    @Test
    public void getFoeIncr9() {
        assertEq(PIKACHU_TR,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),8));
    }
    @Test
    public void getFoeIncr10() {
        assertEq(PIKA_2_TR,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),9));
    }
    @Test
    public void getFoeIncr11() {
        assertEq(PIKACHU_TR+" "+1,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),10));
    }
    @Test
    public void getFoeIncr12() {
        assertEq(PIKA_2_TR +" "+1,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),11));
    }
    @Test
    public void getFoeIncr13() {
        assertEq(PIKACHU_TR+" "+2,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),12));
    }
    @Test
    public void getFoeIncr14() {
        assertEq(PIKA_2_TR +" "+2,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),13));
    }
    @Test
    public void getFoeIncr15() {
        assertEq(PIKACHU_TR+" "+3,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),14));
    }
    @Test
    public void getFoeIncr16() {
        assertEq(PIKA_2_TR +" "+3,callFighterBeanGetIncrUserAccuracyTeam(playerPath(0),15));
    }
    @Test
    public void getFoePr() {
        assertEq(PIKACHU_TR,callFighterBeanGetIncrPrivateMovesTeam(playerPath(0),0));
    }
    @Test
    public void getFoeTk() {
        assertEq(PIKACHU_TR,callFighterBeanGetIncrTrackingMovesTeam(playerPath(0),0));
    }
    @Test
    public void getFoeTp() {
        assertEq(PIKACHU_TR,callFighterBeanGetIncrTrappingMovesTeam(playerPath(0),0));
    }
    @Test
    public void getFoeSt() {
        assertEq(PIKACHU_TR, callFighterBeanGetStatusRelatTeam(playerPath(0),0));
    }
    private FighterBean foePath(int..._args) {
        return beanFighter(Fight.CST_FOE,_args);
    }

    private FighterBean playerPath(int..._args) {
        return beanFighter(Fight.CST_PLAYER,_args);
    }

    private FighterBean beanFighter(int _caller,int..._args) {
        FacadeGame facade_ = facadeFighters(dbFighter());
        FighterBean bFighter_ = new FighterBean();
        update(facade_, bFighter_);
        TeamBean bTeam_ = beanTeam(_caller,facade_);
        bTeam_.getBuilder().setRenders(bTeam_.getBuilder().getRenders());
        bTeam_.getBuilder().getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_FIGHTER_HTML,bFighter_);
        bFighter_.setBuilder(bTeam_.getBuilder());
        IntBeanAction anc_ = bTeam_.getBuilder().getAnchors().get(_args[0] + 2);
        bTeam_.getBuilder().build(anc_);
        return bFighter_;
    }
}
