package aiki.gui;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.util.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import code.maths.*;
import code.mock.*;
import code.util.*;
import code.util.comparators.*;
import code.util.core.*;
import org.junit.Test;

public final class EditorItFormTest extends InitEditorPkForm {
    @Test
    public void itForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelItem g_ = (GeneComponentModelItem) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(I_1);
        g_.getPrice().valueInt(10);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getItems().size());
        assertEq(1,c_.getList().size());
        assertEq(I_1,c_.getList().get(0).getKey());
        assertEq(10,c_.getList().get(0).getValue().getPrice());
    }
    @Test
    public void itForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelItem g_ = (GeneComponentModelItem) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(I_1);
        g_.getPrice().valueInt(10);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelItem gSec_ = (GeneComponentModelItem)c_.getGene();
        assertEq(10,gSec_.getPrice().valueInt());
    }
    @Test
    public void itForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelItem g_ = (GeneComponentModelItem) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(I_1);
        g_.getPrice().valueInt(10);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelItem gSec_ = (GeneComponentModelItem)c_.getGene();
        gSec_.getPrice().valueInt(20);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getItems().size());
        assertEq(20, facade_.getData().getItems().getVal(I_1).getPrice());
    }
    @Test
    public void itForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> c_ = crud(sub_);
        tryClick(c_.getAdd());
        ((GeneComponentModelItem)c_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getItems().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void itForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        tryClick(cm_.getValidAddEdit());
        CrudGeneFormEnt<PokemonData> c_ = crudPk(sub_);
        tryClick(c_.getAdd());
        ((GeneComponentModelPokemonData)c_.getGene()).getGeneComponentModelSelectKey().setupValue(P_1);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_2);
        CrudGeneFormSimpleForm<String, Evolution> evolutions_ = g_.getEvolutions().getCrud();
        tryClick(evolutions_.getAdd());
        keyEvos(evolutions_).setupValue(P_1);
        GeneComponentModelEvolution gEvo_ = valueEvos(evolutions_);
        ConverterCommonMapUtil.trigger(gEvo_.getEvolutionKind(), MessagesEditorSelect.EVO_STONE_SIMPLE);
        gEvo_.getItem().setupValue(I_1);
        tryClick(evolutions_.getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getValidRemove());
        assertEq(1, facade_.getData().getItems().size());
        assertEq(1, cm_.getList().size());
    }
    @Test
    public void itForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq("p_2",facade_.getData().getTranslatedItems().firstValue().getVal(I_2));
    }
    @Test
    public void itForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(I_3);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getItems().contains(I_3));
    }
    @Test
    public void itForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(I_1);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getItems().contains(I_1));
        assertTrue(facade_.getData().getItems().contains(I_2));
    }
    @Test
    public void itForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.REPEL);
        ((GeneComponentModelItem)cm_.getGene()).getSteps().valueLong(5);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(5,((Repel)facade_.getData().getItem(I_1)).getSteps());
    }
    @Test
    public void itForm10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getBoostStatisTypes().getCrud().getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getBoostStatisTypes().getCrud().getKey().setupValue(T_1);
        IdMap<Statistic, Byte> stats_ = new IdMap<Statistic, Byte>();
        stats_.addEntry(Statistic.SPEED,(byte)2);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getBoostStatisTypes().getCrud().getValue().setupValue(stats_);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getBoostStatisTypes().getCrud().getValidAddEdit());
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(1,((ItemForBattle)facade_.getData().getItem(I_1)).getBoostStatisTypes().size());
        assertEq(T_1,((ItemForBattle)facade_.getData().getItem(I_1)).getBoostStatisTypes().getKey(0));
        assertEq(1,((ItemForBattle)facade_.getData().getItem(I_1)).getBoostStatisTypes().getValue(0).size());
        assertEq(Statistic.SPEED,((ItemForBattle)facade_.getData().getItem(I_1)).getBoostStatisTypes().getValue(0).getKey(0));
        assertEq(2,((ItemForBattle)facade_.getData().getItem(I_1)).getBoostStatisTypes().getValue(0).getValue(0));
    }
    @Test
    public void itForm11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getAgainstEvo().setSelected(true);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getAttackLast().setSelected(true);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getAttacksSoon().setSelected(true);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getBoostExp().setSelected(true);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getCancelImmuType().setSelected(true);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getImmuLowStatis().setSelected(true);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getAgainstEvo());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getAttackLast());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getAttacksSoon());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getBoostExp());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getCancelImmuType());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getImmuLowStatis());
    }
    @Test
    public void itForm12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getAgainstEvo().setSelected(false);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getAttackLast().setSelected(false);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getAttacksSoon().setSelected(false);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getBoostExp().setSelected(false);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getCancelImmuType().setSelected(false);
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getImmuLowStatis().setSelected(false);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getAgainstEvo());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getAttackLast());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getAttacksSoon());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getBoostExp());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getCancelImmuType());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getImmuLowStatis());
    }
    @Test
    public void itForm13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);

        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getAdd());
        ((GeneComponentModelEventBoolVal)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getGene()).getEvent().setSelected(true);
        ((GeneComponentModelEventBoolVal)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getGene()).getProba().valueLgInt(new LgInt(3));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getAdd());
        ((GeneComponentModelEventBoolVal)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getGene()).getEvent().setSelected(false);
        ((GeneComponentModelEventBoolVal)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getGene()).getProba().valueLgInt(new LgInt(5));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getAllButtons().get(0));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getCancel());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getAllButtons().get(1));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getLawForAttackFirst().getCancel());

        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(2,((ItemForBattle)facade_.getData().getItem(I_1)).getLawForAttackFirst().size());
        assertEq(SortConstants.EQ_CMP,ComparatorBoolean.cmp(BoolVal.FALSE,((ItemForBattle)facade_.getData().getItem(I_1)).getLawForAttackFirst().getKey(0)));
        assertEq(new LgInt(5),((ItemForBattle)facade_.getData().getItem(I_1)).getLawForAttackFirst().getFreq(0));
        assertEq(SortConstants.EQ_CMP,ComparatorBoolean.cmp(BoolVal.TRUE,((ItemForBattle)facade_.getData().getItem(I_1)).getLawForAttackFirst().getKey(1)));
        assertEq(new LgInt(3),((ItemForBattle)facade_.getData().getItem(I_1)).getLawForAttackFirst().getFreq(1));
    }
    @Test
    public void itForm14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getKey().setupValue(new StatisticPokemon(Statistic.SPEED,P_1));
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getValue().setupValue((byte)1);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getKey().setupValue(new StatisticPokemon(Statistic.SPEED,P_2));
        ((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getValue().setupValue((byte)2);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getAllButtons().get(0));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getMultStatPokemonRank().getCrud().getCancel());
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(2,((ItemForBattle)facade_.getData().getItem(I_1)).getMultStatPokemonRank().size());
        assertEq(P_1,((ItemForBattle)facade_.getData().getItem(I_1)).getMultStatPokemonRank().getKey(0).getPokemon());
        assertEq(Statistic.SPEED,((ItemForBattle)facade_.getData().getItem(I_1)).getMultStatPokemonRank().getKey(0).getStatistic());
        assertEq(1,((ItemForBattle)facade_.getData().getItem(I_1)).getMultStatPokemonRank().getValue(0));
        assertEq(P_2,((ItemForBattle)facade_.getData().getItem(I_1)).getMultStatPokemonRank().getKey(1).getPokemon());
        assertEq(Statistic.SPEED,((ItemForBattle)facade_.getData().getItem(I_1)).getMultStatPokemonRank().getKey(1).getStatistic());
        assertEq(2,((ItemForBattle)facade_.getData().getItem(I_1)).getMultStatPokemonRank().getValue(1));
    }
    @Test
    public void itForm15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.BERRY);
        ((GeneComponentModelItem)cm_.getGene()).getBerryForm().getWithoutFail().setSelected(true);
        ((GeneComponentModelItem)cm_.getGene()).getBerryForm().getLawForAttackFirst().setSelected(true);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertTrue(((Berry)facade_.getData().getItem(I_1)).getWithoutFail());
        assertTrue(((Berry)facade_.getData().getItem(I_1)).getLawForAttackFirst());
    }
    @Test
    public void itForm16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.BERRY);
        ((GeneComponentModelItem)cm_.getGene()).getBerryForm().getWithoutFail().setSelected(false);
        ((GeneComponentModelItem)cm_.getGene()).getBerryForm().getLawForAttackFirst().setSelected(false);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertFalse(((Berry)facade_.getData().getItem(I_1)).getWithoutFail());
        assertFalse(((Berry)facade_.getData().getItem(I_1)).getLawForAttackFirst());
    }
    @Test
    public void itForm17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.BERRY);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getBerryForm().getMultStat().getCrud().getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getBerryForm().getMultStat().getCrud().getKey().setupValue(Statistic.SPEED);
        ((GeneComponentModelItem)cm_.getGene()).getBerryForm().getMultStat().getCrud().getValue().setupValue(new BoostHpRate((byte) 1,new Rate(2)));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getBerryForm().getMultStat().getCrud().getValidAddEdit());
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(1,((Berry)facade_.getData().getItem(I_1)).getMultStat().size());
        assertEq(Statistic.SPEED,((Berry)facade_.getData().getItem(I_1)).getMultStat().getKey(0));
        assertEq(1,((Berry)facade_.getData().getItem(I_1)).getMultStat().getValue(0).getBoost());
        assertEq(new Rate(2),((Berry)facade_.getData().getItem(I_1)).getMultStat().getValue(0).getHpRate());
    }
    @Test
    public void itForm18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.BERRY);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getBerryForm().getMultFoesDamage().getCrud().getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getBerryForm().getMultFoesDamage().getCrud().getKey().setupValue(T_1);
        ((GeneComponentModelItem)cm_.getGene()).getBerryForm().getMultFoesDamage().getCrud().getValue().setupValue(new EfficiencyRate(new Rate(1),new Rate(2)));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getBerryForm().getMultFoesDamage().getCrud().getValidAddEdit());
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(1,((Berry)facade_.getData().getItem(I_1)).getMultFoesDamage().size());
        assertEq(T_1,((Berry)facade_.getData().getItem(I_1)).getMultFoesDamage().getKey(0));
        assertEq(new Rate(1),((Berry)facade_.getData().getItem(I_1)).getMultFoesDamage().getValue(0).getEff());
        assertEq(new Rate(2),((Berry)facade_.getData().getItem(I_1)).getMultFoesDamage().getValue(0).getHpRate());
    }
    @Test
    public void itForm19() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.BOOST);
        ((GeneComponentModelItem)cm_.getGene()).getBoostForm().getWinPp().valueRate(new Rate(2));
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(new Rate(2),((Boost)facade_.getData().getItem(I_1)).getWinPp());
    }
    @Test
    public void itForm20() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.FOSSIL);
        ((GeneComponentModelItem)cm_.getGene()).getFossilForm().getLevel().valueInt(2);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(2,((Fossil)facade_.getData().getItem(I_1)).getLevel());
    }
    @Test
    public void itForm21() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.HEALING_HP);
        ((GeneComponentModelItem)cm_.getGene()).getHealingItemForm().getHealingTeam().setSelected(true);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertTrue(((HealingItem)facade_.getData().getItem(I_1)).getHealingTeam());
    }
    @Test
    public void itForm22() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.HEALING_ITEM);
        ((GeneComponentModelItem)cm_.getGene()).getHealingItemForm().getHealingTeam().setSelected(false);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertFalse(((HealingItem)facade_.getData().getItem(I_1)).getHealingTeam());
    }
    @Test
    public void itForm23() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.HEALING_PP);
        ((GeneComponentModelItem)cm_.getGene()).getHealingPpForm().getHealingMoveFullpp().setSelected(true);
        ((GeneComponentModelItem)cm_.getGene()).getHealingPpForm().getHealingAllMovesPp().setSelected(true);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertTrue(((HealingPp)facade_.getData().getItem(I_1)).getHealingMoveFullpp());
        assertTrue(((HealingPp)facade_.getData().getItem(I_1)).isHealingAllMovesPp());
    }
    @Test
    public void itForm24() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.HEALING_PP);
        ((GeneComponentModelItem)cm_.getGene()).getHealingPpForm().getHealingMoveFullpp().setSelected(false);
        ((GeneComponentModelItem)cm_.getGene()).getHealingPpForm().getHealingAllMovesPp().setSelected(false);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertFalse(((HealingPp)facade_.getData().getItem(I_1)).getHealingMoveFullpp());
        assertFalse(((HealingPp)facade_.getData().getItem(I_1)).isHealingAllMovesPp());
    }
    @Test
    public void itForm25() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.HEALING_STATUS);
        ((GeneComponentModelItem)cm_.getGene()).getHealingStatusForm().getHealingKo().setSelected(true);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertTrue(((HealingStatus)facade_.getData().getItem(I_1)).getHealingKo());
    }
    @Test
    public void itForm26() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.HEALING_HP_STATUS);
        ((GeneComponentModelItem)cm_.getGene()).getHealingStatusForm().getHealingKo().setSelected(false);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertFalse(((HealingStatus)facade_.getData().getItem(I_1)).getHealingKo());
    }
    @Test
    public void itForm27() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.EVOLVING_ITEM);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(Item.EVOLVING_ITEM,facade_.getData().getItem(I_1).getItemType());
    }
    @Test
    public void itForm28() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.EVOLVING_STONE);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(Item.EVOLVING_STONE,facade_.getData().getItem(I_1).getItemType());
    }
    @Test
    public void itForm29() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelItem)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.SELLING_ITEM);
        tryClick(cm_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getCancel());
        assertEq(Item.SELLING_ITEM,facade_.getData().getItem(I_1).getItemType());
    }
    @Test
    public void itForm30() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        GeneComponentModelItem g_ = (GeneComponentModelItem) cm_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getWithEffect().setSelected(true);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getCopyingAbility().setSelected(true);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getDisableWeather().setSelected(true);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getWithEffect().setSelected(false);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getCopyingAbility().setSelected(false);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getDisableWeather().setSelected(false);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getAllButtons().get(0));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getCancel());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getAllButtons().get(1));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getCancel());
        tryClick(cm_.getValidAddEdit());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(0).isWithEffect());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(0).getCopyingAbility());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(0).getDisableWeather());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(1).isWithEffect());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(1).getCopyingAbility());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(1).getDisableWeather());
    }
    @Test
    public void itForm31() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        GeneComponentModelItem g_ = (GeneComponentModelItem) cm_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRound)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentGroupEffectEndRound().getContentEffectEndRound().getFailEndRound().setupValue("_");
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getCancel());
        tryClick(cm_.getValidAddEdit());
        assertEq("_",((ItemForBattle)facade_.getData().getItem(I_1)).getEffectEndRound().get(0).getFailEndRound());
    }
    @Test
    public void itForm32() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        GeneComponentModelItem g_ = (GeneComponentModelItem) cm_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getAdd());
        CrudGeneFormTr cTr_ = crudTrMv(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getWithEffect().setSelected(true);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getCopyingAbility().setSelected(true);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getDisableWeather().setSelected(true);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getWithEffect().setSelected(false);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getCopyingAbility().setSelected(false);
        ((GeneComponentModelSubscribeEffectWhileSending)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getGenePair().getKey()).getCrud().getDisableWeather().setSelected(false);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getAllButtons().get(0));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getCancel());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getAllButtons().get(1));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectSending().getCrud().getCancel());
        tryClick(cm_.getValidAddEdit());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(0).isWithEffect());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(0).getCopyingAbility());
        assertTrue(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(0).getDisableWeather());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(1).isWithEffect());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(1).getCopyingAbility());
        assertFalse(((ItemForBattle)facade_.getData().getItem(I_1)).getEffectSending().get(1).getDisableWeather());
    }
    @Test
    public void itForm33() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Item> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        GeneComponentModelItem g_ = (GeneComponentModelItem) cm_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(I_1);
        ConverterCommonMapUtil.trigger(((GeneComponentModelItem)cm_.getGene()).getEffectKind().getSelectUniq(),Item.ITEM_FOR_BATTLE);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRound)((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentGroupEffectEndRound().getContentEffectEndRound().getFailEndRound().setupValue(facade_.getData().prefixNbTour(M_2));
        CrudGeneFormTr cTr_ = crudTrMv(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(((GeneComponentModelItem)cm_.getGene()).getItemForBattleForm().getEffectEndRound().getCrud().getCancel());
        tryClick(cm_.getValidAddEdit());
        assertEq(facade_.getData().prefixNbTour(move_),((ItemForBattle)facade_.getData().getItem(I_1)).getEffectEndRound().get(0).getFailEndRound());
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().completeQuickMembers(I_1, Instances.newBall());
        f_.getData().completeQuickMembers(I_2, Instances.newBall());
        return f_;
    }
    private CrudGeneFormEnt<Item> crud(WindowPkEditor _crud) {
        tryClick(_crud.getItMenu());
        return _crud.getCrudGeneFormIt();
    }
    private CrudGeneFormEnt<PokemonData> crudPk(WindowPkEditor _crud) {
        tryClick(_crud.getPkMenu());
        return _crud.getCrudGeneFormPk();
    }
    private CrudGeneFormTr crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsItMenu());
        return _crud.getCrudGeneFormItTr();
    }

    private CrudGeneFormTr crudTrMv(WindowPkEditor _crud) {
        tryClick(_crud.getTrsMvMenu());
        return _crud.getCrudGeneFormMvTr();
    }
}
