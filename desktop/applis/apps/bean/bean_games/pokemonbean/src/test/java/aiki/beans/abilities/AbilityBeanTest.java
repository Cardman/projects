package aiki.beans.abilities;

import aiki.db.MessagesDataBaseConstants;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.CustList;
import code.util.StringList;
import org.junit.Test;

public final class AbilityBeanTest extends InitDbAbility {
    @Test
    public void reverseEffects() {
        StringList ls_ = AbilityBean.reverseEffects(feedDbAbility().getData());
        assertEq(1,ls_.size());
        assertEq(A_ABILITY,ls_.get(0));
    }
    @Test
    public void immuRechargeRoundMoves1() {
        StringList ls_ = AbilityBean.immuRechargeRoundMoves(feedDbAbility(false,"").getData());
        assertEq(0,ls_.size());
    }
    @Test
    public void immuRechargeRoundMoves2() {
        StringList ls_ = AbilityBean.immuRechargeRoundMoves(feedDbAbility().getData());
        assertEq(1,ls_.size());
        assertEq(M_DAM,ls_.get(0));
    }
    @Test
    public void pkLearn1() {
        CustList<String> ls_ = AbilityBean.pkLearn(feedDbAbility().getData(),"");
        assertEq(0,ls_.size());
    }
    @Test
    public void pkLearn2() {
        CustList<String> ls_ = AbilityBean.pkLearn(feedDbAbility().getData(),A_ABILITY);
        assertEq(1,ls_.size());
        assertEq(P_POKEMON,ls_.get(0));
    }
    @Test
    public void getDisplayName() {
        assertEq(A_ABILITY_TR,callAbilityBeanDisplayNameGet());
    }
    @Test
    public void getSending1() {
        assertFalse(callAbilityBeanSendingNoGet());
    }
    @Test
    public void getSending2() {
        assertTrue(callAbilityBeanSendingGet());
    }
    @Test
    public void getEndRound1() {
        assertFalse(callAbilityBeanEndRoundGetNo());
    }
    @Test
    public void getEndRound2() {
        assertTrue(callAbilityBeanEndRoundGet());
    }
    @Test
    public void roundRank1() {
        assertEq(0,callAbilityBeanEndRoundRankGetNo());
    }
    @Test
    public void roundRank2() {
        assertEq(1,callAbilityBeanEndRoundRankGet());
    }
    @Test
    public void multWeight() {
        assertEq(Rate.one(),callEffectWhileSendingBeanMultWeightGet(healSimpleNoStatSend()));
    }
    @Test
    public void getAchievedDisappearedPk1() {
        assertFalse(callAbilityBeanAchievedDisappearedPkGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getAchievedDisappearedPk2() {
        assertTrue(callAbilityBeanAchievedDisappearedPkGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getBreakProtection1() {
        assertFalse(callAbilityBeanBreakProtectionGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getBreakProtection2() {
        assertTrue(callAbilityBeanBreakProtectionGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getCancelSecEffectOther1() {
        assertFalse(callAbilityBeanCancelSecEffectOtherGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,1,1,T_TYPE1)));
    }
    @Test
    public void getCancelSecEffectOther2() {
        assertTrue(callAbilityBeanCancelSecEffectOtherGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getCancelSecEffectOwner1() {
        assertFalse(callAbilityBeanCancelSecEffectOwnerGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,1,1,T_TYPE1)));
    }
    @Test
    public void getCancelSecEffectOwner2() {
        assertTrue(callAbilityBeanCancelSecEffectOwnerGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getChgtTypeByDamage1() {
        assertFalse(callAbilityBeanChgtTypeByDamageGet(directCase(true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getChgtTypeByDamage2() {
        assertTrue(callAbilityBeanChgtTypeByDamageGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getCopyMovesTypes1() {
        assertFalse(callAbilityBeanCopyMovesTypesGet(directCase(true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getCopyMovesTypes2() {
        assertTrue(callAbilityBeanCopyMovesTypesGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getForbidUseBerryAgainstFoes1() {
        assertFalse(callAbilityBeanForbidUseBerryAgainstFoesGet(directCase(false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getForbidUseBerryAgainstFoes2() {
        assertTrue(callAbilityBeanForbidUseBerryAgainstFoesGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getGiveItemToAllyHavingUsed1() {
        assertFalse(callAbilityBeanGiveItemToAllyHavingUsedGet(directCase(true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getGiveItemToAllyHavingUsed2() {
        assertTrue(callAbilityBeanGiveItemToAllyHavingUsedGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getHealedStatusBySwitch1() {
        assertFalse(callAbilityBeanHealedStatusBySwitchGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getHealedStatusBySwitch2() {
        assertTrue(callAbilityBeanHealedStatusBySwitchGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getIgnFoeStatisBoost1() {
        assertFalse(callAbilityBeanIgnFoeStatisBoostGet(directCase(true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getIgnFoeStatisBoost2() {
        assertTrue(callAbilityBeanIgnFoeStatisBoostGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuCh1() {
        assertFalse(callAbilityBeanImmuChGet(directCase(true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuCh2() {
        assertTrue(callAbilityBeanImmuChGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuDamageAllyMoves1() {
        assertFalse(callAbilityBeanImmuDamageAllyMovesGet(directCase(true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuDamageAllyMoves2() {
        assertTrue(callAbilityBeanImmuDamageAllyMovesGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuDamageRecoil1() {
        assertFalse(callAbilityBeanImmuDamageRecoilGet(directCase(true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuDamageRecoil2() {
        assertTrue(callAbilityBeanImmuDamageRecoilGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuDamageTrappingMoves1() {
        assertFalse(callAbilityBeanImmuDamageTrappingMovesGet(directCase(true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuDamageTrappingMoves2() {
        assertTrue(callAbilityBeanImmuDamageTrappingMovesGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuRechargeRound1() {
        assertFalse(callAbilityBeanImmuRechargeRoundGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuRechargeRound2() {
        assertTrue(callAbilityBeanImmuRechargeRoundGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuSufferedDamageLowEff1() {
        assertFalse(callAbilityBeanImmuSufferedDamageLowEffGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getImmuSufferedDamageLowEff2() {
        assertTrue(callAbilityBeanImmuSufferedDamageLowEffGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getInflictingDamageInsteadOfSuffering1() {
        assertFalse(callAbilityBeanInflictingDamageInsteadOfSufferingGet(directCase(true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getInflictingDamageInsteadOfSuffering2() {
        assertTrue(callAbilityBeanInflictingDamageInsteadOfSufferingGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getMumy1() {
        assertFalse(callAbilityBeanMumyGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getMumy2() {
        assertTrue(callAbilityBeanMumyGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getNbHits1() {
        assertFalse(callAbilityBeanNbHitsGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getNbHits2() {
        assertTrue(callAbilityBeanNbHitsGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getPlate1() {
        assertFalse(callAbilityBeanPlateGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getPlate2() {
        assertTrue(callAbilityBeanPlateGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getReverseEffectsPowerMovesTypesGlobal1() {
        assertFalse(callAbilityBeanReverseEffectsPowerMovesTypesGlobalGet(directCase(true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getReverseEffectsPowerMovesTypesGlobal2() {
        assertTrue(callAbilityBeanReverseEffectsPowerMovesTypesGlobalGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getSlowing1() {
        assertFalse(callAbilityBeanSlowingGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getSlowing2() {
        assertTrue(callAbilityBeanSlowingGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getTakeItemByDamagingMove1() {
        assertFalse(callAbilityBeanTakeItemByDamagingMoveGet(directCase(true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getTakeItemByDamagingMove2() {
        assertTrue(callAbilityBeanTakeItemByDamagingMoveGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void decreaseNecStepsHatchInt1() {
        assertFalse(callAbilityBeanDecreaseNecStepsHatchInt(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,0,T_TYPE1)));
    }
    @Test
    public void decreaseNecStepsHatchInt2() {
        assertTrue(callAbilityBeanDecreaseNecStepsHatchInt(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void nbUsedPpInt1() {
        assertFalse(callAbilityBeanNbUsedPpInt(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,0,1,T_TYPE1)));
    }
    @Test
    public void nbUsedPpInt2() {
        assertTrue(callAbilityBeanNbUsedPpInt(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getDecreaseNecStepsHatch() {
        assertEq(1,callAbilityBeanDecreaseNecStepsHatchGet());
    }
    @Test
    public void getNbUsedPp() {
        assertEq(1,callAbilityBeanNbUsedPpGet());
    }
    @Test
    public void getTypeForMoves1() {
        assertEq(NULL_REF,callAbilityBeanTypeForMovesGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,NULL_REF)));
    }
    @Test
    public void getTypeForMoves2() {
        assertEq(T_TYPE1_TR,callAbilityBeanTypeForMovesGet(directCase(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,T_TYPE1)));
    }
    @Test
    public void getMultPower() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callAbilityBeanMultPowerGet());
    }
    @Test
    public void getMultDamage() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callAbilityBeanMultDamageGet());
    }
    @Test
    public void getDefEff() {
        assertEq(Rate.one(),callAbilityBeanDefEffGet());
    }
    @Test
    public void getHealHpWhileUsingBerry() {
        assertEq(Rate.one(),callAbilityBeanHealHpWhileUsingBerryGet());
    }
    @Test
    public void getMaxHpForUsingBerry() {
        assertEq(Rate.one(),callAbilityBeanMaxHpForUsingBerryGet());
    }
    @Test
    public void getMultAllyDamage() {
        assertEq(Rate.one(),callAbilityBeanMultAllyDamageGet());
    }
    @Test
    public void getMultDamageCh() {
        assertEq(Rate.one(),callAbilityBeanMultDamageChGet());
    }
    @Test
    public void getMultEvtRateCh() {
        assertEq(Rate.one(),callAbilityBeanMultEvtRateChGet());
    }
    @Test
    public void getMultEvtRateSecEffectOwner() {
        assertEq(Rate.one(),callAbilityBeanMultEvtRateSecEffectOwnerGet());
    }
    @Test
    public void getMultStab() {
        assertEq(Rate.one(),callAbilityBeanMultStabGet());
    }
    @Test
    public void getMultSufferedDamageSuperEff() {
        assertEq(Rate.one(),callAbilityBeanMultSufferedDamageSuperEffGet());
    }
    @Test
    public void getMultVarBoost() {
        assertEq(Rate.one(),callAbilityBeanMultVarBoostGet());
    }
    @Test
    public void getRecoilDamageFoe() {
        assertEq(Rate.one(),callAbilityBeanRecoilDamageFoeGet());
    }
    @Test
    public void getHealedHpRateBySwitch() {
        assertEq(Rate.one(),callAbilityBeanHealedHpRateBySwitchGet());
    }
    @Test
    public void getImmuLowStat() {
        assertSizeEq(1,callAbilityBeanImmuLowStatGet());
    }
    @Test
    public void getTrImmuLowStat() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrImmuLowStat());
    }
    @Test
    public void getMaxStatisticsIfCh() {
        assertSizeEq(1,callAbilityBeanMaxStatisticsIfChGet());
    }
    @Test
    public void getTrMaxStatisticsIfCh() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMaxStatisticsIfCh());
    }
    @Test
    public void getEfficiencyMoves1() {
        assertSizeEq(1,callAbilityBeanBreakFoeImmuneGet());
    }
    @Test
    public void getEfficiencyMoves2() {
        assertEq(T_TYPE1,callTypesDuoGetDamageType(elt(callAbilityBeanBreakFoeImmuneGet(),0)));
    }
    @Test
    public void getEfficiencyMoves3() {
        assertEq(T_TYPE2,callTypesDuoGetPokemonType(elt(callAbilityBeanBreakFoeImmuneGet(),0)));
    }
    @Test
    public void getTrBreakFoeImmuneKey() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrBreakFoeImmuneKey());
    }
    @Test
    public void getTrBreakFoeImmuneValue() {
        assertEq(T_TYPE2_TR,callAbilityBeanGetTrBreakFoeImmuneValue());
    }
    @Test
    public void getBonusStatRank1() {
        assertSizeEq(1,callAbilityBeanBonusStatRankGet());
    }
    @Test
    public void getBonusStatRank2() {
        assertEq(1,second(elt(callAbilityBeanBonusStatRankGet(),0)));
    }
    @Test
    public void getTrBonusStatRank() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrBonusStatRank());
    }
    @Test
    public void getBoostStatRankEndRound1() {
        assertSizeEq(1,callAbilityBeanBoostStatRankEndRoundGet());
    }
    @Test
    public void getBoostStatRankEndRound2() {
        assertEq(1,second(elt(callAbilityBeanBoostStatRankEndRoundGet(),0)));
    }
    @Test
    public void getTrBoostStatRankEndRound() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrBoostStatRankEndRound());
    }
    @Test
    public void getBoostStatRankProtected1() {
        assertSizeEq(1,callAbilityBeanBoostStatRankProtectedGet());
    }
    @Test
    public void getBoostStatRankProtected2() {
        assertEq(1,second(elt(callAbilityBeanBoostStatRankProtectedGet(),0)));
    }
    @Test
    public void getTrBoostStatRankProtected() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrBoostStatRankProtected());
    }
    @Test
    public void getLowStatFoeHit1() {
        assertSizeEq(1,callAbilityBeanLowStatFoeHitGet());
    }
    @Test
    public void getLowStatFoeHit2() {
        assertEq(1,second(elt(callAbilityBeanLowStatFoeHitGet(),0)));
    }
    @Test
    public void getTrLowStatFoeHit() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrLowStatFoeHit());
    }
    @Test
    public void getMultStatIfKoFoe1() {
        assertSizeEq(1,callAbilityBeanMultStatIfKoFoeGet());
    }
    @Test
    public void getMultStatIfKoFoe2() {
        assertEq(1,second(elt(callAbilityBeanMultStatIfKoFoeGet(),0)));
    }
    @Test
    public void getTrMultStatIfKoFoe() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMultStatIfKoFoe());
    }
    @Test
    public void getMultStatIfLowStat1() {
        assertSizeEq(1,callAbilityBeanMultStatIfLowStatGet());
    }
    @Test
    public void getMultStatIfLowStat2() {
        assertEq(1,second(elt(callAbilityBeanMultStatIfLowStatGet(),0)));
    }
    @Test
    public void getTrMultStatIfLowStat() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMultStatIfLowStat());
    }
    @Test
    public void getMultStatAlly1() {
        assertSizeEq(1,callAbilityBeanMultStatAllyGet());
    }
    @Test
    public void getMultStatAlly2() {
        assertEq(Rate.one(),second(elt(callAbilityBeanMultStatAllyGet(),0)));
    }
    @Test
    public void getTrMultStatAlly() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMultStatAlly());
    }
    @Test
    public void getMultStat1() {
        assertSizeEq(1,callAbilityBeanMultStatGet());
    }
    @Test
    public void getMultStat2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,second(elt(callAbilityBeanMultStatGet(),0)));
    }
    @Test
    public void getTrMultStat() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMultStat());
    }
    @Test
    public void getImmuMove1() {
        assertSizeEq(1,callAbilityBeanImmuMoveGet());
    }
    @Test
    public void getImmuMove2() {
        assertEq(M_DAM,elt(callAbilityBeanImmuMoveGet(),0));
    }
    @Test
    public void getTrImmuMove() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrImmuMove());
    }
    @Test
    public void clickImmuMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickImmuMove());
    }
    @Test
    public void clickImmuMove2() {
        assertEq(M_DAM,callAbilityBeanClickImmuMoveId());
    }
    @Test
    public void getBreakProtectionMoves1() {
        assertSizeEq(1,callAbilityBeanBreakProtectionMovesGet());
    }
    @Test
    public void getBreakProtectionMoves2() {
        assertEq(M_DAM,elt(callAbilityBeanBreakProtectionMovesGet(),0));
    }
    @Test
    public void getTrBreakProtectionMoves() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrBreakProtectionMoves());
    }
    @Test
    public void clickBreakProtectionMoves1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickBreakProtectionMoves());
    }
    @Test
    public void clickBreakProtectionMoves2() {
        assertEq(M_DAM,callAbilityBeanClickBreakProtectionMovesId());
    }
    @Test
    public void getImmuRechargeRoundMoves1() {
        assertSizeEq(1,callAbilityBeanImmuRechargeRoundMovesGet());
    }
    @Test
    public void getImmuRechargeRoundMoves2() {
        assertEq(M_DAM,elt(callAbilityBeanImmuRechargeRoundMovesGet(),0));
    }
    @Test
    public void getTrImmuRechargeRoundMoves() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrImmuRechargeRoundMoves());
    }
    @Test
    public void clickImmuRechargeRoundMoves1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickImmuRechargeRoundMoves());
    }
    @Test
    public void clickImmuRechargeRoundMoves2() {
        assertEq(M_DAM,callAbilityBeanClickImmuRechargeRoundMovesId());
    }
    @Test
    public void getImmuAllyFromMoves1() {
        assertSizeEq(1,callAbilityBeanImmuAllyFromMovesGet());
    }
    @Test
    public void getImmuAllyFromMoves2() {
        assertEq(M_DAM,elt(callAbilityBeanImmuAllyFromMovesGet(),0));
    }
    @Test
    public void getTrImmuAllyFromMoves() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrImmuAllyFromMoves());
    }
    @Test
    public void clickImmuAllyFromMoves1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickImmuAllyFromMoves());
    }
    @Test
    public void clickImmuAllyFromMoves2() {
        assertEq(M_DAM,callAbilityBeanClickImmuAllyFromMovesId());
    }
    @Test
    public void getIgnFoeTeamMove1() {
        assertSizeEq(1,callAbilityBeanIgnFoeTeamMoveGet());
    }
    @Test
    public void getIgnFoeTeamMove2() {
        assertEq(M_DAM,elt(callAbilityBeanIgnFoeTeamMoveGet(),0));
    }
    @Test
    public void getTrIgnFoeTeamMove() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrIgnFoeTeamMove());
    }
    @Test
    public void clickIgnFoeTeamMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickIgnFoeTeamMove());
    }
    @Test
    public void clickIgnFoeTeamMove2() {
        assertEq(M_DAM,callAbilityBeanClickIgnFoeTeamMoveId());
    }
    @Test
    public void getImmuWeather1() {
        assertSizeEq(1,callAbilityBeanImmuWeatherGet());
    }
    @Test
    public void getImmuWeather2() {
        assertEq(M_DAM,elt(callAbilityBeanImmuWeatherGet(),0));
    }
    @Test
    public void getTrImmuWeather() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrWeather());
    }
    @Test
    public void clickImmuWeather1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickWeather());
    }
    @Test
    public void clickImmuWeather2() {
        assertEq(M_DAM,callAbilityBeanClickWeatherId());
    }
    @Test
    public void getIgnAbility1() {
        assertSizeEq(1,callAbilityBeanIgnAbilityGet());
    }
    @Test
    public void getIgnAbility2() {
        assertEq(A_ABILITY2,elt(callAbilityBeanIgnAbilityGet(),0));
    }
    @Test
    public void getTrIgnAbility() {
        assertEq(A_ABILITY2_TR,callAbilityBeanGetTrIgnAbility());
    }
    @Test
    public void clickIgnAbility1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callAbilityBeanClickIgnAbility());
    }
    @Test
    public void clickIgnAbility2() {
        assertEq(A_ABILITY2,callAbilityBeanClickIgnAbilityId());
    }
    @Test
    public void getImmuAbility1() {
        assertSizeEq(1,callAbilityBeanImmuAbilityGet());
    }
    @Test
    public void getImmuAbility2() {
        assertEq(A_ABILITY2,elt(callAbilityBeanImmuAbilityGet(),0));
    }
    @Test
    public void getTrImmuAbility() {
        assertEq(A_ABILITY2_TR,callAbilityBeanGetTrImmuAbility());
    }
    @Test
    public void clickImmuAbility1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callAbilityBeanClickImmuAbility());
    }
    @Test
    public void clickImmuAbility2() {
        assertEq(A_ABILITY2,callAbilityBeanClickImmuAbilityId());
    }
    @Test
    public void getImmuStatusBeginRound1() {
        assertSizeEq(1,callAbilityBeanImmuStatusBeginRoundGet());
    }
    @Test
    public void getImmuStatusBeginRound2() {
        assertEq(S_STA_SIM,elt(callAbilityBeanImmuStatusBeginRoundGet(),0));
    }
    @Test
    public void getTrImmuStatusBeginRound() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrImmuStatusBeginRound());
    }
    @Test
    public void clickImmuStatusBeginRound1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickImmuStatusBeginRound());
    }
    @Test
    public void clickImmuStatusBeginRound2() {
        assertEq(S_STA_SIM,callAbilityBeanClickImmuStatusBeginRoundId());
    }
    @Test
    public void getPokemon1() {
        assertSizeEq(1,callAbilityBeanPokemonGet());
    }
    @Test
    public void getPokemon2() {
        assertEq(P_POKEMON,elt(callAbilityBeanPokemonGet(),0));
    }
    @Test
    public void getTrPokemon() {
        assertEq(P_POKEMON_TR,callAbilityBeanGetTrPokemon());
    }
    @Test
    public void clickPokemon1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callAbilityBeanClickPokemon());
    }
    @Test
    public void clickPokemon2() {
        assertEq(P_POKEMON,callAbilityBeanClickPokemonId());
    }
    @Test
    public void getMultPowerMovesTypesGlobal1() {
        assertSizeEq(1,callAbilityBeanMultPowerMovesTypesGlobalGet());
    }
    @Test
    public void getMultPowerMovesTypesGlobal2() {
        assertEq(T_TYPE1,first(elt(callAbilityBeanMultPowerMovesTypesGlobalGet(),0)));
    }
    @Test
    public void getMultPowerMovesTypesGlobal3() {
        assertEq(Rate.one(),second(elt(callAbilityBeanMultPowerMovesTypesGlobalGet(),0)));
    }
    @Test
    public void getTrMultPowerMovesTypesGlobalKey() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrMultPowerMovesTypesGlobalKey());
    }
    @Test
    public void getMultDamageFoe1() {
        assertSizeEq(1,callAbilityBeanMultDamageFoeGet());
    }
    @Test
    public void getMultDamageFoe2() {
        assertEq(T_TYPE1,first(elt(callAbilityBeanMultDamageFoeGet(),0)));
    }
    @Test
    public void getMultDamageFoe3() {
        assertEq(Rate.one(),second(elt(callAbilityBeanMultDamageFoeGet(),0)));
    }
    @Test
    public void getTrMultDamageFoe() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrMultDamageFoe());
    }
    @Test
    public void getIncreasedPrioTypes1() {
        assertSizeEq(1,callAbilityBeanIncreasedPrioTypesGet());
    }
    @Test
    public void getIncreasedPrioTypes2() {
        assertEq(T_TYPE1,first(elt(callAbilityBeanIncreasedPrioTypesGet(),0)));
    }
    @Test
    public void getIncreasedPrioTypes3() {
        assertEq(1,second(elt(callAbilityBeanIncreasedPrioTypesGet(),0)));
    }
    @Test
    public void getTrIncreasedPrioTypes() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrIncreasedPrioTypes());
    }
    @Test
    public void getIncreasedPrio1() {
        assertSizeEq(1,callAbilityBeanIncreasedPrioGet());
    }
    @Test
    public void getIncreasedPrio2() {
        assertEq(C_CAT,first(elt(callAbilityBeanIncreasedPrioGet(),0)));
    }
    @Test
    public void getIncreasedPrio3() {
        assertEq(1,second(elt(callAbilityBeanIncreasedPrioGet(),0)));
    }
    @Test
    public void getTrIncreasedPrio() {
        assertEq(C_CAT1_TR,callAbilityBeanGetTrIncreasedPrio());
    }
    @Test
    public void getReverseEffectsPowerMovesTypesGlobalAbilities1() {
        assertSizeEq(1,callAbilityBeanReverseEffectsPowerMovesTypesGlobalAbilitiesGet());
    }
    @Test
    public void getReverseEffectsPowerMovesTypesGlobalAbilities2() {
        assertEq(A_ABILITY2,elt(callAbilityBeanReverseEffectsPowerMovesTypesGlobalAbilitiesGet(),0));
    }
    @Test
    public void getTrReversePowerTypesAbilities() {
        assertEq(A_ABILITY2_TR,callAbilityBeanGetTrReversePowerTypesAbilities());
    }
    @Test
    public void clickReversePowerTypesAbilities1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callAbilityBeanClickReversePowerTypesAbilities());
    }
    @Test
    public void clickReversePowerTypesAbilities2() {
        assertEq(A_ABILITY2,callAbilityBeanClickReversePowerTypesAbilitiesId());
    }
    @Test
    public void getDivideStatusRound1() {
        assertSizeEq(1,callAbilityBeanDivideStatusRoundGet());
    }
    @Test
    public void getDivideStatusRound2() {
        assertEq(S_STA_SIM,first(elt(callAbilityBeanDivideStatusRoundGet(),0)));
    }
    @Test
    public void getDivideStatusRound3() {
        assertEq(Rate.one(),second(elt(callAbilityBeanDivideStatusRoundGet(),0)));
    }
    @Test
    public void getTrDivideStatusRoundKey() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrDivideStatusRoundKey());
    }
    @Test
    public void clickDivideStatusRoundKey1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickDivideStatusRoundKey());
    }
    @Test
    public void clickDivideStatusRoundKey2() {
        assertEq(S_STA_SIM,callAbilityBeanClickDivideStatusRoundKeyId());
    }
    @Test
    public void getFailStatus1() {
        assertSizeEq(1,callAbilityBeanFailStatusGet());
    }
    @Test
    public void getFailStatus2() {
        assertEq(S_STA_SIM,first(elt(callAbilityBeanFailStatusGet(),0)));
    }
    @Test
    public void getFailStatus3() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,second(elt(callAbilityBeanFailStatusGet(),0)));
    }
    @Test
    public void getTrFailStatus() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrFailStatus());
    }
    @Test
    public void clickFailStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickFailStatus());
    }
    @Test
    public void clickFailStatus2() {
        assertEq(S_STA_SIM,callAbilityBeanClickFailStatusId());
    }
    @Test
    public void getForwardStatus1() {
        assertSizeEq(1,callAbilityBeanForwardStatusGet());
    }
    @Test
    public void getForwardStatus2() {
        assertEq(S_STA_SIM,first(elt(callAbilityBeanForwardStatusGet(),0)));
    }
    @Test
    public void getForwardStatus3() {
        assertEq(S_STA_REL,second(elt(callAbilityBeanForwardStatusGet(),0)));
    }
    @Test
    public void getTrForwardStatusKey() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrForwardStatusKey());
    }
    @Test
    public void getTrForwardStatusValue() {
        assertEq(S_STA_REL_TR,callAbilityBeanGetTrForwardStatusValue());
    }
    @Test
    public void clickForwardStatusKey1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickForwardStatusKey());
    }
    @Test
    public void clickForwardStatusKey2() {
        assertEq(S_STA_SIM,callAbilityBeanClickForwardStatusKeyId());
    }
    @Test
    public void clickForwardStatusValue1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickForwardStatusValue());
    }
    @Test
    public void clickForwardStatusValue2() {
        assertEq(S_STA_REL,callAbilityBeanClickForwardStatusValueId());
    }
    @Test
    public void getImmuStatusTypes1() {
        assertSizeEq(1,callAbilityBeanImmuStatusTypesGet());
    }
    @Test
    public void getImmuStatusTypes2() {
        assertEq(T_TYPE1,first(elt(callAbilityBeanImmuStatusTypesGet(),0)));
    }
    @Test
    public void getImmuStatusTypes3() {
        assertSizeEq(1,second(elt(callAbilityBeanImmuStatusTypesGet(),0)));
    }
    @Test
    public void getImmuStatusTypes4() {
        assertEq(S_STA_SIM,elt(second(elt(callAbilityBeanImmuStatusTypesGet(),0)),0));
    }
    @Test
    public void getTrImmuStatusTypes() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrImmuStatusTypes());
    }
    @Test
    public void getTrImmuStatusValue() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrImmuStatusValue());
    }
    @Test
    public void clickImmuStatusTypes1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickImmuStatusTypes());
    }
    @Test
    public void clickImmuStatusTypes2() {
        assertEq(S_STA_SIM,callAbilityBeanClickImmuStatusTypesId());
    }
    @Test
    public void getImmuLowStatIfStatus() {
        assertSizeEq(1,callAbilityBeanImmuLowStatIfStatusGet());
    }
    @Test
    public void getTrImmuLowStatIfStatusKey() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrImmuLowStatIfStatusKey());
    }
    @Test
    public void getTrImmuLowStatIfStatusValue() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrImmuLowStatIfStatusValue());
    }
    @Test
    public void clickImmuLowStatIfStatusKey1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickImmuLowStatIfStatusKey());
    }
    @Test
    public void clickImmuLowStatIfStatusKey2() {
        assertEq(S_STA_SIM,callAbilityBeanClickImmuLowStatIfStatusKeyId());
    }
    @Test
    public void getImmuLowStatisTypes1() {
        assertSizeEq(1,callAbilityBeanImmuLowStatisTypesGet());
    }
    @Test
    public void getImmuLowStatisTypes2() {
        assertEq(T_TYPE1,first(elt(callAbilityBeanImmuLowStatisTypesGet(),0)));
    }
    @Test
    public void getImmuLowStatisTypes3() {
        assertSizeEq(1,second(elt(callAbilityBeanImmuLowStatisTypesGet(),0)));
    }
    @Test
    public void getTrImmuLowStatisTypes() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrImmuLowStatisTypes());
    }
    @Test
    public void getTrImmuLowStatisValue() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrImmuLowStatisValue());
    }
    @Test
    public void getChangingBoostTypes1() {
        assertSizeEq(1,callAbilityBeanChangingBoostTypesGet());
    }
    @Test
    public void getChangingBoostTypes2() {
        assertEq(T_TYPE1,first(elt(callAbilityBeanChangingBoostTypesGet(),0)));
    }
    @Test
    public void getChangingBoostTypes3() {
        assertEq(Rate.one(),callTypeDamageBoostGetBoost(second(elt(callAbilityBeanChangingBoostTypesGet(),0))));
    }
    @Test
    public void getTrChangingBoostTypesOld() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrChangingBoostTypesOld());
    }
    @Test
    public void getTrChangingBoostTypesNew() {
        assertEq(T_TYPE2_TR,callAbilityBeanGetTrChangingBoostTypesNew());
    }
    @Test
    public void getMultStatIfDamageCat1() {
        assertSizeEq(1,callAbilityBeanMultStatIfDamageCatGet());
    }
    @Test
    public void getMultStatIfDamageCat2() {
        assertEq(1,second(elt(callAbilityBeanMultStatIfDamageCatGet(),0)));
    }
    @Test
    public void getTrMultStatIfDamageCatKey() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMultStatIfDamageCatKey());
    }
    @Test
    public void getTrMultStatIfDamageCatKeySec() {
        assertEq(C_CAT1_TR,callAbilityBeanGetTrMultStatIfDamageCatKeySec());
    }
    @Test
    public void getMultStatIfCat1() {
        assertSizeEq(1,callAbilityBeanMultStatIfCatGet());
    }
    @Test
    public void getMultStatIfCat2() {
        assertEq(Rate.one(),second(elt(callAbilityBeanMultStatIfCatGet(),0)));
    }
    @Test
    public void getTrMultStatIfCatKey() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMultStatIfCatKey());
    }
    @Test
    public void getTrMultStatIfCatKeySec() {
        assertEq(C_CAT1_TR,callAbilityBeanGetTrMultStatIfCatKeySec());
    }
    @Test
    public void getMultStatIfDamgeType1() {
        assertSizeEq(1,callAbilityBeanMultStatIfDamgeTypeGet());
    }
    @Test
    public void getMultStatIfDamgeType2() {
        assertEq(1,second(elt(callAbilityBeanMultStatIfDamgeTypeGet(),0)));
    }
    @Test
    public void getTrMultStatIfDamgeType() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMultStatIfDamgeType());
    }
    @Test
    public void getTrMultStatIfDamgeTypeSec() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrMultStatIfDamgeTypeSec());
    }
    @Test
    public void getMultStatIfStatutRank1() {
        assertSizeEq(1,callAbilityBeanMultStatIfStatutRankGet());
    }
    @Test
    public void getMultStatIfStatutRank2() {
        assertEq(1,second(elt(callAbilityBeanMultStatIfStatutRankGet(),0)));
    }
    @Test
    public void getTrMultStatIfStatutRank() {
        assertEq(ST_SPEED_TR,callAbilityBeanGetTrMultStatIfStatutRank());
    }
    @Test
    public void getTrMultStatIfStatutRankSec() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrMultStatIfStatutRankSec());
    }
    @Test
    public void clickMultStatIfStatutRankSec1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickMultStatIfStatutRankSec());
    }
    @Test
    public void clickMultStatIfStatutRankSec2() {
        assertEq(S_STA_SIM,callAbilityBeanClickMultStatIfStatutRankSecId());
    }
    @Test
    public void getChgtTypeByWeather1() {
        assertSizeEq(2,callAbilityBeanChgtTypeByWeatherGet());
    }
    @Test
    public void getChgtTypeByWeather2() {
        assertEq(NULL_REF,first(elt(callAbilityBeanChgtTypeByWeatherGet(),0)));
    }
    @Test
    public void getChgtTypeByWeather3() {
        assertEq(T_TYPE1,second(elt(callAbilityBeanChgtTypeByWeatherGet(),0)));
    }
    @Test
    public void getChgtTypeByWeather4() {
        assertEq(M_DAM,first(elt(callAbilityBeanChgtTypeByWeatherGet(),1)));
    }
    @Test
    public void getChgtTypeByWeather5() {
        assertEq(T_TYPE2,second(elt(callAbilityBeanChgtTypeByWeatherGet(),1)));
    }
    @Test
    public void isChgtTypeByWeather1() {
        assertFalse(callAbilityBeanIsChgtTypeByWeather(0));
    }
    @Test
    public void isChgtTypeByWeather2() {
        assertTrue(callAbilityBeanIsChgtTypeByWeather(1));
    }
    @Test
    public void getTrChgtTypeByWeatherKey() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrChgtTypeByWeatherKey());
    }
    @Test
    public void getTrChgtTypeByWeatherValue() {
        assertEq(T_TYPE2_TR,callAbilityBeanGetTrChgtTypeByWeatherValue());
    }
    @Test
    public void clickChgtTypeByWeatherKey1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickChgtTypeByWeatherKey());
    }
    @Test
    public void clickChgtTypeByWeatherKey2() {
        assertEq(M_DAM,callAbilityBeanClickChgtTypeByWeatherKeyId());
    }
    @Test
    public void getHealHpByTypeIfWeather1() {
        assertSizeEq(2,callAbilityBeanHealHpByTypeIfWeatherGet());
    }
    @Test
    public void getHealHpByTypeIfWeather2() {
        assertEq(Rate.one(),second(elt(callAbilityBeanHealHpByTypeIfWeatherGet(),0)));
    }
    @Test
    public void getHealHpByTypeIfWeather3() {
        assertEq(Rate.newRate("2"),second(elt(callAbilityBeanHealHpByTypeIfWeatherGet(),1)));
    }

    @Test
    public void isHealHpByTypeIfWeather1() {
        assertFalse(callAbilityBeanIsHealHpByTypeIfWeather(0));
    }
    @Test
    public void isHealHpByTypeIfWeather2() {
        assertTrue(callAbilityBeanIsHealHpByTypeIfWeather(1));
    }
    @Test
    public void getTrHealHpByTypeIfWeatherKey() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrHealHpByTypeIfWeatherKey());
    }
    @Test
    public void getTrHealHpByTypeIfWeatherKeySec() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrHealHpByTypeIfWeatherKeySec());
    }
    @Test
    public void clickHealHpByTypeIfWeatherKey1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickHealHpByTypeIfWeatherKey());
    }
    @Test
    public void clickHealHpByTypeIfWeatherKey2() {
        assertEq(M_DAM,callAbilityBeanClickHealHpByTypeIfWeatherKeyId());
    }
    @Test
    public void getHealHpByWeather1() {
        assertSizeEq(2,callAbilityBeanHealHpByWeatherGet());
    }
    @Test
    public void getHealHpByWeather2() {
        assertEq(NULL_REF,first(elt(callAbilityBeanHealHpByWeatherGet(),0)));
    }
    @Test
    public void getHealHpByWeather3() {
        assertEq(Rate.one(),second(elt(callAbilityBeanHealHpByWeatherGet(),0)));
    }
    @Test
    public void getHealHpByWeather4() {
        assertEq(M_DAM,first(elt(callAbilityBeanHealHpByWeatherGet(),1)));
    }
    @Test
    public void getHealHpByWeather5() {
        assertEq(Rate.newRate("2"),second(elt(callAbilityBeanHealHpByWeatherGet(),1)));
    }

    @Test
    public void isHealHpByWeather1() {
        assertFalse(callAbilityBeanIsHealHpByWeather(0));
    }
    @Test
    public void isHealHpByWeather2() {
        assertTrue(callAbilityBeanIsHealHpByWeather(1));
    }
    @Test
    public void getTrHealHpByWeatherKey() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrHealHpByWeatherKey());
    }
    @Test
    public void clickHealHpByWeatherKey1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickHealHpByWeatherKey());
    }
    @Test
    public void clickHealHpByWeatherKey2() {
        assertEq(M_DAM,callAbilityBeanClickHealHpByWeatherKeyId());
    }
    @Test
    public void getImmuStatus1() {
        assertSizeEq(2,callAbilityBeanImmuStatusGet());
    }
    @Test
    public void getImmuStatus2() {
        assertEq(NULL_REF,first(elt(callAbilityBeanImmuStatusGet(),0)));
    }
    @Test
    public void getImmuStatus3() {
        assertSizeEq(0,second(elt(callAbilityBeanImmuStatusGet(),0)));
    }
    @Test
    public void getImmuStatus4() {
        assertEq(M_DAM,first(elt(callAbilityBeanImmuStatusGet(),1)));
    }
    @Test
    public void getImmuStatus5() {
        assertSizeEq(1,second(elt(callAbilityBeanImmuStatusGet(),1)));
    }
    @Test
    public void getImmuStatus6() {
        assertEq(S_STA_SIM,elt(second(elt(callAbilityBeanImmuStatusGet(),1)),0));
    }
    @Test
    public void isMoveByStatus1() {
        assertFalse(callAbilityBeanIsMoveByStatus(0));
    }
    @Test
    public void isMoveByStatus2() {
        assertTrue(callAbilityBeanIsMoveByStatus(1));
    }
    @Test
    public void getTrImmuStatusWeather() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrImmuStatusWeather());
    }
    @Test
    public void getTrImmuStatus() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrImmuStatus());
    }
    @Test
    public void clickImmuStatusWeather1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickImmuStatusWeather());
    }
    @Test
    public void clickImmuStatusWeather2() {
        assertEq(M_DAM,callAbilityBeanClickImmuStatusWeatherId());
    }
    @Test
    public void clickImmuStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickImmuStatus());
    }
    @Test
    public void clickImmuStatus2() {
        assertEq(S_STA_SIM,callAbilityBeanClickImmuStatusId());
    }
    @Test
    public void getImmuMoveTypesByWeather1() {
        assertSizeEq(2,callAbilityBeanImmuMoveTypesByWeatherGet());
    }
    @Test
    public void getImmuMoveTypesByWeather2() {
        assertEq(NULL_REF,first(elt(callAbilityBeanImmuMoveTypesByWeatherGet(),0)));
    }
    @Test
    public void getImmuMoveTypesByWeather3() {
        assertSizeEq(0,second(elt(callAbilityBeanImmuMoveTypesByWeatherGet(),0)));
    }
    @Test
    public void getImmuMoveTypesByWeather4() {
        assertEq(M_DAM,first(elt(callAbilityBeanImmuMoveTypesByWeatherGet(),1)));
    }
    @Test
    public void getImmuMoveTypesByWeather5() {
        assertSizeEq(1,second(elt(callAbilityBeanImmuMoveTypesByWeatherGet(),1)));
    }
    @Test
    public void getImmuMoveTypesByWeather6() {
        assertEq(T_TYPE1,elt(second(elt(callAbilityBeanImmuMoveTypesByWeatherGet(),1)),0));
    }
    @Test
    public void isMoveByWeather1() {
        assertFalse(callAbilityBeanIsMoveByWeather(0));
    }
    @Test
    public void isMoveByWeather2() {
        assertTrue(callAbilityBeanIsMoveByWeather(1));
    }
    @Test
    public void getTrImmuMoveByWeather() {
        assertEq(M_DAM_TR,callAbilityBeanGetTrImmuMoveByWeather());
    }
    @Test
    public void getTrImmuTypeByWeather() {
        assertEq(T_TYPE1_TR,callAbilityBeanGetTrImmuTypeByWeather());
    }
    @Test
    public void clickWeather1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickImmuMoveByWeather());
    }
    @Test
    public void clickWeather2() {
        assertEq(M_DAM,callAbilityBeanClickImmuMoveByWeatherId());
    }
    @Test
    public void getSingleStatus1() {
        assertSizeEq(2,callAbilityBeanSingleStatusGet());
    }
    @Test
    public void getSingleStatus2() {
        assertEq(NULL_REF,first(elt(callAbilityBeanSingleStatusGet(),0)));
    }
    @Test
    public void getSingleStatus3() {
        assertEq(Rate.newRate("1/4"),second(elt(callAbilityBeanSingleStatusGet(),0)));
    }
    @Test
    public void getSingleStatus4() {
        assertEq(S_STA_SIM,first(elt(callAbilityBeanSingleStatusGet(),1)));
    }
    @Test
    public void getSingleStatus5() {
        assertEq(Rate.newRate("3/4"),second(elt(callAbilityBeanSingleStatusGet(),1)));
    }
    @Test
    public void isStatus1() {
        assertFalse(callAbilityBeanIsStatus(0));
    }
    @Test
    public void isStatus2() {
        assertTrue(callAbilityBeanIsStatus(1));
    }
    @Test
    public void getTrSingleStatus() {
        assertEq(S_STA_SIM_TR,callAbilityBeanGetTrSingleStatus());
    }
    @Test
    public void clickSingleStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickSingleStatus());
    }
    @Test
    public void clickSingleStatus2() {
        assertEq(S_STA_SIM,callAbilityBeanClickSingleStatusId());
    }
    @Test
    public void getMapVars1() {
        assertSizeEq(1,callAbilityBeanMapVarsGet());
    }
    @Test
    public void getMapVars2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callAbilityBeanMapVarsGet(),0)));
    }
    @Test
    public void getMapVars3() {
        assertEq(TIME,second(elt(callAbilityBeanMapVarsGet(),0)));
    }
    @Test
    public void getMapVarsFailEndRound1() {
        assertSizeEq(1,callAbilityBeanMapVarsFailEndRoundGet());
    }
    @Test
    public void getMapVarsFailEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callAbilityBeanMapVarsFailEndRoundGet(),0)));
    }
    @Test
    public void getMapVarsFailEndRound3() {
        assertEq(TIME,second(elt(callAbilityBeanMapVarsFailEndRoundGet(),0)));
    }
    @Test
    public void getReasonsEndRound1() {
        assertSizeEq(1,callAbilityBeanReasonsEndRoundGet());
    }
    @Test
    public void getReasonsEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callAbilityBeanReasonsEndRoundGet(),0));
    }
    @Test
    public void clickAbilities() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML,callAbilityBeanClickIndex());
    }
}
