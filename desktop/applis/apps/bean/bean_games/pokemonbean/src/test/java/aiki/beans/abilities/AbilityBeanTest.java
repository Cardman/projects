package aiki.beans.abilities;

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
}
