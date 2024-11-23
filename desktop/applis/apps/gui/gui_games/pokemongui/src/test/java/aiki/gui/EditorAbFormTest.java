package aiki.gui;

import aiki.facade.*;
import aiki.fight.abilities.AbilityData;
import aiki.fight.pokemon.PokemonData;
import aiki.gui.components.editor.*;
import aiki.instances.*;
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
        g_.getMultDamage().valueString(A_2);
        g_.getMultPower().valueString(A_3);
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
        g_.getMultDamage().valueString(A_2);
        g_.getMultPower().valueString(A_3);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelAbilityData gSec_ = (GeneComponentModelAbilityData)c_.getGene();
        assertEq(A_2,gSec_.getMultDamage().valueString());
        assertEq(A_3,gSec_.getMultPower().valueString());
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
        g_.getMultDamage().valueString(A_2);
        g_.getMultPower().valueString(A_3);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelAbilityData gSec_ = (GeneComponentModelAbilityData)c_.getGene();
        gSec_.getMultDamage().valueString(A_3);
        gSec_.getMultPower().valueString(A_2);
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
        g_.getAbilities().setupValue(new CustList<String>(A_1));
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
        cTr_.getDestination().setText(A_3);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getAbilities().contains(A_3));
    }
    @Test
    public void abForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(A_1);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
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
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).isWithEffect());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).getCopyingAbility());
        assertTrue(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(0).getDisableWeather());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).isWithEffect());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).getCopyingAbility());
        assertFalse(facade_.getData().getAbilities().getVal(A_1).getEffectSending().get(1).getDisableWeather());
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
}
