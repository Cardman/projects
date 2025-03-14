package aiki.gui;

import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.util.LevelMove;
import aiki.gui.components.editor.*;
import aiki.map.pokemon.enums.*;
import code.maths.Rate;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorPkFormTest extends InitEditorPkForm {

    @Test
    public void pkForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokedex().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().get(0).getKey());
        assertEq(1,c_.getList().get(0).getValue().getTypes().size());
        assertEq(T_1,c_.getList().get(0).getValue().getTypes().get(0));
    }
    @Test
    public void pkForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelPokemonData gSec_ = (GeneComponentModelPokemonData)c_.getGene();
        assertEq(6,gSec_.getStatistics().size());
        assertEq(50,gSec_.getStatistics().getVal(Statistic.SPEED).getBase().getValue());
        assertEq(25,gSec_.getStatistics().getVal(Statistic.SPEED).getEv().getValue());
    }
    @Test
    public void pkForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(40);
        tryClick(c_.getValidAddEdit());
        assertEq(40, facade_.getData().getPokedex().getVal(P_1).getStatistics().getVal(Statistic.SPEED).getBase());
    }
    @Test
    public void pkForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getPokedex().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void pkForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_1);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAdd());
        g_.getGeneComponentModelSelectKey().setupValue(P_2);
        g_.getTypes().setupValue(new StringList(T_2));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_1);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(2, facade_.getData().getPokedex().size());
        assertEq(2, c_.getList().size());
    }
    @Test
    public void pkForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq(P_2,facade_.getData().getPokemon(P_1).getBaseEvo());
    }
    @Test
    public void pkForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq(P_2,facade_.getData().getPokemon(P_1).getBaseEvo());
    }
    @Test
    public void pkForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(P_4);
        enterTextField(cTr_.getDestination());
        assertEq(P_4,facade_.getData().getPokemon(P_1).getBaseEvo());
    }
    @Test
    public void pkForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(P_2);
        enterTextField(cTr_.getDestination());
        assertEq(P_2,facade_.getData().getPokemon(P_1).getBaseEvo());
    }
    @Test
    public void pkForm10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText("");
        enterTextField(cTr_.getDestination());
        assertEq(P_2,facade_.getData().getPokemon(P_1).getBaseEvo());
    }
    @Test
    public void pkForm11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        cTr_.getDestination().setText("");
        enterTextField(cTr_.getDestination());
        assertEq(P_2,facade_.getData().getPokemon(P_1).getBaseEvo());
    }
    @Test
    public void pkForm12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAdd());
        ((GeneComponentModelTr)cTr_.getGene()).getKey().valueString("");
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq(3,facade_.getData().getTranslatedPokemon().getVal(pr_.getLanguage()).size());
    }
    @Test
    public void pkForm13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAdd());
        ((GeneComponentModelTr)cTr_.getGene()).getKey().valueString(P_3);
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq(3,facade_.getData().getTranslatedPokemon().getVal(pr_.getLanguage()).size());
    }
    @Test
    public void pkForm14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAdd());
        ((GeneComponentModelTr)cTr_.getGene()).getKey().valueString(P_4);
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq(4,facade_.getData().getTranslatedPokemon().getVal(pr_.getLanguage()).size());
    }
    @Test
    public void pkForm15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(0));
        tryClick(cTr_.getValidRemove());
        assertEq(3,facade_.getData().getTranslatedPokemon().getVal(pr_.getLanguage()).size());
    }
    @Test
    public void pkForm16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        tryClick(cTr_.getValidRemove());
        assertEq(2,facade_.getData().getTranslatedPokemon().getVal(pr_.getLanguage()).size());
    }
    @Test
    public void pkForm17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getCancel());
        assertEq(0,facade_.getData().getPokedex().size());
    }
    @Test
    public void pkForm18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        g_.getTypes().setupValue(new StringList(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        GeneComponentModelPokemonData gs_ = (GeneComponentModelPokemonData)c_.getGene();
        tryClick(c_.getAdd());
        g_.getGeneComponentModelSelectKey().setupValue(P_2);
        gs_.getTypes().setupValue(new StringList(T_1));
        gs_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        gs_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        gs_.getBaseEvo().setupValue(P_2);
        tryClick(c_.getValidAddEdit());
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(P_2);
        enterTextField(cTr_.getDestination());
        assertEq(P_2,facade_.getData().getPokemon(P_1).getBaseEvo());
    }
    @Test
    public void pkForm19() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleElement<LevelMove> levMoves_ = g_.getLevMoves().getCrud();
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueLong(1);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueLong(1);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_2);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueLong(2);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_3);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(3,facade_.getData().getPokemon(P_1).getLevMoves().size());
    }
    @Test
    public void pkForm20() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String, Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_LEVEL_SIMPLE);
        evoForm_.getLevel().valueLong(2);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm21() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_LEVEL_GENDER);
        evoForm_.getLevel().valueLong(2);
        evoForm_.getEvoGender().setupValue(Gender.NO_GENDER);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm22() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_STONE_SIMPLE);
        evoForm_.getItem().setupValue(I_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm23() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_STONE_GENDER);
        evoForm_.getItem().setupValue(I_1);
        evoForm_.getEvoGender().setupValue(Gender.NO_GENDER);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm24() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_HAPPINESS);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm25() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_ITEM);
        evoForm_.getItem().setupValue(I_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm26() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_MOVE);
        evoForm_.getEvoMove().setupValue(M_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm27() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_MOVE_TYPE);
        evoForm_.getEvoMoveType().setupValue(T_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm28() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_TEAM);
        evoForm_.getEvoTeamPokemon().setupValue(P_3);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm29() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_TEAM);
        evoForm_.getEvoTeamPokemon().setupValue(P_3);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getValidRemove());
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm30() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleElement<LevelMove> levMoves_ = g_.getLevMoves().getCrud();
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueLong(1);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getValidRemove());
        tryClick(c_.getValidAddEdit());
        assertEq(0,facade_.getData().getPokemon(P_1).getLevMoves().size());
    }
    @Test
    public void pkForm31() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleElement<LevelMove> levMoves_ = g_.getLevMoves().getCrud();
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueLong(1);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(cTr_.getAdd());
        ((GeneComponentModelTr)cTr_.getGene()).getKey().valueString(P_4);
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getLevMoves().size());
    }
    @Test
    public void pkForm32() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleForm<String,Evolution> levMoves_ = g_.getEvolutions().getCrud();
        tryClick(levMoves_.getAdd());
        keyEvos(levMoves_).setupValue(P_2);
        GeneComponentModelEvolution evoForm_ = valueEvos(levMoves_);
        ConverterCommonMapUtil.trigger(evoForm_.getEvolutionKind(),MessagesEditorSelect.EVO_ITEM);
        evoForm_.getItem().setupValue(I_1);
        tryClick(cTr_.getAdd());
        ((GeneComponentModelTr)cTr_.getGene()).getKey().valueString(P_4);
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAllButtons().get(0));
        tryClick(levMoves_.getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEvolutions().size());
    }
    @Test
    public void pkForm33() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        tryClick(g_.getEggGroups().getCrud().getAdd());
        g_.getEggGroups().getCrud().getGenePair().getKey().setupValue("_");
        tryClick(g_.getEggGroups().getCrud().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokemon(P_1).getEggGroups().size());
        assertEq("_",facade_.getData().getPokemon(P_1).getEggGroups().get(0));
    }
    @Test
    public void pkForm34() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getConstNum().addEntry(DataBase.DEF_MAX_ATT,new Rate(4));
        facade_.getData().getExpGrowth().addEntry(ExpType.E,"1");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<PokemonData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleElement<LevelMove> levMoves_ = g_.getLevMoves().getCrud();
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueLong(1);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueLong(1);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_2);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueLong(2);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_3);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidAddEdit());
        assertEq(3,facade_.getData().getPokemon(P_1).getLevMoves().size());
    }
    @Test
    public void menus() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        pr_.getFrames().add(sub_);
        crudTr(sub_).getFrame().getWindowListenersDef().get(0).windowClosing();
        crud(sub_).getFrame().getWindowListenersDef().get(0).windowClosing();
        sub_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(sub_.getCommonFrame().isVisible());
        sub_.changeLanguage(sub_.getApplicationName());
    }
    private CrudGeneFormEnt<PokemonData> crud(WindowPkEditor _crud) {
        tryClick(_crud.getPkMenu());
        return _crud.getCrudGeneFormPk();
    }
    private CrudGeneFormTr crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsPkMenu());
        return _crud.getCrudGeneFormPkTr();
    }
}
