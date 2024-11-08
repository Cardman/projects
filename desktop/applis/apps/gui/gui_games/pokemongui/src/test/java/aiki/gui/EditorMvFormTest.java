package aiki.gui;

import aiki.facade.*;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import code.gui.*;
import code.maths.*;
import code.mock.*;
import org.junit.Test;

public final class EditorMvFormTest extends InitEditorPkForm {

    @Test
    public void tmForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormNb c_ = crudTm(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelEltStr g_ = (GeneComponentModelEltStr)c_.getGeneValue();
        c_.getGeneKey().value(2);
        g_.value(M_3);
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
        GeneComponentModelEltStr g_ = (GeneComponentModelEltStr)c_.getGeneValue();
        c_.getGeneKey().value(3);
        g_.value(M_3);
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
        GeneComponentModelEltStr g_ = (GeneComponentModelEltStr)c_.getGeneValue();
        g_.value(M_3);
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
        GeneComponentModelEltStr g_ = (GeneComponentModelEltStr)c_.getGeneValue();
        c_.getGeneKey().value(2);
        g_.value(M_3);
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
        GeneComponentModelEltStr g_ = (GeneComponentModelEltStr)c_.getGeneValue();
        c_.getGeneKey().value(2);
        g_.value(M_3);
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
        GeneComponentModelEltStr g_ = (GeneComponentModelEltStr)c_.getGeneValue();
        c_.getGeneKey().value(3);
        g_.value(M_3);
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
        GeneComponentModelEltStr g_ = (GeneComponentModelEltStr)c_.getGeneValue();
        g_.value(M_3);
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
        GeneComponentModelEltStr g_ = (GeneComponentModelEltStr)c_.getGeneValue();
        c_.getGeneKey().value(2);
        g_.value(M_3);
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
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGeneValue();
        c_.getGeneKey().value(M_1);
        g_.getPp().valueInt(10);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getMoves().size());
        assertEq(1,c_.getList().size());
        assertEq(M_1,c_.getList().getKey(0));
        assertEq(10,c_.getList().getValue(0).getPp());
    }
    @Test
    public void mvForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGeneValue();
        c_.getGeneKey().value(M_1);
        g_.getPp().valueInt(10);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGeneValue();
        assertEq(10,gSec_.getPp().valueInt());
    }
    @Test
    public void mvForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<MoveData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData) c_.getGeneValue();
        c_.getGeneKey().value(M_1);
        g_.getPp().valueInt(10);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelMoveData gSec_ = (GeneComponentModelMoveData)c_.getGeneValue();
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
        c_.getGeneKey().value(M_1);
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
        cm_.getGeneKey().value(M_1);
        tryClick(cm_.getValidAddEdit());
        CrudGeneFormEnt<PokemonData> c_ = crudPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        c_.getGeneKey().value(P_1);
        CrudGeneFormListSubLevelMove levMoves_ = g_.getLevMoves();
        tryClick(levMoves_.getAdd());
        GeneComponentModelLevelMove gm_ = (GeneComponentModelLevelMove)levMoves_.getGene();
        gm_.getLevel().value(1);
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
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGeneValue();
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
}
