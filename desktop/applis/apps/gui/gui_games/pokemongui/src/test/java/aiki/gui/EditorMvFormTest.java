package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.pokemon.*;
import aiki.fight.util.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import code.maths.*;
import code.mock.*;
import code.util.core.*;
import org.junit.Test;

public final class EditorMvFormTest extends InitEditorPkForm {

    @Test
    public void tmForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelNb g_ = (GeneComponentModelNb)c_.getGene();
        g_.getKey().valueInt(2);
        g_.getValue().setupValue(M_3);
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getTm().size());
        assertEq(2,facade_.getData().getTmPrice().size());
    }
    @Test
    public void tmForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelNb g_ = (GeneComponentModelNb)c_.getGene();
        g_.getKey().valueInt(3);
        g_.getValue().setupValue(M_3);
        tryClick(c_.getValidAddEdit());
        assertEq(3,facade_.getData().getTm().size());
        assertEq(3,facade_.getData().getTmPrice().size());
    }
    @Test
    public void tmForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        tryClick(c_.getAllButtons().get(0));
        c_.getDestination().setValue(2);
        assertEq(2,facade_.getData().getTmPrice().size());
        assertEq(2,facade_.getData().getTm().size());
        assertEq(M_1,facade_.getData().getTm().getVal((short)1));
        assertEq(M_2,facade_.getData().getTm().getVal((short)2));
    }
    @Test
    public void tmForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        c_.getDestination().setValue(2);
        assertEq(2,facade_.getData().getTmPrice().size());
        assertEq(2,facade_.getData().getTm().size());
        assertEq(M_1,facade_.getData().getTm().getVal((short)1));
        assertEq(M_2,facade_.getData().getTm().getVal((short)2));
    }
    @Test
    public void tmForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        tryClick(c_.getAllButtons().get(0));
        c_.getDestination().setValue(3);
        assertEq(2,facade_.getData().getTmPrice().size());
        assertEq(2,facade_.getData().getTm().size());
        assertEq(M_1,facade_.getData().getTm().getVal((short)3));
        assertEq(M_2,facade_.getData().getTm().getVal((short)2));
    }
    @Test
    public void tmForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        PokemonData pData_ = Instances.newPokemonData();
        pData_.getTechnicalMoves().add((short)1);
        facade_.getData().completeQuickMembers(P_1, pData_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(2,facade_.getData().getTm().size());
        assertEq(2,facade_.getData().getTmPrice().size());
    }
    @Test
    public void tmForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(1,facade_.getData().getTm().size());
        assertEq(1,facade_.getData().getTmPrice().size());
    }
    @Test
    public void tmForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelNb g_ = (GeneComponentModelNb)c_.getGene();
        g_.getValue().setupValue(M_3);
        tryClick(c_.getValidAddEdit());
        assertEq(M_3,facade_.getData().getTm().getVal((short)1));
    }

    @Test
    public void tmForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        CrudGeneFormEnt<PokemonData> cPk_ = crudPk(sub_);
        tryClick(cPk_.getAdd());
        tryClick(c_.getAdd());
        GeneComponentModelNb g_ = (GeneComponentModelNb)c_.getGene();
        g_.getKey().valueInt(2);
        g_.getValue().setupValue(M_3);
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getTm().size());
    }

    @Test
    public void hmForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelNb g_ = (GeneComponentModelNb)c_.getGene();
        g_.getKey().valueInt(2);
        g_.getValue().setupValue(M_3);
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getHm().size());
    }
    @Test
    public void hmForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelNb g_ = (GeneComponentModelNb)c_.getGene();
        g_.getKey().valueInt(3);
        g_.getValue().setupValue(M_3);
        tryClick(c_.getValidAddEdit());
        assertEq(3,facade_.getData().getHm().size());
    }
    @Test
    public void hmForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        tryClick(c_.getAllButtons().get(0));
        c_.getDestination().setValue(2);
        assertEq(2,facade_.getData().getHm().size());
        assertEq(M_1,facade_.getData().getHm().getVal((short)1));
        assertEq(M_2,facade_.getData().getHm().getVal((short)2));
    }
    @Test
    public void hmForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        c_.getDestination().setValue(2);
        assertEq(2,facade_.getData().getHm().size());
        assertEq(M_1,facade_.getData().getHm().getVal((short)1));
        assertEq(M_2,facade_.getData().getHm().getVal((short)2));
    }
    @Test
    public void hmForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        tryClick(c_.getAllButtons().get(0));
        c_.getDestination().setValue(3);
        assertEq(2,facade_.getData().getHm().size());
        assertEq(M_1,facade_.getData().getHm().getVal((short)3));
        assertEq(M_2,facade_.getData().getHm().getVal((short)2));
    }
    @Test
    public void hmForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        PokemonData pData_ = Instances.newPokemonData();
        pData_.getHiddenMoves().add((short)1);
        facade_.getData().completeQuickMembers(P_1, pData_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(2,facade_.getData().getHm().size());
    }
    @Test
    public void hmForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(1,facade_.getData().getHm().size());
    }
    @Test
    public void hmForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelNb g_ = (GeneComponentModelNb)c_.getGene();
        g_.getValue().setupValue(M_3);
        tryClick(c_.getValidAddEdit());
        assertEq(M_3,facade_.getData().getHm().getVal((short)1));
    }

    @Test
    public void hmForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudHm(sub_);
        CrudGeneFormEnt<PokemonData> cPk_ = crudPk(sub_);
        tryClick(cPk_.getAdd());
        tryClick(c_.getAdd());
        GeneComponentModelNb g_ = (GeneComponentModelNb)c_.getGene();
        g_.getKey().valueInt(2);
        g_.getValue().setupValue(M_3);
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getHm().size());
    }
    @Test
    public void mvForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getPp().valueInt(10);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(1,c_.getList().size());
        assertEq(M_1,c_.getList().get(0).getKey());
        assertEq(10,c_.getList().get(0).getValue().getPp());
    }
    @Test
    public void mvForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getPp().valueInt(10);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGene();
        assertEq(10,gSec_.getPp().valueInt());
    }
    @Test
    public void mvForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getPp().valueInt(10);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGene();
        gSec_.getPp().valueInt(20);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(20, facade_.getData().getMoves().getVal(M_1).getPp());
    }
    @Test
    public void mvForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        ((GeneComponentModelMoveData) c_.getGene()).getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getMoves().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void mvForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelMoveData)cm_.getGene()).getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(cm_.getValidAddEdit());
        CrudGeneFormEnt<PokemonData> c_ = crudPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(P_1);
        CrudGeneFormSimpleElement<LevelMove> levMoves_ = g_.getLevMoves();
        tryClick(levMoves_.getAdd());
        GeneComponentModelLevelMove gm_ = ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getCrud();
        gm_.getLevel().valueInt(1);
        gm_.getMove().setupValue(M_1);
        tryClick(levMoves_.getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getValidRemove());
        assertEq(1, facade_.getData().getMoves().size());
        assertEq(1, cm_.getList().size());
    }
    @Test
    public void mvForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq("p_2",facade_.getData().getTranslatedMoves().firstValue().getVal(M_2));
    }
    @Test
    public void mvForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(M_3);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getMoves().contains(M_3));
    }
    @Test
    public void mvForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(M_1);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getMoves().contains(M_1));
        assertTrue(facade_.getData().getMoves().contains(M_2));
    }
    @Test
    public void mvForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getConstUserChoice().setSelected(false);
        g_.getDisappearBeforeUse().setSelected(false);
        g_.getRechargeRound().setSelected(false);
        g_.getStoppableMoveMulti().setSelected(false);
        g_.getStoppableMovePrio().setSelected(false);
        g_.getStoppableMoveSolo().setSelected(false);
        g_.getIgnVarAccurUserNeg().setSelected(false);
        g_.getIgnVarEvasTargetPos().setSelected(false);
        g_.getSecEffectIfNoDamage().setSelected(false);
        g_.getSwitchType().setSelected(false);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGene();
        gSec_.getPp().valueInt(20);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(20, facade_.getData().getMoves().getVal(M_1).getPp());
    }
    @Test
    public void mvForm10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getConstUserChoice().setSelected(true);
        g_.getDisappearBeforeUse().setSelected(true);
        g_.getRechargeRound().setSelected(true);
        g_.getStoppableMoveMulti().setSelected(true);
        g_.getStoppableMovePrio().setSelected(true);
        g_.getStoppableMoveSolo().setSelected(true);
        g_.getIgnVarAccurUserNeg().setSelected(true);
        g_.getIgnVarEvasTargetPos().setSelected(true);
        g_.getSecEffectIfNoDamage().setSelected(true);
        g_.getSwitchType().setSelected(true);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGene();
        gSec_.getPp().valueInt(20);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(20, facade_.getData().getMoves().getVal(M_1).getPp());
    }
    @Test
    public void mvForm11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getTypesByOwnedItem().getAdd());
        g_.getTypesByOwnedItem().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        g_.getTypesByOwnedItem().getGenePair().getValue().setupValue(T_2);
        tryClick(g_.getTypesByOwnedItem().getValidAddEdit());
        tryClick(g_.getTypesByOwnedItem().getAdd());
        g_.getTypesByOwnedItem().getGenePair().getKey().setupValue(I_2);
        g_.getTypesByOwnedItem().getGenePair().getValue().setupValue(T_3);
        tryClick(g_.getTypesByOwnedItem().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(2, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().size());
        assertEq(T_2, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().getVal(DataBase.EMPTY_STRING));
        assertEq(T_3, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().getVal(I_2));
    }
    @Test
    public void mvForm12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getTypesByOwnedItem().getAdd());
        g_.getTypesByOwnedItem().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        g_.getTypesByOwnedItem().getGenePair().getValue().setupValue(T_2);
        tryClick(g_.getTypesByOwnedItem().getValidAddEdit());
        tryClick(g_.getTypesByOwnedItem().getAdd());
        g_.getTypesByOwnedItem().getGenePair().getKey().setupValue(I_2);
        g_.getTypesByOwnedItem().getGenePair().getValue().setupValue(T_3);
        tryClick(g_.getTypesByOwnedItem().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGene();
        tryClick(gSec_.getTypesByOwnedItem().getAllButtons().get(0));
        gSec_.getTypesByOwnedItem().getGenePair().getValue().setupValue(T_1);
        tryClick(g_.getTypesByOwnedItem().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(2, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().size());
        assertEq(T_1, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().getVal(DataBase.EMPTY_STRING));
        assertEq(T_3, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().getVal(I_2));
    }
    @Test
    public void mvForm13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getTypesByOwnedItem().getAdd());
        g_.getTypesByOwnedItem().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        g_.getTypesByOwnedItem().getGenePair().getValue().setupValue(T_2);
        tryClick(g_.getTypesByOwnedItem().getValidAddEdit());
        tryClick(g_.getTypesByOwnedItem().getAdd());
        g_.getTypesByOwnedItem().getGenePair().getKey().setupValue(I_2);
        g_.getTypesByOwnedItem().getGenePair().getValue().setupValue(T_3);
        tryClick(g_.getTypesByOwnedItem().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGene();
        tryClick(gSec_.getTypesByOwnedItem().getAllButtons().get(1));
        gSec_.getTypesByOwnedItem().getGenePair().getValue().setupValue(T_1);
        tryClick(g_.getTypesByOwnedItem().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(2, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().size());
        assertEq(T_2, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().getVal(DataBase.EMPTY_STRING));
        assertEq(T_1, facade_.getData().getMoves().getVal(M_1).getTypesByOwnedItem().getVal(I_2));
    }
    @Test
    public void mvForm14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getTypesByWeather().getAdd());
        g_.getTypesByWeather().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        g_.getTypesByWeather().getGenePair().getValue().setupValue(T_2);
        tryClick(g_.getTypesByWeather().getValidAddEdit());
        tryClick(g_.getTypesByWeather().getAdd());
        g_.getTypesByWeather().getGenePair().getKey().setupValue(M_2);
        g_.getTypesByWeather().getGenePair().getValue().setupValue(T_3);
        tryClick(g_.getTypesByWeather().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(2, facade_.getData().getMoves().getVal(M_1).getTypesByWeather().size());
        assertEq(T_2, facade_.getData().getMoves().getVal(M_1).getTypesByWeather().getVal(DataBase.EMPTY_STRING));
        assertEq(T_3, facade_.getData().getMoves().getVal(M_1).getTypesByWeather().getVal(M_2));
    }
    @Test
    public void mvForm15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getSecEffectsByItem().getAdd());
        g_.getSecEffectsByItem().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(4);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getAdd());
        g_.getSecEffectsByItem().getGenePair().getKey().setupValue(I_2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(1);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(3);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(2, facade_.getData().getMoves().getVal(M_1).getSecEffectsByItem().size());
        assertEq(2, facade_.getData().getMoves().getVal(M_1).getSecEffectsByItem().getVal(DataBase.EMPTY_STRING).size());
        assertEq(2, facade_.getData().getMoves().getVal(M_1).getSecEffectsByItem().getVal(DataBase.EMPTY_STRING).get(0));
        assertEq(4, facade_.getData().getMoves().getVal(M_1).getSecEffectsByItem().getVal(DataBase.EMPTY_STRING).get(1));
        assertEq(2, facade_.getData().getMoves().getVal(M_1).getSecEffectsByItem().getVal(I_2).size());
        assertEq(1, facade_.getData().getMoves().getVal(M_1).getSecEffectsByItem().getVal(I_2).get(0));
        assertEq(3, facade_.getData().getMoves().getVal(M_1).getSecEffectsByItem().getVal(I_2).get(1));
    }
    @Test
    public void mvForm16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getSecEffectsByItem().getAdd());
        g_.getSecEffectsByItem().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(4);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getAdd());
        g_.getSecEffectsByItem().getGenePair().getKey().setupValue(I_2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(1);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(3);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(((GeneComponentModelMoveData) c_.getGene()).getSecEffectsByItem().getAllButtons().get(0));
        assertEq(2,((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAllButtons().size());
    }
    @Test
    public void mvForm17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_DAMAGE));
        effForm_.getEffectKind().getSelect().events(null);
        effForm_.getFail().valueString(M_2);
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().getVal(M_1).getEffects().size());
        assertEq(M_2,facade_.getData().getMoves().getVal(M_1).getEffects().get(0).getFail());
    }
    @Test
    public void mvForm18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getDamagingMove().setSelected(false);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect evoForm_ = effects(g_.getEffects());
        evoForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_STATIS));
        evoForm_.getEffectKind().getSelect().events(null);
        evoForm_.getFail().valueString(M_2);
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getMoves().getVal(M_1).getEffects().size());
        assertEq(M_2,facade_.getData().getMoves().getVal(M_1).getEffects().get(0).getFail());
    }
    @Test
    public void mvForm19() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getCategory().setupValue(C_1);
        tryClick(c_.getValidAddEdit());
        assertEq(C_1,((DamagingMoveData)facade_.getData().getMoves().getVal(M_1)).getCategory());
    }
    @Test
    public void mvForm20() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getDamagingMove().setSelected(false);
        g_.getThievableMove().setSelected(true);
        g_.getCounterableMove().setSelected(true);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertTrue(((StatusMoveData)facade_.getData().getMoves().getVal(M_1)).getCounterableMove());
        assertTrue(((StatusMoveData)facade_.getData().getMoves().getVal(M_1)).getThievableMove());
    }
    @Test
    public void mvForm21() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getDamagingMove().setSelected(false);
        g_.getThievableMove().setSelected(false);
        g_.getCounterableMove().setSelected(false);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertFalse(((StatusMoveData)facade_.getData().getMoves().getVal(M_1)).getCounterableMove());
        assertFalse(((StatusMoveData)facade_.getData().getMoves().getVal(M_1)).getThievableMove());
    }
    @Test
    public void mvForm22() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getDirect().setSelected(true);
        g_.getStoppableMoveKoSingle().setSelected(true);
        g_.getCannotKo().setSelected(true);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertTrue(((DamagingMoveData)facade_.getData().getMoves().getVal(M_1)).isDirect());
        assertTrue(((DamagingMoveData)facade_.getData().getMoves().getVal(M_1)).getStoppableMoveKoSingle());
        assertTrue(((DamagingMoveData)facade_.getData().getMoves().getVal(M_1)).getCannotKo());
    }
    @Test
    public void mvForm23() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getDirect().setSelected(false);
        g_.getStoppableMoveKoSingle().setSelected(false);
        g_.getCannotKo().setSelected(false);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertFalse(((DamagingMoveData)facade_.getData().getMoves().getVal(M_1)).isDirect());
        assertFalse(((DamagingMoveData)facade_.getData().getMoves().getVal(M_1)).getStoppableMoveKoSingle());
        assertFalse(((DamagingMoveData)facade_.getData().getMoves().getVal(M_1)).getCannotKo());
    }
    @Test
    public void mvForm24() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_DAMAGE));
        effForm_.getEffectKind().getSelect().events(null);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        ((GeneComponentModelEventString)effForm_.getContentEffectDamage().getDamageLaw().getGene()).getEvent().valueString("_1");
        ((GeneComponentModelEventString)effForm_.getContentEffectDamage().getDamageLaw().getGene()).getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        ((GeneComponentModelEventString)effForm_.getContentEffectDamage().getDamageLaw().getGene()).getEvent().valueString("_2");
        ((GeneComponentModelEventString)effForm_.getContentEffectDamage().getDamageLaw().getGene()).getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        effForm_.getContentEffectDamage().getConstDamage().setSelected(true);
        effForm_.getContentEffectDamage().getRandMax().setSelected(true);
        effForm_.getContentEffectDamage().getTargetDefense().setSelected(true);
        effForm_.getContentEffectDamage().getUserAttack().setSelected(true);
        effForm_.getContentEffectDamage().getSummingUserTeamOkFighter().setSelected(true);
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().size());
        assertEq("_1",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(0));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(0));
        assertEq("_2",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(1));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(1));
    }
    @Test
    public void mvForm25() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_DAMAGE));
        effForm_.getEffectKind().getSelect().events(null);
        tryClick(effForm_.getContentEffectDamage().getMultDamageAgainst().getAdd());
        effForm_.getContentEffectDamage().getMultDamageAgainst().getGenePair().getKey().setupValue(C_1);
        effForm_.getContentEffectDamage().getMultDamageAgainst().getGenePair().getValue().setupValue(Rate.one());
        tryClick(effForm_.getContentEffectDamage().getMultDamageAgainst().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getMultDamageAgainst().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getMultDamageAgainst().getCancel());
        effForm_.getContentEffectDamage().getConstDamage().setSelected(false);
        effForm_.getContentEffectDamage().getRandMax().setSelected(false);
        effForm_.getContentEffectDamage().getTargetDefense().setSelected(false);
        effForm_.getContentEffectDamage().getUserAttack().setSelected(false);
        effForm_.getContentEffectDamage().getSummingUserTeamOkFighter().setSelected(false);
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamageAgainst().size());
        assertEq(C_1,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamageAgainst().getKey(0));
        assertEq(Rate.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamageAgainst().getValue(0));
    }
    @Test
    public void mvForm26() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_DAMAGE));
        effForm_.getEffectKind().getSelect().events(null);
        tryClick(effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getAdd());
        effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getGenePair().getKey().setupValue(Statistic.SPEED);
        effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getGenePair().getValue().setupValue((byte) 1);
        tryClick(effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getCancel());
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getBoostStatisOnceKoFoe().size());
        assertEq(Statistic.SPEED,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getBoostStatisOnceKoFoe().getKey(0));
        assertEq(1,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getBoostStatisOnceKoFoe().getValue(0));
    }
    @Test
    public void mvForm27() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_STATIS));
        effForm_.getEffectKind().getSelect().events(null);
        tryClick(effForm_.getContentEffectStatistic().getLawBoost().getLaw().getAdd());
        effForm_.getContentEffectStatistic().getLawBoost().getCompo().getEvent().setupValue(Statistic.SPEED);
        effForm_.getContentEffectStatistic().getLawBoost().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectStatistic().getLawBoost().getLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectStatistic().getLawBoost().getLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectStatistic().getLawBoost().getLaw().getCancel());
        tryClick(effForm_.getContentEffectStatistic().getLawBoost().getLaw().getAdd());
        effForm_.getContentEffectStatistic().getLawBoost().getCompo().getEvent().setupValue(Statistic.HP);
        effForm_.getContentEffectStatistic().getLawBoost().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectStatistic().getLawBoost().getLaw().getValidAddEdit());
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawBoost().size());
        assertEq(Statistic.HP,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawBoost().getEvent(0));
        assertEq(LgInt.one(),((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawBoost().getFreq(0));
        assertEq(Statistic.SPEED,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawBoost().getEvent(1));
        assertEq(LgInt.one(),((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawBoost().getFreq(1));
    }
    @Test
    public void mvForm28() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_STATIS));
        effForm_.getEffectKind().getSelect().events(null);
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getAdd());
        effForm_.getContentEffectStatistic().getStatisVarRank().getGenePair().getKey().setupValue(Statistic.SPEED);
        effForm_.getContentEffectStatistic().getStatisVarRank().getGenePair().getValue().setupValue((byte) 1);
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getValidAddEdit());
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getCancel());
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getAdd());
        effForm_.getContentEffectStatistic().getStatisVarRank().getGenePair().getKey().setupValue(Statistic.HP);
        effForm_.getContentEffectStatistic().getStatisVarRank().getGenePair().getValue().setupValue((byte) 1);
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getValidAddEdit());
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatisVarRank().size());
        assertEq(Statistic.HP,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatisVarRank().getKey(0));
        assertEq(1,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatisVarRank().getValue(0));
        assertEq(Statistic.SPEED,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatisVarRank().getKey(1));
        assertEq(1,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatisVarRank().getValue(1));
    }
    @Test
    public void mvForm29() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_STATIS));
        effForm_.getEffectKind().getSelect().events(null);
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getAdd());
        effForm_.getContentEffectStatistic().getLocalFailStatis().getGenePair().getKey().setupValue(Statistic.SPEED);
        effForm_.getContentEffectStatistic().getLocalFailStatis().getGenePair().getValue().setupValue("_1");
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getValidAddEdit());
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getCancel());
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getAdd());
        effForm_.getContentEffectStatistic().getLocalFailStatis().getGenePair().getKey().setupValue(Statistic.HP);
        effForm_.getContentEffectStatistic().getLocalFailStatis().getGenePair().getValue().setupValue("_1");
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getValidAddEdit());
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLocalFailStatis().size());
        assertEq(Statistic.HP,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLocalFailStatis().getKey(0));
        assertEq("_1",((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLocalFailStatis().getValue(0));
        assertEq(Statistic.SPEED,((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLocalFailStatis().getKey(1));
        assertEq("_1",((EffectStatistic)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLocalFailStatis().getValue(1));
    }
    @Test
    public void mvForm30() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_STATUS));
        effForm_.getEffectKind().getSelect().events(null);
        tryClick(effForm_.getContentEffectStatus().getLawStatus().getLaw().getAdd());
        effForm_.getContentEffectStatus().getLawStatus().getCompo().getEvent().setupValue(S_1);
        effForm_.getContentEffectStatus().getLawStatus().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectStatus().getLawStatus().getLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectStatus().getLawStatus().getLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectStatus().getLawStatus().getLaw().getCancel());
        tryClick(effForm_.getContentEffectStatus().getLawStatus().getLaw().getAdd());
        effForm_.getContentEffectStatus().getLawStatus().getCompo().getEvent().setupValue(S_2);
        effForm_.getContentEffectStatus().getLawStatus().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectStatus().getLawStatus().getLaw().getValidAddEdit());
        effForm_.getContentEffectStatus().getStatusFromUser().setSelected(true);
        effForm_.getContentEffectStatus().getKoUserHealSubst().setSelected(true);
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(g_.getEffects().getAdd());
        effects(g_.getEffects()).getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_STATUS));
        effects(g_.getEffects()).getEffectKind().getSelect().events(null);
        effects(g_.getEffects()).getContentEffectStatus().getStatusFromUser().setSelected(false);
        effects(g_.getEffects()).getContentEffectStatus().getKoUserHealSubst().setSelected(false);
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(1));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getMoves().getVal(M_1).getEffects().size());
        assertEq(2,((EffectStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawStatus().size());
        assertEq(S_1,((EffectStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawStatus().getEvent(0));
        assertEq(LgInt.one(),((EffectStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawStatus().getFreq(0));
        assertEq(S_2,((EffectStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawStatus().getEvent(1));
        assertEq(LgInt.one(),((EffectStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getLawStatus().getFreq(1));
    }
    @Test
    public void mvForm31() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_END_ROUND_FOE));
        effForm_.getEffectKind().getSelect().events(null);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(Rate.one());
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRound().getEndRoundRank().valueInt(2);
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectEndRoundFoe)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getInflictedRateHpTarget());
        assertEq(2,((EffectEndRound)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEndRoundRank());
    }
    @Test
    public void mvForm32() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(g_.getEffects().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getEffects());
        effForm_.getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_END_ROUND_TEAM));
        effForm_.getEffectKind().getSelect().events(null);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundTeam().getDeleteAllStatus().valueRate(Rate.one());
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundTeam().getDeleteAllStatusAlly().valueRate(new Rate(2));
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRound().getFailEndRound().valueString(C_1);
        tryClick(g_.getEffects().getValidAddEdit());
        tryClick(g_.getEffects().getAllButtons().get(0));
        tryClick(g_.getEffects().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectEndRoundTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDeleteAllStatus());
        assertEq(new Rate(2),((EffectEndRoundTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDeleteAllStatusAlly());
        assertEq(C_1,((EffectEndRound)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getFailEndRound());
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().getTm().addEntry((short)1,M_1);
        f_.getData().getTm().addEntry((short)2,M_2);
        f_.getData().getTmPrice().addEntry((short)1,new LgInt(1));
        f_.getData().getTmPrice().addEntry((short)2,new LgInt(2));
        f_.getData().getHm().addEntry((short)1,M_1);
        f_.getData().getHm().addEntry((short)2,M_2);
        f_.getData().completeQuickMembers(M_1, Instances.newDamagingMoveData());
        f_.getData().completeQuickMembers(M_2, Instances.newDamagingMoveData());
        return f_;
    }
    private CrudGeneFormEnt<MoveData> crud(WindowPkEditor _crud) {
        tryClick(_crud.getMvMenu());
        return _crud.getCrudGeneFormMv();
    }
    private CrudGeneFormEnt<PokemonData> crudPk(WindowPkEditor _crud) {
        tryClick(_crud.getPkMenu());
        return _crud.getCrudGeneFormPk();
    }
    private CrudGeneFormTr crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsMvMenu());
        return _crud.getCrudGeneFormMvTr();
    }
    private CrudGeneFormNb crudTm(WindowPkEditor _crud) {
        tryClick(_crud.getTmMenu());
        return _crud.getCrudGeneFormTm();
    }
    private CrudGeneFormNb crudHm(WindowPkEditor _crud) {
        tryClick(_crud.getHmMenu());
        return _crud.getCrudGeneFormHm();
    }
    private GeneComponentModelEffect effects(CrudGeneFormSimpleElement<Effect> _evos) {
        return ((GeneComponentModelSubscribeEffect)_evos.getGenePair().getKey()).getCrud();
    }
}
