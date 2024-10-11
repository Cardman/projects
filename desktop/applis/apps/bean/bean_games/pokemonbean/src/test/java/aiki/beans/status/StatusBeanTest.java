package aiki.beans.status;

import aiki.db.MessagesDataBaseConstants;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class StatusBeanTest extends InitDbStatus {
    @Test
    public void getDisplayName() {
        assertEq(S_STA_00_TR,callStatusBeanDisplayNameGet(0));
    }
    @Test
    public void isSingle1() {
        assertFalse(callStatusBeanIsSingle(0));
    }
    @Test
    public void isSingle2() {
        assertTrue(callStatusBeanIsSingle(3));
    }
    @Test
    public void incrementEndRoundInt1() {
        assertFalse(callStatusBeanIncrementEndRoundInt(0));
    }
    @Test
    public void incrementEndRoundInt2() {
        assertTrue(callStatusBeanIncrementEndRoundInt(1));
    }
    @Test
    public void getEndRound1() {
        assertFalse(callStatusBeanEndRoundGet(0));
    }
    @Test
    public void getEndRound2() {
        assertTrue(callStatusBeanEndRoundGet(3));
    }
    @Test
    public void getSingleStatus1() {
        assertFalse(callStatusBeanSingleStatusGet(0));
    }
    @Test
    public void getSingleStatus2() {
        assertTrue(callStatusBeanSingleStatusGet(3));
    }
    @Test
    public void getSingleStatus3() {
        assertFalse(callStatusBeanSingleStatusGet(2));
    }
    @Test
    public void getIncrementingDamageByRounds1() {
        assertFalse(callStatusBeanIncrementingDamageByRoundsGet(0));
    }
    @Test
    public void getIncrementingDamageByRounds2() {
        assertFalse(callStatusBeanIncrementingDamageByRoundsGet(3));
    }
    @Test
    public void getIncrementingDamageByRounds3() {
        assertTrue(callStatusBeanIncrementingDamageByRoundsGet(6));
    }
    @Test
    public void getDisabledEffIfSwitch1() {
        assertFalse(callStatusBeanDisabledEffIfSwitchGet(0));
    }
    @Test
    public void getDisabledEffIfSwitch2() {
        assertTrue(callStatusBeanDisabledEffIfSwitchGet(1));
    }
    @Test
    public void getIncrementingEndRound1() {
        assertFalse(callStatusBeanIncrementingEndRoundGet(0));
    }
    @Test
    public void getIncrementingEndRound2() {
        assertTrue(callStatusBeanIncrementingEndRoundGet(1));
    }
    @Test
    public void getIncrementEndRound1() {
        assertEq(0,callStatusBeanIncrementEndRoundGet(0));
    }
    @Test
    public void getIncrementEndRound2() {
        assertEq(1,callStatusBeanIncrementEndRoundGet(1));
    }
    @Test
    public void getNotAttack1() {
        assertFalse(callStatusBeanNotAttackGet(0));
    }
    @Test
    public void getNotAttack2() {
        assertFalse(callStatusBeanNotAttackGet(7));
    }
    @Test
    public void getNotAttack3() {
        assertFalse(callStatusBeanNotAttackGet(10));
    }
    @Test
    public void getNotAttack4() {
        assertTrue(callStatusBeanNotAttackGet(8));
    }
    @Test
    public void getNotAttackFoe1() {
        assertFalse(callStatusBeanNotAttackFoeGet(0));
    }
    @Test
    public void getNotAttackFoe2() {
        assertFalse(callStatusBeanNotAttackFoeGet(7));
    }
    @Test
    public void getNotAttackFoe3() {
        assertFalse(callStatusBeanNotAttackFoeGet(10));
    }
    @Test
    public void getNotAttackFoe4() {
        assertTrue(callStatusBeanNotAttackFoeGet(8));
    }
    @Test
    public void getWeddingAlly1() {
        assertFalse(callEffectPartnerStatusGetWeddingAlly(callStatusBeanGetEffectPartner(12)));
    }
    @Test
    public void getWeddingAlly2() {
        assertTrue(callEffectPartnerStatusGetWeddingAlly(callStatusBeanGetEffectPartner(11)));
    }
    @Test
    public void getWeddingAlly3() {
        assertFalse(callEffectPartnerStatusGetWeddingAlly(elt(callStatusBeanEffectsPartnerGet(12),0)));
    }
    @Test
    public void getWeddingAlly4() {
        assertTrue(callEffectPartnerStatusGetWeddingAlly(elt(callStatusBeanEffectsPartnerGet(11),0)));
    }
    @Test
    public void getAnimStatus() {
        assertEq(two(IMG_00),callStatusBeanAnimStatusGet(0));
    }
    @Test
    public void getEndRoundRank() {
        assertEq(5,callStatusBeanEndRoundRankGet(3));
    }
    @Test
    public void getCatchingRate() {
        assertEq(Rate.one(),callStatusBeanCatchingRateGet(0));
    }
    @Test
    public void getRateForUsingAMove1() {
        assertEq(Rate.zero(),callStatusBeanRateForUsingAMoveGet(0));
    }
    @Test
    public void getRateForUsingAMove2() {
        assertEq(Rate.newRate("25/47"),callStatusBeanRateForUsingAMoveGet(7));
    }
    @Test
    public void getRateForUsingAMove3() {
        assertEq(Rate.zero(),callStatusBeanRateForUsingAMoveGet(8));
    }
    @Test
    public void getRateForUsingAMove4() {
        assertEq(Rate.one(),callStatusBeanRateForUsingAMoveGet(9));
    }
    @Test
    public void getRateForUsingAMoveIfFoe1() {
        assertEq(Rate.zero(),callStatusBeanRateForUsingAMoveIfFoeGet(0));
    }
    @Test
    public void getRateForUsingAMoveIfFoe2() {
        assertEq(Rate.newRate("35/78"),callStatusBeanRateForUsingAMoveIfFoeGet(7));
    }
    @Test
    public void getRateForUsingAMoveIfFoe3() {
        assertEq(Rate.zero(),callStatusBeanRateForUsingAMoveIfFoeGet(8));
    }
    @Test
    public void getRateForUsingAMoveIfFoe4() {
        assertEq(Rate.one(),callStatusBeanRateForUsingAMoveIfFoeGet(9));
    }
    @Test
    public void getRateForFullHealIfMove1() {
        assertEq(Rate.zero(),callStatusBeanRateForFullHealIfMoveGet(0));
    }
    @Test
    public void getRateForFullHealIfMove2() {
        assertEq(Rate.newRate("17/45"),callStatusBeanRateForFullHealIfMoveGet(7));
    }
    @Test
    public void getRateForFullHealIfMove3() {
        assertEq(Rate.zero(),callStatusBeanRateForFullHealIfMoveGet(8));
    }
    @Test
    public void getRateForFullHealIfMove4() {
        assertEq(Rate.zero(),callStatusBeanRateForFullHealIfMoveGet(9));
    }
    @Test
    public void getPower1() {
        assertEq(Rate.zero(),callStatusBeanPowerGet(0));
    }
    @Test
    public void getPower2() {
        assertEq(Rate.zero(),callStatusBeanPowerGet(9));
    }
    @Test
    public void getPower3() {
        assertEq(Rate.one(),callStatusBeanPowerGet(8));
    }
    @Test
    public void getAttack1() {
        assertEq(NULL_REF,callStatusBeanAttackGet(0));
    }
    @Test
    public void getAttack2() {
        assertEq(NULL_REF,callStatusBeanAttackGet(9));
    }
    @Test
    public void getAttack3() {
        assertEq(ST_ATT_TR,callStatusBeanAttackGet(8));
    }
    @Test
    public void getDefense1() {
        assertEq(NULL_REF,callStatusBeanDefenseGet(0));
    }
    @Test
    public void getDefense2() {
        assertEq(NULL_REF,callStatusBeanDefenseGet(9));
    }
    @Test
    public void getDefense3() {
        assertEq(ST_DEF_TR,callStatusBeanDefenseGet(8));
    }
    @Test
    public void getMapVarsFail1() {
        assertSizeEq(1,callStatusBeanMapVarsFailGet(0));
    }
    @Test
    public void getMapVarsFail2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callStatusBeanMapVarsFailGet(0),0)));
    }
    @Test
    public void getMapVarsFail3() {
        assertEq(TIME,second(elt(callStatusBeanMapVarsFailGet(0),0)));
    }
    @Test
    public void getReasons1() {
        assertSizeEq(1,callStatusBeanReasonsGet(0));
    }
    @Test
    public void getReasons2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callStatusBeanReasonsGet(0),0));
    }
    @Test
    public void getMapVarsFailEndRound1() {
        assertSizeEq(1,callStatusBeanMapVarsFailEndRoundGet(3));
    }
    @Test
    public void getMapVarsFailEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callStatusBeanMapVarsFailEndRoundGet(3),0)));
    }
    @Test
    public void getMapVarsFailEndRound3() {
        assertEq(TIME,second(elt(callStatusBeanMapVarsFailEndRoundGet(3),0)));
    }
    @Test
    public void getReasonsEndRound1() {
        assertSizeEq(1,callStatusBeanReasonsEndRoundGet(3));
    }
    @Test
    public void getReasonsEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callStatusBeanReasonsEndRoundGet(3),0));
    }
    @Test
    public void getMultStat1() {
        assertSizeEq(1,callStatusBeanMultStatGet(0));
    }
    @Test
    public void getTrMultStat() {
        assertEq(EV_TR,callStatusBeanGetTrMultStat(0,0));
    }
    @Test
    public void getMultStat2() {
        assertEq(Rate.one(),second(elt(callStatusBeanMultStatGet(0),0)));
    }
    @Test
    public void getLawForUsingAMoveNbRound1() {
        assertSizeEq(2,callStatusBeanLawForUsingAMoveNbRoundGet(7));
    }
    @Test
    public void getLawForUsingAMoveNbRound2() {
        assertEq(LgInt.one(),first(elt(callStatusBeanLawForUsingAMoveNbRoundGet(7),0)));
    }
    @Test
    public void getLawForUsingAMoveNbRound3() {
        assertEq(Rate.newRate("5/8"),second(elt(callStatusBeanLawForUsingAMoveNbRoundGet(7),0)));
    }
    @Test
    public void getLawForUsingAMoveNbRound4() {
        assertEq(LgInt.newLgInt("2"),first(elt(callStatusBeanLawForUsingAMoveNbRoundGet(7),1)));
    }
    @Test
    public void getLawForUsingAMoveNbRound5() {
        assertEq(Rate.newRate("3/8"),second(elt(callStatusBeanLawForUsingAMoveNbRoundGet(7),1)));
    }
    @Test
    public void getMultDamageAgainstFoe() {
        assertEq(Rate.one(),callEffectPartnerStatusGetMultDamageAgainstFoe(callStatusBeanGetEffectPartner(12)));
    }
    @Test
    public void getRestoredHpRateLovedAlly() {
        assertEq(Rate.one(),callEffectPartnerStatusGetRestoredHpRateLovedAlly(callStatusBeanGetEffectPartner(11)));
    }
    @Test
    public void clickIndex() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_STATUS_HTML,callStatusBeanClickIndex(0));
    }
}
