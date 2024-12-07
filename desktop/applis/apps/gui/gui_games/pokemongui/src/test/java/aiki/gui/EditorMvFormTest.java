package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.util.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import aiki.map.levels.enums.*;
import code.gui.*;
import code.maths.*;
import code.mock.*;
import code.util.*;
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
        CrudGeneFormSimpleElement<LevelMove> levMoves_ = g_.getLevMoves().getCrud();
        tryClick(levMoves_.getAdd());
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getLevel().valueInt(1);
        ((GeneComponentModelSubscribeLevelMove)levMoves_.getGenePair().getKey()).getMove().setupValue(M_1);
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
        cTr_.getDestination().setText(M_4);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getMoves().contains(M_4));
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
        tryClick(g_.getTypesByOwnedItem().getCrud().getAdd());
        g_.getTypesByOwnedItem().getCrud().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        g_.getTypesByOwnedItem().getCrud().getGenePair().getValue().setupValue(T_2);
        tryClick(g_.getTypesByOwnedItem().getCrud().getValidAddEdit());
        tryClick(g_.getTypesByOwnedItem().getCrud().getAdd());
        g_.getTypesByOwnedItem().getCrud().getGenePair().getKey().setupValue(I_2);
        g_.getTypesByOwnedItem().getCrud().getGenePair().getValue().setupValue(T_3);
        tryClick(g_.getTypesByOwnedItem().getCrud().getValidAddEdit());
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
        tryClick(g_.getTypesByOwnedItem().getCrud().getAdd());
        g_.getTypesByOwnedItem().getCrud().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        g_.getTypesByOwnedItem().getCrud().getGenePair().getValue().setupValue(T_2);
        tryClick(g_.getTypesByOwnedItem().getCrud().getValidAddEdit());
        tryClick(g_.getTypesByOwnedItem().getCrud().getAdd());
        g_.getTypesByOwnedItem().getCrud().getGenePair().getKey().setupValue(I_2);
        g_.getTypesByOwnedItem().getCrud().getGenePair().getValue().setupValue(T_3);
        tryClick(g_.getTypesByOwnedItem().getCrud().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGene();
        tryClick(gSec_.getTypesByOwnedItem().getCrud().getAllButtons().get(0));
        gSec_.getTypesByOwnedItem().getCrud().getGenePair().getValue().setupValue(T_1);
        tryClick(g_.getTypesByOwnedItem().getCrud().getValidAddEdit());
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
        tryClick(g_.getTypesByOwnedItem().getCrud().getAdd());
        g_.getTypesByOwnedItem().getCrud().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        g_.getTypesByOwnedItem().getCrud().getGenePair().getValue().setupValue(T_2);
        tryClick(g_.getTypesByOwnedItem().getCrud().getValidAddEdit());
        tryClick(g_.getTypesByOwnedItem().getCrud().getAdd());
        g_.getTypesByOwnedItem().getCrud().getGenePair().getKey().setupValue(I_2);
        g_.getTypesByOwnedItem().getCrud().getGenePair().getValue().setupValue(T_3);
        tryClick(g_.getTypesByOwnedItem().getCrud().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGene();
        tryClick(gSec_.getTypesByOwnedItem().getCrud().getAllButtons().get(1));
        gSec_.getTypesByOwnedItem().getCrud().getGenePair().getValue().setupValue(T_1);
        tryClick(g_.getTypesByOwnedItem().getCrud().getValidAddEdit());
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
        tryClick(g_.getTypesByWeather().getCrud().getAdd());
        g_.getTypesByWeather().getCrud().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        g_.getTypesByWeather().getCrud().getGenePair().getValue().setupValue(T_2);
        tryClick(g_.getTypesByWeather().getCrud().getValidAddEdit());
        tryClick(g_.getTypesByWeather().getCrud().getAdd());
        g_.getTypesByWeather().getCrud().getGenePair().getKey().setupValue(M_2);
        g_.getTypesByWeather().getCrud().getGenePair().getValue().setupValue(T_3);
        tryClick(g_.getTypesByWeather().getCrud().getValidAddEdit());
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
        tryClick(g_.getSecEffectsByItem().getCrud().getAdd());
        g_.getSecEffectsByItem().getCrud().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(4);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getCrud().getAdd());
        g_.getSecEffectsByItem().getCrud().getGenePair().getKey().setupValue(I_2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(1);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(3);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getCrud().getValidAddEdit());
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
        tryClick(g_.getSecEffectsByItem().getCrud().getAdd());
        g_.getSecEffectsByItem().getCrud().getGenePair().getKey().setupValue(DataBase.EMPTY_STRING);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(4);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getCrud().getAdd());
        g_.getSecEffectsByItem().getCrud().getGenePair().getKey().setupValue(I_2);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(1);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAdd());
        ((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getGenePair().getKey().setupValue(3);
        tryClick(((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getValidAddEdit());
        tryClick(g_.getSecEffectsByItem().getCrud().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(((GeneComponentModelMoveData) c_.getGene()).getSecEffectsByItem().getCrud().getAllButtons().get(0));
        assertEq(2,((GeneComponentModelSubscribeInts)g_.getSecEffectsByItem().getCrud().getGenePair().getValue()).getCrud().getAllButtons().size());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        effForm_.getFail().setupValue(M_2);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_STATIS);
        effForm_.getFail().setupValue(M_2);
        tryClick(effectsCrud(g_).getValidAddEdit());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_1",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_1");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_2",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_2");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        effForm_.getContentEffectDamage().getConstDamage().setSelected(true);
        effForm_.getContentEffectDamage().getRandMax().setSelected(true);
        effForm_.getContentEffectDamage().getTargetDefense().setSelected(true);
        effForm_.getContentEffectDamage().getUserAttack().setSelected(true);
        effForm_.getContentEffectDamage().getSummingUserTeamOkFighter().setSelected(true);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getMultDamageAgainst().getCrud().getAdd());
        effForm_.getContentEffectDamage().getMultDamageAgainst().getCrud().getGenePair().getKey().setupValue(C_1);
        effForm_.getContentEffectDamage().getMultDamageAgainst().getCrud().getGenePair().getValue().setupValue(Rate.one());
        tryClick(effForm_.getContentEffectDamage().getMultDamageAgainst().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getMultDamageAgainst().getCrud().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getMultDamageAgainst().getCrud().getCancel());
        effForm_.getContentEffectDamage().getConstDamage().setSelected(false);
        effForm_.getContentEffectDamage().getRandMax().setSelected(false);
        effForm_.getContentEffectDamage().getTargetDefense().setSelected(false);
        effForm_.getContentEffectDamage().getUserAttack().setSelected(false);
        effForm_.getContentEffectDamage().getSummingUserTeamOkFighter().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getCrud().getAdd());
        effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getCrud().getGenePair().getKey().setupValue(Statistic.SPEED);
        effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getCrud().getGenePair().getValue().setupValue((byte) 1);
        tryClick(effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getCrud().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getBoostStatisOnceKoFoe().getCrud().getCancel());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_STATIS);
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
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_STATIS);
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getAdd());
        effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getGenePair().getKey().setupValue(Statistic.SPEED);
        effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getGenePair().getValue().setupValue((byte) 1);
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getCancel());
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getAdd());
        effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getGenePair().getKey().setupValue(Statistic.HP);
        effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getGenePair().getValue().setupValue((byte) 1);
        tryClick(effForm_.getContentEffectStatistic().getStatisVarRank().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_STATIS);
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getAdd());
        effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getGenePair().getKey().setupValue(Statistic.SPEED);
        effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getGenePair().getValue().setupValue("_1");
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getCancel());
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getAdd());
        effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getGenePair().getKey().setupValue(Statistic.HP);
        effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getGenePair().getValue().setupValue("_1");
        tryClick(effForm_.getContentEffectStatistic().getLocalFailStatis().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_STATUS);
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
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_STATUS);
        effects(effectsCrud(g_)).getContentEffectStatus().getStatusFromUser().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectStatus().getKoUserHealSubst().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_FOE);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(Rate.one());
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRound().getEndRoundRank().valueInt(2);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
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
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_TEAM);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundTeam().getDeleteAllStatus().valueRate(Rate.one());
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundTeam().getDeleteAllStatusAlly().valueRate(new Rate(2));
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRound().getFailEndRound().setupValue(C_1);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectEndRoundTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDeleteAllStatus());
        assertEq(new Rate(2),((EffectEndRoundTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDeleteAllStatusAlly());
        assertEq(C_1,((EffectEndRound)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getFailEndRound());
    }
    @Test
    public void mvForm33() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_STATUS);
        effects(effectsCrud(g_)).getContentGroupEffectEndRound().getGroupEffectEndRoundStatus().getContentEffectEndRoundStatus().getInflictedRateHpTarget().valueRate(Rate.one());
        effects(effectsCrud(g_)).getContentGroupEffectEndRound().getGroupEffectEndRoundStatus().getContentEffectEndRoundSingleStatus().getIncrementingDamageByRounds().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_STATUS);
        effects(effectsCrud(g_)).getContentGroupEffectEndRound().getGroupEffectEndRoundStatus().getContentEffectEndRoundStatus().getInflictedRateHpTarget().valueRate(Rate.one());
        effects(effectsCrud(g_)).getContentGroupEffectEndRound().getGroupEffectEndRoundStatus().getContentEffectEndRoundSingleStatus().getIncrementingDamageByRounds().setSelected(true);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectEndRoundStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getInflictedRateHpTarget());
        assertFalse(((EffectEndRoundSingleStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).isIncrementingDamageByRounds());
        assertEq(Rate.one(),((EffectEndRoundStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(1)).getInflictedRateHpTarget());
        assertTrue(((EffectEndRoundSingleStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(1)).isIncrementingDamageByRounds());
    }
    @Test
    public void mvForm34() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_STATUS_RELATION);
        effects(effectsCrud(g_)).getContentGroupEffectEndRound().getGroupEffectEndRoundStatus().getContentEffectEndRoundStatus().getInflictedRateHpTarget().valueRate(Rate.one());
        effects(effectsCrud(g_)).getContentGroupEffectEndRound().getGroupEffectEndRoundStatus().getContentEffectEndRoundStatusRelation().getThievedHpRateTargetToUser().valueRate(new Rate(2));
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectEndRoundStatus)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getInflictedRateHpTarget());
        assertEq(new Rate(2),((EffectEndRoundStatusRelation)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getThievedHpRateTargetToUser());
    }
    @Test
    public void mvForm35() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_INDIVIDUAL);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundIndividual().getUserStatusEndRound().setupValue(S_1);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(S_1,((EffectEndRoundIndividual)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getUserStatusEndRound());
    }
    @Test
    public void mvForm36() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_MULTI_RELATION);
        tryClick(effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundMultiRelation().getDamageByStatus().getCrud().getAdd());
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundMultiRelation().getDamageByStatus().getCrud().getKey().setupValue(S_1);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundMultiRelation().getDamageByStatus().getCrud().getValue().setupValue(Rate.one());
        tryClick(effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundMultiRelation().getDamageByStatus().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectEndRoundMultiRelation)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageByStatus().getVal(S_1));
    }
    @Test
    public void mvForm37() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_POSITION_RELATION);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundPositionRelation().getHealHp().valueRate(Rate.one());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectEndRoundPositionRelation)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getHealHp());
    }
    @Test
    public void mvForm38() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_SINGLE_RELATION);
        tryClick(effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getAdd());
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getKey().setupValue(1L);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getValue().setupValue(Rate.one());
        tryClick(effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getAdd());
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getKey().setupValue(2L);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getValue().setupValue(new Rate(2));
        tryClick(effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getAllButtons().get(0));
        tryClick(effForm_.getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().getCrud().getCancel());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectEndRoundSingleRelation)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getRateDamageFunctionOfNbRounds().getVal(1L));
        assertEq(new Rate(2),((EffectEndRoundSingleRelation)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getRateDamageFunctionOfNbRounds().getVal(2L));
    }
    @Test
    public void mvForm39() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_GLOBAL);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRound().getEndRoundRank().valueInt(2);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectEndRound)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEndRoundRank());
    }
    @Test
    public void mvForm40() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_POSITION_TARGET);
        effForm_.getContentGroupEffectEndRound().getContentEffectEndRound().getEndRoundRank().valueInt(2);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectEndRound)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEndRoundRank());
    }
    @Test
    public void mvForm41() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_GLOBAL);
        effForm_.getContentEffectGlobal().getWeather().setSelected(true);
        effForm_.getContentEffectGlobal().getCanceledIfUsed().setSelected(true);
        effForm_.getContentEffectGlobal().getPuttingKo().setSelected(true);
        effForm_.getContentEffectGlobal().getReverseOrderOfSortBySpeed().setSelected(true);
        effForm_.getContentEffectGlobal().getUnusableItem().setSelected(true);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().getVal(M_1).getEffects().size());
    }
    @Test
    public void mvForm42() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_GLOBAL);
        effForm_.getContentEffectGlobal().getWeather().setSelected(false);
        effForm_.getContentEffectGlobal().getCanceledIfUsed().setSelected(false);
        effForm_.getContentEffectGlobal().getPuttingKo().setSelected(false);
        effForm_.getContentEffectGlobal().getReverseOrderOfSortBySpeed().setSelected(false);
        effForm_.getContentEffectGlobal().getUnusableItem().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().getVal(M_1).getEffects().size());
    }
    @Test
    public void mvForm43() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_GLOBAL);
        tryClick(effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getAdd());
        effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getKey().setupValue(new TypesDuo(T_1,T_2));
        effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getValue().setupValue(new Rate(2));
        tryClick(effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getAdd());
        effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getKey().setupValue(new TypesDuo(T_2,T_1));
        effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getValue().setupValue(new Rate(3));
        tryClick(effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectGlobal().getEfficiencyMoves().getCrud().getCancel());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEfficiencyMoves().size());
        assertEq(T_1,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEfficiencyMoves().getKey(0).getDamageType());
        assertEq(T_2,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEfficiencyMoves().getKey(0).getPokemonType());
        assertEq(new Rate(2),((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEfficiencyMoves().getValue(0));
        assertEq(T_2,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEfficiencyMoves().getKey(1).getDamageType());
        assertEq(T_1,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEfficiencyMoves().getKey(1).getPokemonType());
        assertEq(new Rate(3),((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getEfficiencyMoves().getValue(1));
    }
    @Test
    public void mvForm44() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_GLOBAL);
        tryClick(effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getAdd());
        effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getKey().setupValue(new StatisticType(Statistic.SPEED,T_1));
        effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getValue().setupValue(new Rate(2));
        tryClick(effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getAdd());
        effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getKey().setupValue(new StatisticType(Statistic.SPEED,T_2));
        effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getValue().setupValue(new Rate(3));
        tryClick(effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectGlobal().getMultStatIfContainsType().getCrud().getCancel());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(2,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultStatIfContainsType().size());
        assertEq(T_1,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultStatIfContainsType().getKey(0).getType());
        assertEq(Statistic.SPEED,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultStatIfContainsType().getKey(0).getStatistic());
        assertEq(new Rate(2),((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultStatIfContainsType().getValue(0));
        assertEq(T_2,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultStatIfContainsType().getKey(1).getType());
        assertEq(Statistic.SPEED,((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultStatIfContainsType().getKey(1).getStatistic());
        assertEq(new Rate(3),((EffectGlobal)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultStatIfContainsType().getValue(1));
    }
    @Test
    public void mvForm45() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_INVOKE);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingAllyMove().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingMoveButUser().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingSufferedMove().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingTargetChosenMove().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingTargetSuccesfulMove().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingUserMoveWhileSleep().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_INVOKE);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingAllyMove().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingMoveButUser().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingSufferedMove().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingTargetChosenMove().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingTargetSuccesfulMove().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectInvoke().getInvokingUserMoveWhileSleep().setSelected(true);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getMoves().getVal(M_1).getEffects().size());
    }
    @Test
    public void mvForm46() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_TEAM);
        tryClick(effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getAdd());
        effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getKey().setupValue(new CategoryMult(C_1,(short) 1));
        effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getValue().setupValue(new Rate(1));
        tryClick(effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getValidAddEdit());
        tryClick(effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getAdd());
        effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getKey().setupValue(new CategoryMult(C_2,(short) 2));
        effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getValue().setupValue(new Rate(2));
        tryClick(effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getValidAddEdit());
        tryClick(effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getAllButtons().get(0));
        tryClick(effects(effectsCrud(g_)).getContentEffectTeam().getMultDamage().getCrud().getCancel());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_TEAM);
        effects(effectsCrud(g_)).getContentEffectTeam().getForbiddingHealing().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectTeam().getProtectAgainstCh().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_INVOKE);
        effects(effectsCrud(g_)).getContentEffectTeam().getForbiddingHealing().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectTeam().getProtectAgainstCh().setSelected(true);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(2));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamage().size());
        assertEq(C_1,((EffectTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamage().getKey(0).getCategory());
        assertEq(1,((EffectTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamage().getKey(0).getMult());
        assertEq(new Rate(1),((EffectTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamage().getValue(0));
        assertEq(C_2,((EffectTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamage().getKey(1).getCategory());
        assertEq(2,((EffectTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamage().getKey(1).getMult());
        assertEq(new Rate(2),((EffectTeam)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultDamage().getValue(1));
    }
    @Test
    public void mvForm47() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_TEAM_WHILE_SENDING_FOE);
        tryClick(effects(effectsCrud(g_)).getContentEffectTeamWhileSendFoe().getStatusByNbUses().getCrud().getAdd());
        effects(effectsCrud(g_)).getContentEffectTeamWhileSendFoe().getStatusByNbUses().getCrud().getKey().setupValue((short) 1);
        effects(effectsCrud(g_)).getContentEffectTeamWhileSendFoe().getStatusByNbUses().getCrud().getValue().setupValue(S_1);
        tryClick(effects(effectsCrud(g_)).getContentEffectTeamWhileSendFoe().getStatusByNbUses().getCrud().getValidAddEdit());
        tryClick(effects(effectsCrud(g_)).getContentEffectTeamWhileSendFoe().getStatusByNbUses().getCrud().getAdd());
        effects(effectsCrud(g_)).getContentEffectTeamWhileSendFoe().getStatusByNbUses().getCrud().getKey().setupValue((short) 2);
        effects(effectsCrud(g_)).getContentEffectTeamWhileSendFoe().getStatusByNbUses().getCrud().getValue().setupValue(S_2);
        tryClick(effects(effectsCrud(g_)).getContentEffectTeamWhileSendFoe().getStatusByNbUses().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectTeamWhileSendFoe)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatusByNbUses().size());
        assertEq(1,((EffectTeamWhileSendFoe)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatusByNbUses().getKey(0));
        assertEq(S_1,((EffectTeamWhileSendFoe)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatusByNbUses().getValue(0));
        assertEq(2,((EffectTeamWhileSendFoe)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatusByNbUses().getKey(1));
        assertEq(S_2,((EffectTeamWhileSendFoe)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getStatusByNbUses().getValue(1));
    }
    @Test
    public void mvForm48() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_UNPROTECT_FROM_TYPES);
        tryClick(effForm_.getContentEffectUnprotectFromTypes().getTypes().getCrud().getAdd());
        effForm_.getContentEffectUnprotectFromTypes().getTypes().getCrud().getGenePair().getKey().setupValue(new TypesDuo(T_1,T_2));
        tryClick(effForm_.getContentEffectUnprotectFromTypes().getTypes().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectUnprotectFromTypes().getTypes().getCrud().getAdd());
        effForm_.getContentEffectUnprotectFromTypes().getTypes().getCrud().getGenePair().getKey().setupValue(new TypesDuo(T_2,T_1));
        tryClick(effForm_.getContentEffectUnprotectFromTypes().getTypes().getCrud().getValidAddEdit());
        tryClick(effForm_.getContentEffectUnprotectFromTypes().getTypes().getCrud().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectUnprotectFromTypes().getTypes().getCrud().getCancel());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectUnprotectFromTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getTypes().size());
        assertEq(T_1,((EffectUnprotectFromTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getTypes().get(0).getDamageType());
        assertEq(T_2,((EffectUnprotectFromTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getTypes().get(0).getPokemonType());
        assertEq(T_2,((EffectUnprotectFromTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getTypes().get(1).getDamageType());
        assertEq(T_1,((EffectUnprotectFromTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getTypes().get(1).getPokemonType());
    }
    @Test
    public void mvForm49() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_COUNTER_ATTACK);
        effForm_.getContentEffectCounterAttack().getSufferingDamageDirectMove().valueRate(Rate.one());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectCounterAttack)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getSufferingDamageDirectMove());
    }
    @Test
    public void mvForm50() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_SWITCH_ABILITIES);
        effForm_.getContentEffectSwitchAbilities().getExchangeAbility().setupValue(DataBase.DEF_EXCHANGE_TYPE_EXCHANGE);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(ExchangeType.EXCHANGE,((EffectSwitchAbilities)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getExchangeAbility());
    }
    @Test
    public void mvForm51() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_SWITCH_ABILITIES);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(ExchangeType.NOTHING,((EffectSwitchAbilities)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getExchangeAbility());
    }
    @Test
    public void mvForm52() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_SWITCH_ITEMS);
        effForm_.getContentEffectSwitchItems().getMoveObject().setupValue(DataBase.DEF_MOVE_ITEM_TYPE_EXCHANGE_OBJECTS);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(MoveItemType.EXCHANGE_OBJECTS,((EffectSwitchItems)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMoveObject());
    }
    @Test
    public void mvForm53() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_SWITCH_MOVES_TYPES);
        tryClick(effForm_.getContentEffectSwitchMoveTypes().getChangeTypes().getCrud().getAdd());
        effForm_.getContentEffectSwitchMoveTypes().getChangeTypes().getCrud().getKey().setupValue(T_1);
        effForm_.getContentEffectSwitchMoveTypes().getChangeTypes().getCrud().getValue().setupValue(T_2);
        tryClick(effForm_.getContentEffectSwitchMoveTypes().getChangeTypes().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,((EffectSwitchMoveTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getChangeTypes().size());
        assertEq(T_1,((EffectSwitchMoveTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getChangeTypes().getKey(0));
        assertEq(T_2,((EffectSwitchMoveTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getChangeTypes().getValue(0));
    }
    @Test
    public void mvForm54() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_SWITCH_POINT_VIEW);
        effForm_.getContentEffectSwitchPointView().getPointViewChangement().setupValue(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_ATTRACT_DAMAGES_MOVES);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(PointViewChangementType.ATTRACT_DAMAGES_MOVES,((EffectSwitchPointView)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getPointViewChangement());
    }
    @Test
    public void mvForm55() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_SWITCH_TYPES);
        tryClick(effForm_.getContentEffectSwitchTypes().getChgtTypeByEnv().getCrud().getAdd());
        effForm_.getContentEffectSwitchTypes().getChgtTypeByEnv().getCrud().getKey().setupValue(EnvironmentType.ROAD);
        effForm_.getContentEffectSwitchTypes().getChgtTypeByEnv().getCrud().getValue().setupValue(T_1);
        tryClick(effForm_.getContentEffectSwitchTypes().getChgtTypeByEnv().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,((EffectSwitchTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getChgtTypeByEnv().size());
        assertEq(EnvironmentType.ROAD,((EffectSwitchTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getChgtTypeByEnv().getKey(0));
        assertEq(T_1,((EffectSwitchTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getChgtTypeByEnv().getValue(0));
    }
    @Test
    public void mvForm56() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_SWITCH_POSITION);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().getVal(M_1).getEffects().size());
    }
    @Test
    public void mvForm57() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_RESTRICTION);
        effects(effectsCrud(g_)).getContentEffectRestriction().getForbidTargetUsingItem().setSelected(true);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_RESTRICTION);
        effects(effectsCrud(g_)).getContentEffectRestriction().getForbidTargetUsingItem().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertTrue(((EffectRestriction)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getForbidTargetUsingItem());
        assertFalse(((EffectRestriction)facade_.getData().getMoves().getVal(M_1).getEffects().get(1)).getForbidTargetUsingItem());
    }
    @Test
    public void mvForm58() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_VAR_PP);
        effects(effectsCrud(g_)).getContentEffectVarPP().getDeletePp().valueInt(2);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,((EffectVarPP)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDeletePp());
    }
    @Test
    public void mvForm59() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_WIN_MONEY);
        effects(effectsCrud(g_)).getContentEffectWinMoney().getWinningRateBySumTargetUser().valueRate(Rate.one());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectWinMoney)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getWinningRateBySumTargetUser());
    }
    @Test
    public void mvForm60() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_REMAINED_HP_RATE);
        effects(effectsCrud(g_)).getContentEffectRemainedHpRate().getRateHp().valueRate(Rate.one());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectRemainedHpRate)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getRateHp());
    }
    @Test
    public void mvForm61() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_PROTECTION);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtSingle().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtTeamAgainstDamageMoves().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtTeamAgainstMultTargets().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtTeamAgainstPrio().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtTeamAgainstStatusMoves().setSelected(true);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtSingleAgainstKo().valueRate(Rate.one());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_PROTECTION);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtSingle().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtTeamAgainstDamageMoves().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtTeamAgainstMultTargets().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtTeamAgainstPrio().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtTeamAgainstStatusMoves().setSelected(false);
        effects(effectsCrud(g_)).getContentEffectProtection().getProtSingleAgainstKo().valueRate(new Rate(2));
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectProtection)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getProtSingleAgainstKo());
    }
    @Test
    public void mvForm62() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_PROTECT_FROM_TYPES);
        effects(effectsCrud(g_)).getContentEffectProtectFromTypes().getImmuAgainstTypes().setupValue(new StringList(T_1));
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,((EffectProtectFromTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getImmuAgainstTypes().size());
        assertEq(T_1,((EffectProtectFromTypes)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getImmuAgainstTypes().get(0));
    }
    @Test
    public void mvForm63() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_ORDER);
        effects(effectsCrud(g_)).getContentEffectOrder().getTargetAttacksLast().setSelected(true);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_ORDER);
        effects(effectsCrud(g_)).getContentEffectOrder().getTargetAttacksLast().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertTrue(((EffectOrder)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getTargetAttacksLast());
        assertFalse(((EffectOrder)facade_.getData().getMoves().getVal(M_1).getEffects().get(1)).getTargetAttacksLast());
    }
    @Test
    public void mvForm64() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_MULT_SUFFERED_MOVE_POWER);
        tryClick(effects(effectsCrud(g_)).getContentEffectMultMovePower().getMultMovePowerFctType().getCrud().getAdd());
        effects(effectsCrud(g_)).getContentEffectMultMovePower().getMultMovePowerFctType().getCrud().getKey().setupValue(T_1);
        effects(effectsCrud(g_)).getContentEffectMultMovePower().getMultMovePowerFctType().getCrud().getValue().setupValue(Rate.one());
        tryClick(effects(effectsCrud(g_)).getContentEffectMultMovePower().getMultMovePowerFctType().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_MULT_USED_MOVE_POWER);
        tryClick(effects(effectsCrud(g_)).getContentEffectMultMovePower().getMultMovePowerFctType().getCrud().getAdd());
        effects(effectsCrud(g_)).getContentEffectMultMovePower().getMultMovePowerFctType().getCrud().getKey().setupValue(T_1);
        effects(effectsCrud(g_)).getContentEffectMultMovePower().getMultMovePowerFctType().getCrud().getValue().setupValue(Rate.one());
        tryClick(effects(effectsCrud(g_)).getContentEffectMultMovePower().getMultMovePowerFctType().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(T_1,((EffectMultSufferedMovePower)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultMovePowerFctType().getKey(0));
        assertEq(Rate.one(),((EffectMultSufferedMovePower)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultMovePowerFctType().getValue(0));
        assertEq(T_1,((EffectMultUsedMovePower)facade_.getData().getMoves().getVal(M_1).getEffects().get(1)).getMultMovePowerFctType().getKey(0));
        assertEq(Rate.one(),((EffectMultUsedMovePower)facade_.getData().getMoves().getVal(M_1).getEffects().get(1)).getMultMovePowerFctType().getValue(0));
    }
    @Test
    public void mvForm65() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_FULL_HP_RATE);
        effects(effectsCrud(g_)).getContentEffectFullHpRate().getRestoredHp().setupValue("_");
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq("_",((EffectFullHpRate)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getRestoredHp());
    }
    @Test
    public void mvForm66() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_DAMAGE_RATE);
        effects(effectsCrud(g_)).getContentEffectDamageRate().getRateDamage().valueRate(Rate.one());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectDamageRate)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getRateDamage());
    }
    @Test
    public void mvForm67() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_COPY_MOVE);
        effects(effectsCrud(g_)).getContentEffectCopyMove().getCopyingMoveForUserDef().setSelected(true);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_COPY_MOVE);
        effects(effectsCrud(g_)).getContentEffectCopyMove().getCopyingMoveForUserDef().setSelected(false);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertTrue(((EffectCopyMove)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getCopyingMoveForUserDef());
        assertFalse(((EffectCopyMove)facade_.getData().getMoves().getVal(M_1).getEffects().get(1)).getCopyingMoveForUserDef());
    }
    @Test
    public void mvForm68() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_COPY_FIGHTER);
        effects(effectsCrud(g_)).getContentEffectCopyFighter().getPpForMoves().valueInt(1);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,((EffectCopyFighter)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getPpForMoves());
    }
    @Test
    public void mvForm69() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_COMMON_STATISTICS);
        tryClick(effects(effectsCrud(g_)).getContentEffectCommonStatistics().getCommonValue().getCrud().getAdd());
        effects(effectsCrud(g_)).getContentEffectCommonStatistics().getCommonValue().getCrud().getKey().setupValue(Statistic.SPEED);
        effects(effectsCrud(g_)).getContentEffectCommonStatistics().getCommonValue().getCrud().getValue().setupValue("_");
        tryClick(effects(effectsCrud(g_)).getContentEffectCommonStatistics().getCommonValue().getCrud().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Statistic.SPEED,((EffectCommonStatistics)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getCommonValue().getKey(0));
        assertEq("_",((EffectCommonStatistics)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getCommonValue().getValue(0));
    }
    @Test
    public void mvForm70() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_CLONE);
        effects(effectsCrud(g_)).getContentEffectClone().getHpRateClone().valueRate(Rate.one());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectClone)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getHpRateClone());
    }
    @Test
    public void mvForm71() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_ALLY);
        effects(effectsCrud(g_)).getContentEffectAlly().getMultAllyDamage().valueRate(Rate.one());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(Rate.one(),((EffectAlly)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getMultAllyDamage());
    }
    @Test
    public void mvForm72() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_ACCURACY);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAdd());
        ConverterCommonMapUtil.trigger(effects(effectsCrud(g_)).getEffectKind(),MessagesEditorSelect.EFF_BATON_PASS);
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(effectsCrud(g_).getAllButtons().get(1));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(2,facade_.getData().getMoves().getVal(M_1).getEffects().size());
    }
    @Test
    public void mvForm73() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>(facade_.getData().prefixNbTour(M_2),LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString(facade_.getData().prefixNbTour(M_2));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_2",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_2");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getCancel());
        assertEq(2,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().size());
        assertEq(facade_.getData().prefixNbTour(move_),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(0));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(0));
        assertEq("_2",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(1));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(1));
    }
    @Test
    public void mvForm74() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>(facade_.getData().prefixNbTour(M_2),LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString(facade_.getData().prefixNbTour(M_2));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_2",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_2");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getCancel());
        assertEq(2,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().size());
        assertEq(facade_.getData().prefixNbTour(move_),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(0));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(0));
        assertEq("_2",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(1));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(1));
    }
    @Test
    public void mvForm75() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>(facade_.getData().prefixNbTour(M_2),LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString(facade_.getData().prefixNbTour(M_2));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_2",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_2");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getCancel());
        assertEq(2,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().size());
        assertEq(facade_.getData().prefixNbTour(M_2),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(0));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(0));
        assertEq("_2",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(1));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(1));
    }
    @Test
    public void mvForm76() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        facade_.getData().validateOtherConstants();
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>(facade_.getData().prefixNbTour(M_2),LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString(facade_.getData().prefixNbTour(M_2));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_2",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_2");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        CrudGeneFormTrCstList cTr_ = crudConst(sub_);
        String move_ = "move";
        cTr_.getFields().getVal(DataBaseConstants.KEY_NB_TOUR).setText(move_);
        ((MockTextField)cTr_.getFields().getVal(DataBaseConstants.KEY_NB_TOUR)).getAbsAdvActionListeners().get(0).action(null,null);
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getCancel());
        assertEq(2,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().size());
        assertEq(facade_.getData().prefixNbTour(M_2),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(0));
        assertEq("VAR__move__"+M_2,facade_.getData().prefixNbTour(M_2));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(0));
        assertEq("_2",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(1));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(1));
    }
    @Test
    public void mvForm77() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        facade_.getData().validateOtherConstants();
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>(facade_.getData().prefixNbTour(M_2),LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString(facade_.getData().prefixNbTour(M_2));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_2",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_2");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        CrudGeneFormTrCstList cTr_ = crudConst(sub_);
        String move_ = "move";
        cTr_.getFields().getVal(DataBaseConstants.PREFIX_KEY).setText(move_);
        ((MockTextField)cTr_.getFields().getVal(DataBaseConstants.PREFIX_KEY)).getAbsAdvActionListeners().get(0).action(null,null);
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getCancel());
        assertEq(2,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().size());
        assertEq(facade_.getData().prefixNbTour(M_2),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(0));
        assertEq("move__NB_TOUR__"+M_2,facade_.getData().prefixNbTour(M_2));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(0));
        assertEq("_2",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(1));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(1));
    }
    @Test
    public void mvForm78() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        facade_.getData().validateOtherConstants();
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>(facade_.getData().prefixNbTour(M_2),LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString(facade_.getData().prefixNbTour(M_2));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_2",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_2");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        CrudGeneFormTrCstList cTr_ = crudConst(sub_);
        String move_ = "";
        cTr_.getFields().getVal(DataBaseConstants.KEY_NB_TOUR).setText(move_);
        ((MockTextField)cTr_.getFields().getVal(DataBaseConstants.KEY_NB_TOUR)).getAbsAdvActionListeners().get(0).action(null,null);
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getCancel());
        assertEq(2,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().size());
        assertEq(facade_.getData().prefixNbTour(M_2),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(0));
        assertEq("VAR__NB_TOUR__"+M_2,facade_.getData().prefixNbTour(M_2));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(0));
        assertEq("_2",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(1));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(1));
    }
    @Test
    public void mvForm79() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        facade_.getData().validateOtherConstants();
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        tryClick(effectsCrud(g_).getAdd());
        GeneComponentModelEffect effForm_ = effects(effectsCrud(g_));
        ConverterCommonMapUtil.trigger(effForm_.getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>(facade_.getData().prefixNbTour(M_2),LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString(facade_.getData().prefixNbTour(M_2));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAllButtons().get(0));
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getCancel());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getAdd());
        effForm_.getContentEffectDamage().getDamageLaw().setupValue(new EditedCrudPair<String, LgInt>("_2",LgInt.one()));
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getEvent().valueString("_2");
//        effForm_.getContentEffectDamage().getDamageLaw().getCompo().getProba().valueLgInt(LgInt.one());
        tryClick(effForm_.getContentEffectDamage().getDamageLaw().getValidAddEdit());
        tryClick(effectsCrud(g_).getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(effectsCrud(g_).getAllButtons().get(0));
        CrudGeneFormTrCstList cTr_ = crudConst(sub_);
        String move_ = "NB_TOUR";
        cTr_.getFields().getVal(DataBaseConstants.KEY_NB_TOUR).setText(move_);
        ((MockTextField)cTr_.getFields().getVal(DataBaseConstants.KEY_NB_TOUR)).getAbsAdvActionListeners().get(0).action(null,null);
        tryClick(effectsCrud(g_).getCancel());
        tryClick(c_.getCancel());
        assertEq(2,((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().size());
        assertEq(facade_.getData().prefixNbTour(M_2),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(0));
        assertEq("VAR__NB_TOUR__"+M_2,facade_.getData().prefixNbTour(M_2));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(0));
        assertEq("_2",((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getEvent(1));
        assertEq(LgInt.one(),((EffectDamage)facade_.getData().getMoves().getVal(M_1).getEffects().get(0)).getDamageLaw().getFreq(1));
    }
    private CrudGeneFormSimpleElement<Effect> effectsCrud(GeneComponentModelMoveData _g) {
        return _g.getEffects().getCrud();
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

    private CrudGeneFormTrCstList crudConst(WindowPkEditor _crud) {
        tryClick(_crud.getTrsConstMenu());
        return _crud.getCrudGeneFormTrCstList();
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
