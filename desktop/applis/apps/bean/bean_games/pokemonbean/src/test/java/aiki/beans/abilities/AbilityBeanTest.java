package aiki.beans.abilities;

import aiki.game.fight.Fight;
import code.maths.Rate;
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
        assertEq(AikiBeansAbilitiesStd.WEB_HTML_ABILITY_DATA_HTML,callAbilityBeanClickReversePowerTypesAbilities());
    }
    @Test
    public void clickReversePowerTypesAbilities2() {
        assertEq(A_ABILITY2,callAbilityBeanClickReversePowerTypesAbilitiesId());
    }
    @Test
    public void getMapVarsFailEndRound1() {
        assertSizeEq(1,callAbilityBeanMapVarsFailEndRoundGet());
    }
    @Test
    public void getMapVarsFailEndRound2() {
        assertEq(Fight.TEMPS_TOUR,first(elt(callAbilityBeanMapVarsFailEndRoundGet(),0)));
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
        assertEq(Fight.TEMPS_TOUR,elt(callAbilityBeanReasonsEndRoundGet(),0));
    }

}
