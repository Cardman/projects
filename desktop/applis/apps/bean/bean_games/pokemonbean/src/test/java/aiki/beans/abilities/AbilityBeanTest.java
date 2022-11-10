package aiki.beans.abilities;

import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.pokemon.AikiBeansPokemonStd;
import aiki.beans.status.AikiBeansStatusStd;
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
        assertEq(Fight.TEMPS_TOUR,callAbilityBeanMultPowerGet());
    }
    @Test
    public void getMultDamage() {
        assertEq(Fight.TEMPS_TOUR,callAbilityBeanMultDamageGet());
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
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickImmuMove());
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
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickBreakProtectionMoves());
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
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickImmuRechargeRoundMoves());
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
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickImmuAllyFromMoves());
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
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickIgnFoeTeamMove());
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
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callAbilityBeanClickWeather());
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
        assertEq(AikiBeansAbilitiesStd.WEB_HTML_ABILITY_DATA_HTML,callAbilityBeanClickIgnAbility());
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
        assertEq(AikiBeansAbilitiesStd.WEB_HTML_ABILITY_DATA_HTML,callAbilityBeanClickImmuAbility());
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
        assertEq(AikiBeansStatusStd.WEB_HTML_STATUS_DATA_HTML,callAbilityBeanClickImmuStatusBeginRound());
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
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML,callAbilityBeanClickPokemon());
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
