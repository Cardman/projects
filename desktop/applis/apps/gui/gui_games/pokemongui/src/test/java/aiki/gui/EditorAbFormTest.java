package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.util.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import code.maths.Rate;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorAbFormTest extends InitEditorPkForm {
    @Test
    public void abForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        g_.getMultDamage().setupValue(A_2);
        g_.getMultPower().setupValue(A_3);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getAbilities().size());
        assertEq(1,c_.getList().size());
        assertEq(A_1,c_.getList().get(0).getKey());
        assertEq(A_2,c_.getList().get(0).getValue().getMultDamage());
        assertEq(A_3,c_.getList().get(0).getValue().getMultPower());
    }
    @Test
    public void abForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        g_.getMultDamage().setupValue(A_2);
        g_.getMultPower().setupValue(A_3);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelAbilityData gSec_ = (GeneComponentModelAbilityData)c_.getGene();
        assertEq(A_2,gSec_.getMultDamage().tryRet());
        assertEq(A_3,gSec_.getMultPower().tryRet());
    }
    @Test
    public void abForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        g_.getMultDamage().setupValue(A_2);
        g_.getMultPower().setupValue(A_3);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelAbilityData gSec_ = (GeneComponentModelAbilityData)c_.getGene();
        gSec_.getMultDamage().setupValue(A_3);
        gSec_.getMultPower().setupValue(A_2);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getAbilities().size());
        assertEq(A_2, facade_.getData().getAbilities().getVal(A_1).getMultPower());
        assertEq(A_3, facade_.getData().getAbilities().getVal(A_1).getMultDamage());
    }
    @Test
    public void abForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getAbilities().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void abForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelAbilityData) cm_.getGene()).getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(cm_.getValidAddEdit());
        CrudGeneFormEnt<PokemonData> c_ = crudPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getAbilities().setupValue(new StringList(A_1));
        tryClick(c_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getValidRemove());
        assertEq(1, facade_.getData().getAbilities().size());
        assertEq(1, cm_.getList().size());
    }
    @Test
    public void abForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq("p_2",facade_.getData().getTranslatedAbilities().firstValue().getVal(A_2));
    }
    @Test
    public void abForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(A_4);
        enterTextField(cTr_.getDestination());
        assertTrue(facade_.getData().getAbilities().contains(A_4));
    }
    @Test
    public void abForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(A_1);
        enterTextField(cTr_.getDestination());
        assertTrue(facade_.getData().getAbilities().contains(A_1));
        assertTrue(facade_.getData().getAbilities().contains(A_2));
    }
    @Test
    public void abForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getEffectSending().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getWithEffect().setSelected(true);
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getCopyingAbility().setSelected(true);
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getDisableWeather().setSelected(true);
        tryClick(g_.getEffectSending().getCrud().getValidAddEdit());
        tryClick(g_.getEffectSending().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getWithEffect().setSelected(false);
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getCopyingAbility().setSelected(false);
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getDisableWeather().setSelected(false);
        tryClick(g_.getEffectSending().getCrud().getValidAddEdit());
        tryClick(g_.getEffectSending().getCrud().getAllButtons().get(0));
        tryClick(g_.getEffectSending().getCrud().getCancel());
        tryClick(g_.getEffectSending().getCrud().getAllButtons().get(1));
        tryClick(g_.getEffectSending().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).isWithEffect());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).getCopyingAbility());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).getDisableWeather());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).isWithEffect());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).getCopyingAbility());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).getDisableWeather());
    }
    @Test
    public void abForm10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRound)g_.getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentGroupEffectEndRound().getContentEffectEndRound().getFailEndRound().setupValue("_");
        tryClick(g_.getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(g_.getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(g_.getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq("_",facade_.getData().getAbilities().getVal(A_1).getEffectEndRound().get(0).getFailEndRound());
    }
    @Test
    public void abForm11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        g_.getForbidUseBerryAgainstFoes().setSelected(true);
        g_.getChgtTypeByDamage().setSelected(true);
        g_.getIgnFoeStatisBoost().setSelected(true);
        g_.getImmuCh().setSelected(true);
        g_.getImmuDamageTrappingMoves().setSelected(true);
        g_.getImmuDamageAllyMoves().setSelected(true);
        g_.getImmuDamageRecoil().setSelected(true);
        g_.getImmuRechargeRound().setSelected(true);
        g_.getSlowing().setSelected(true);
        g_.getImmuSufferedDamageLowEff().setSelected(true);
        g_.getCancelSecEffectOther().setSelected(true);
        g_.getCancelSecEffectOwner().setSelected(true);
        g_.getInflictingDamageInsteadOfSuffering().setSelected(true);
        g_.getNbHits().setSelected(true);
        g_.getBreakProtection().setSelected(true);
        g_.getPlate().setSelected(true);
        g_.getHealedStatusBySwitch().setSelected(true);
        g_.getAchievedDisappearedPk().setSelected(true);
        g_.getMumy().setSelected(true);
        g_.getCopyMovesTypes().setSelected(true);
        g_.getReverseEffectsPowerMovesTypesGlobal().setSelected(true);
        g_.getTakeItemByDamagingMove().setSelected(true);
        g_.getGiveItemToAllyHavingUsed().setSelected(true);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isForbidUseBerryAgainstFoes());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isChgtTypeByDamage());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isIgnFoeStatisBoost());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isImmuCh());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isImmuDamageTrappingMoves());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isImmuDamageAllyMoves());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isImmuDamageRecoil());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isImmuRechargeRound());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isSlowing());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isImmuSufferedDamageLowEff());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isCancelSecEffectOther());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isCancelSecEffectOwner());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isInflictingDamageInsteadOfSuffering());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isNbHits());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isBreakProtection());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isPlate());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isHealedStatusBySwitch());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isAchievedDisappearedPk());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isMumy());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isCopyMovesTypes());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isReverseEffectsPowerMovesTypesGlobal());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isTakeItemByDamagingMove());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).isGiveItemToAllyHavingUsed());
    }
    @Test
    public void abForm12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        g_.getForbidUseBerryAgainstFoes().setSelected(false);
        g_.getChgtTypeByDamage().setSelected(false);
        g_.getIgnFoeStatisBoost().setSelected(false);
        g_.getImmuCh().setSelected(false);
        g_.getImmuDamageTrappingMoves().setSelected(false);
        g_.getImmuDamageAllyMoves().setSelected(false);
        g_.getImmuDamageRecoil().setSelected(false);
        g_.getImmuRechargeRound().setSelected(false);
        g_.getSlowing().setSelected(false);
        g_.getImmuSufferedDamageLowEff().setSelected(false);
        g_.getCancelSecEffectOther().setSelected(false);
        g_.getCancelSecEffectOwner().setSelected(false);
        g_.getInflictingDamageInsteadOfSuffering().setSelected(false);
        g_.getNbHits().setSelected(false);
        g_.getBreakProtection().setSelected(false);
        g_.getPlate().setSelected(false);
        g_.getHealedStatusBySwitch().setSelected(false);
        g_.getAchievedDisappearedPk().setSelected(false);
        g_.getMumy().setSelected(false);
        g_.getCopyMovesTypes().setSelected(false);
        g_.getReverseEffectsPowerMovesTypesGlobal().setSelected(false);
        g_.getTakeItemByDamagingMove().setSelected(false);
        g_.getGiveItemToAllyHavingUsed().setSelected(false);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isForbidUseBerryAgainstFoes());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isChgtTypeByDamage());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isIgnFoeStatisBoost());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isImmuCh());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isImmuDamageTrappingMoves());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isImmuDamageAllyMoves());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isImmuDamageRecoil());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isImmuRechargeRound());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isSlowing());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isImmuSufferedDamageLowEff());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isCancelSecEffectOther());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isCancelSecEffectOwner());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isInflictingDamageInsteadOfSuffering());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isNbHits());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isBreakProtection());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isPlate());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isHealedStatusBySwitch());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isAchievedDisappearedPk());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isMumy());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isCopyMovesTypes());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isReverseEffectsPowerMovesTypesGlobal());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isTakeItemByDamagingMove());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).isGiveItemToAllyHavingUsed());
    }
    @Test
    public void abForm13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getImmuStatusTypes().getCrud().getAdd());
        g_.getImmuStatusTypes().getCrud().getKey().setupValue(T_1);
        g_.getImmuStatusTypes().getCrud().getValue().setupValue(new StringList(S_1));
        tryClick(g_.getImmuStatusTypes().getCrud().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getImmuStatusTypes().size());
        assertEq(T_1,facade_.getData().getAbilities().getVal(A_1).getImmuStatusTypes().getKey(0));
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getImmuStatusTypes().getValue(0).size());
        assertEq(S_1,facade_.getData().getAbilities().getVal(A_1).getImmuStatusTypes().getValue(0).get(0));
    }
    @Test
    public void abForm14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getImmuLowStatisTypes().getCrud().getAdd());
        g_.getImmuLowStatisTypes().getCrud().getKey().setupValue(T_1);
        g_.getImmuLowStatisTypes().getCrud().getValue().setupValue(new IdList<Statistic>(Statistic.SPEED));
        tryClick(g_.getImmuLowStatisTypes().getCrud().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatisTypes().size());
        assertEq(T_1,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatisTypes().getKey(0));
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatisTypes().getValue(0).size());
        assertEq(Statistic.SPEED,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatisTypes().getValue(0).get(0));
    }
    @Test
    public void abForm15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getImmuLowStatIfStatus().getCrud().getAdd());
        g_.getImmuLowStatIfStatus().getCrud().getGenePair().getKey().setupValue(new StatisticStatus(Statistic.SPEED,S_1));
        tryClick(g_.getImmuLowStatIfStatus().getCrud().getValidAddEdit());
        tryClick(g_.getImmuLowStatIfStatus().getCrud().getAdd());
        g_.getImmuLowStatIfStatus().getCrud().getGenePair().getKey().setupValue(new StatisticStatus(Statistic.SPEED,S_2));
        tryClick(g_.getImmuLowStatIfStatus().getCrud().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatIfStatus().size());
        assertEq(S_1,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatIfStatus().get(0).getStatus());
        assertEq(Statistic.SPEED,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatIfStatus().get(0).getStatistic());
        assertEq(S_2,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatIfStatus().get(1).getStatus());
        assertEq(Statistic.SPEED,facade_.getData().getAbilities().getVal(A_1).getImmuLowStatIfStatus().get(1).getStatistic());
    }
    @Test
    public void abForm16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getMultStatIfStatutRank().getCrud().getAdd());
        g_.getMultStatIfStatutRank().getCrud().getGenePair().getKey().setupValue(new StatisticStatus(Statistic.SPEED,S_1));
        g_.getMultStatIfStatutRank().getCrud().getGenePair().getValue().setupValue(1L);
        tryClick(g_.getMultStatIfStatutRank().getCrud().getValidAddEdit());
        tryClick(g_.getMultStatIfStatutRank().getCrud().getAdd());
        g_.getMultStatIfStatutRank().getCrud().getGenePair().getKey().setupValue(new StatisticStatus(Statistic.SPEED,S_2));
        g_.getMultStatIfStatutRank().getCrud().getGenePair().getValue().setupValue(2L);
        tryClick(g_.getMultStatIfStatutRank().getCrud().getValidAddEdit());
        tryClick(g_.getMultStatIfStatutRank().getCrud().getAllButtons().get(0));
        tryClick(g_.getMultStatIfStatutRank().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(2,facade_.getData().getAbilities().getVal(A_1).getMultStatIfStatutRank().size());
        assertEq(S_1,facade_.getData().getAbilities().getVal(A_1).getMultStatIfStatutRank().getKey(0).getStatus());
        assertEq(Statistic.SPEED,facade_.getData().getAbilities().getVal(A_1).getMultStatIfStatutRank().getKey(0).getStatistic());
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getMultStatIfStatutRank().getValue(0));
        assertEq(S_2,facade_.getData().getAbilities().getVal(A_1).getMultStatIfStatutRank().getKey(1).getStatus());
        assertEq(Statistic.SPEED,facade_.getData().getAbilities().getVal(A_1).getMultStatIfStatutRank().getKey(1).getStatistic());
        assertEq(2,facade_.getData().getAbilities().getVal(A_1).getMultStatIfStatutRank().getValue(1));
    }
    @Test
    public void abForm17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getMultStatIfDamageCat().getCrud().getAdd());
        g_.getMultStatIfDamageCat().getCrud().getGenePair().getKey().setupValue(new StatisticCategory(Statistic.SPEED,C_1));
        g_.getMultStatIfDamageCat().getCrud().getGenePair().getValue().setupValue(1L);
        tryClick(g_.getMultStatIfDamageCat().getCrud().getValidAddEdit());
        tryClick(g_.getMultStatIfDamageCat().getCrud().getAdd());
        g_.getMultStatIfDamageCat().getCrud().getGenePair().getKey().setupValue(new StatisticCategory(Statistic.SPEED,C_2));
        g_.getMultStatIfDamageCat().getCrud().getGenePair().getValue().setupValue(2L);
        tryClick(g_.getMultStatIfDamageCat().getCrud().getValidAddEdit());
        tryClick(g_.getMultStatIfDamageCat().getCrud().getAllButtons().get(0));
        tryClick(g_.getMultStatIfDamageCat().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(2,facade_.getData().getAbilities().getVal(A_1).getMultStatIfDamageCat().size());
        assertEq(C_1,facade_.getData().getAbilities().getVal(A_1).getMultStatIfDamageCat().getKey(0).getCategory());
        assertEq(Statistic.SPEED,facade_.getData().getAbilities().getVal(A_1).getMultStatIfDamageCat().getKey(0).getStatistic());
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getMultStatIfDamageCat().getValue(0));
        assertEq(C_2,facade_.getData().getAbilities().getVal(A_1).getMultStatIfDamageCat().getKey(1).getCategory());
        assertEq(Statistic.SPEED,facade_.getData().getAbilities().getVal(A_1).getMultStatIfDamageCat().getKey(1).getStatistic());
        assertEq(2,facade_.getData().getAbilities().getVal(A_1).getMultStatIfDamageCat().getValue(1));
    }
    @Test
    public void abForm18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getChangingBoostTypes().getCrud().getAdd());
        g_.getChangingBoostTypes().getCrud().getGenePair().getKey().setupValue(T_1);
        g_.getChangingBoostTypes().getCrud().getGenePair().getValue().setupValue(new TypeDamageBoost(T_2, Rate.one()));
        tryClick(g_.getChangingBoostTypes().getCrud().getValidAddEdit());
        tryClick(g_.getChangingBoostTypes().getCrud().getAllButtons().get(0));
        tryClick(g_.getChangingBoostTypes().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getChangingBoostTypes().size());
        assertEq(T_1,facade_.getData().getAbilities().getVal(A_1).getChangingBoostTypes().getKey(0));
        assertEq(T_2,facade_.getData().getAbilities().getVal(A_1).getChangingBoostTypes().getValue(0).getType());
        assertEq(Rate.one(),facade_.getData().getAbilities().getVal(A_1).getChangingBoostTypes().getValue(0).getBoost());
    }
    @Test
    public void abForm19() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getHealHpByTypeIfWeather().getCrud().getAdd());
        g_.getHealHpByTypeIfWeather().getCrud().getGenePair().getKey().setupValue(new WeatherType(M_1,T_1));
        g_.getHealHpByTypeIfWeather().getCrud().getGenePair().getValue().setupValue(Rate.one());
        tryClick(g_.getHealHpByTypeIfWeather().getCrud().getValidAddEdit());
        tryClick(g_.getHealHpByTypeIfWeather().getCrud().getAdd());
        g_.getHealHpByTypeIfWeather().getCrud().getGenePair().getKey().setupValue(new WeatherType(M_2,T_2));
        g_.getHealHpByTypeIfWeather().getCrud().getGenePair().getValue().setupValue(new Rate(2));
        tryClick(g_.getHealHpByTypeIfWeather().getCrud().getValidAddEdit());
        tryClick(g_.getHealHpByTypeIfWeather().getCrud().getAllButtons().get(0));
        tryClick(g_.getHealHpByTypeIfWeather().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(2,facade_.getData().getAbilities().getVal(A_1).getHealHpByTypeIfWeather().size());
        assertEq(M_1,facade_.getData().getAbilities().getVal(A_1).getHealHpByTypeIfWeather().getKey(0).getWeather());
        assertEq(T_1,facade_.getData().getAbilities().getVal(A_1).getHealHpByTypeIfWeather().getKey(0).getType());
        assertEq(Rate.one(),facade_.getData().getAbilities().getVal(A_1).getHealHpByTypeIfWeather().getValue(0));
        assertEq(M_2,facade_.getData().getAbilities().getVal(A_1).getHealHpByTypeIfWeather().getKey(1).getWeather());
        assertEq(T_2,facade_.getData().getAbilities().getVal(A_1).getHealHpByTypeIfWeather().getKey(1).getType());
        assertEq(new Rate(2),facade_.getData().getAbilities().getVal(A_1).getHealHpByTypeIfWeather().getValue(1));
    }
    @Test
    public void abForm20() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getFailStatus().getCrud().getAdd());
        g_.getFailStatus().getCrud().getKey().setupValue(S_1);
        g_.getFailStatus().getCrud().getValue().setupValue(facade_.getData().prefixNbTour(M_2));
        tryClick(g_.getFailStatus().getCrud().getValidAddEdit());
        CrudGeneFormTr cTr_ = crudTrMv(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        enterTextField(cTr_.getDestination());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getFailStatus().size());
        assertEq(S_1,facade_.getData().getAbilities().getVal(A_1).getFailStatus().getKey(0));
        assertEq(facade_.getData().prefixNbTour(move_),facade_.getData().getAbilities().getVal(A_1).getFailStatus().getValue(0));
    }
    @Test
    public void abForm21() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getEffectSending().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getWithEffect().setSelected(true);
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getCopyingAbility().setSelected(true);
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getDisableWeather().setSelected(true);
        CrudGeneFormTr cTr_ = crudTrMv(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        enterTextField(cTr_.getDestination());
        tryClick(g_.getEffectSending().getCrud().getValidAddEdit());
        tryClick(g_.getEffectSending().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getWithEffect().setSelected(false);
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getCopyingAbility().setSelected(false);
        ((GeneComponentModelSubscribeEffectWhileSending)g_.getEffectSending().getCrud().getGenePair().getKey()).getCrud().getDisableWeather().setSelected(false);
        tryClick(g_.getEffectSending().getCrud().getValidAddEdit());
        tryClick(g_.getEffectSending().getCrud().getAllButtons().get(0));
        tryClick(g_.getEffectSending().getCrud().getCancel());
        tryClick(g_.getEffectSending().getCrud().getAllButtons().get(1));
        tryClick(g_.getEffectSending().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).isWithEffect());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).getCopyingAbility());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).getDisableWeather());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).isWithEffect());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).getCopyingAbility());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).getDisableWeather());
    }
    @Test
    public void abForm22() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRound)g_.getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentGroupEffectEndRound().getContentEffectEndRound().getFailEndRound().setupValue(facade_.getData().prefixNbTour(M_2));
        CrudGeneFormTr cTr_ = crudTrMv(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        enterTextField(cTr_.getDestination());
        tryClick(g_.getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(g_.getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(g_.getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(facade_.getData().prefixNbTour(move_),facade_.getData().getAbilities().getVal(A_1).getEffectEndRound().get(0).getFailEndRound());
    }
    @Test
    public void abForm23() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        facade_.getData().validateOtherConstants();
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getFailStatus().getCrud().getAdd());
        g_.getFailStatus().getCrud().getKey().setupValue(S_1);
        g_.getFailStatus().getCrud().getValue().setupValue(facade_.getData().prefixNbTour(M_2));
        tryClick(g_.getFailStatus().getCrud().getValidAddEdit());
        CrudGeneFormTrCstList cTr_ = crudConst(sub_);
        String move_ = "move";
        cTr_.getFields().getVal(DataBaseConstants.KEY_NB_TOUR).setText(move_);
        enterTextField(cTr_.getFields().getVal(DataBaseConstants.KEY_NB_TOUR));
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getFailStatus().size());
        assertEq(S_1,facade_.getData().getAbilities().getVal(A_1).getFailStatus().getKey(0));
        assertEq(facade_.getData().prefixNbTour(M_2),facade_.getData().getAbilities().getVal(A_1).getFailStatus().getValue(0));
    }
    @Test
    public void abForm24() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        facade_.getData().validateOtherConstants();
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(A_1);
        tryClick(g_.getFailStatus().getCrud().getAdd());
        g_.getFailStatus().getCrud().getKey().setupValue(S_1);
        g_.getFailStatus().getCrud().getValue().setupValue(facade_.getData().prefixNbTour(M_2));
        tryClick(g_.getFailStatus().getCrud().getValidAddEdit());
        CrudGeneFormTrCstList cTr_ = crudConst(sub_);
        String move_ = "move";
        cTr_.getFields().getVal(DataBaseConstants.PREFIX_KEY).setText(move_);
        enterTextField(cTr_.getFields().getVal(DataBaseConstants.PREFIX_KEY));
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getAbilities().getVal(A_1).getFailStatus().size());
        assertEq(S_1,facade_.getData().getAbilities().getVal(A_1).getFailStatus().getKey(0));
        assertEq(facade_.getData().prefixNbTour(M_2),facade_.getData().getAbilities().getVal(A_1).getFailStatus().getValue(0));
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().completeQuickMembers(A_1, Instances.newAbilityData());
        f_.getData().completeQuickMembers(A_2, Instances.newAbilityData());
        return f_;
    }
    private CrudGeneFormEnt<AbilityData> crud(WindowPkEditor _crud) {
        tryClick(_crud.getAbMenu());
        return _crud.getCrudGeneFormAb();
    }
    private CrudGeneFormEnt<PokemonData> crudPk(WindowPkEditor _crud) {
        tryClick(_crud.getPkMenu());
        return _crud.getCrudGeneFormPk();
    }
    private CrudGeneFormTr crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsAbMenu());
        return _crud.getCrudGeneFormAbTr();
    }
    private CrudGeneFormTr crudTrMv(WindowPkEditor _crud) {
        tryClick(_crud.getTrsMvMenu());
        return _crud.getCrudGeneFormMvTr();
    }

    private CrudGeneFormTrCstList crudConst(WindowPkEditor _crud) {
        tryClick(_crud.getTrsConstMenu());
        return _crud.getCrudGeneFormTrCstList();
    }
}
