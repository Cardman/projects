package aiki.gui;

import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.*;
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
        CrudGeneFormListSubLevelMove levMoves_ = g_.getLevMoves();
        tryClick(levMoves_.getAdd());
        GeneComponentModelLevelMove gm_ = (GeneComponentModelLevelMove)levMoves_.getGene();
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
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGeneComponentModelInt().valueInt(2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGeneComponentModelInt().valueInt(4);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getAdd());
        g_.getSecEffectsByItem().getGenePair().getKey().setupValue(I_2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGeneComponentModelInt().valueInt(1);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGeneComponentModelInt().valueInt(3);
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
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGeneComponentModelInt().valueInt(2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGeneComponentModelInt().valueInt(4);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getAdd());
        g_.getSecEffectsByItem().getGenePair().getKey().setupValue(I_2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGeneComponentModelInt().valueInt(1);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getGenePair().getValue()).getCrud().getGeneComponentModelInt().valueInt(3);
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
        tryClick(g_.getMoves().getAdd());
        GeneComponentModelEffect effForm_ = effects(g_.getMoves());
        effForm_.getEvolutionKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_DAMAGE));
        effForm_.getEvolutionKind().getSelect().events(null);
        effForm_.getFail().valueString(M_2);
        tryClick(g_.getMoves().getValidAddEdit());
        tryClick(g_.getMoves().getAllButtons().get(0));
        tryClick(g_.getMoves().getCancel());
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
        tryClick(g_.getMoves().getAdd());
        GeneComponentModelEffect evoForm_ = effects(g_.getMoves());
        evoForm_.getEvolutionKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_STATIS));
        evoForm_.getEvolutionKind().getSelect().events(null);
        evoForm_.getFail().valueString(M_2);
        tryClick(g_.getMoves().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().getVal(M_1).getEffects().size());
        assertEq(M_2,facade_.getData().getMoves().getVal(M_1).getEffects().get(0).getFail());
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
    private GeneComponentModelEffect effects(CrudGeneFormListSubEffect _evos) {
        return (GeneComponentModelEffect) _evos.getGene();
    }
}
