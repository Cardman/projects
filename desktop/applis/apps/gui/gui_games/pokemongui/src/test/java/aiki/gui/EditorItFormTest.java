package aiki.gui;

import aiki.facade.*;
import aiki.fight.items.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import code.mock.*;
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
}
