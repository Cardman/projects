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
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGeneValue();
        c_.getGeneKey().value(A_1);
        g_.getMultDamage().value(A_2);
        g_.getMultPower().value(A_3);
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getAbilities().size());
        assertEq(1,c_.getList().size());
        assertEq(A_1,c_.getList().getKey(0));
        assertEq(A_2,c_.getList().getValue(0).getMultDamage());
        assertEq(A_3,c_.getList().getValue(0).getMultPower());
    }
    @Test
    public void abForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGeneValue();
        c_.getGeneKey().value(A_1);
        g_.getMultDamage().value(A_2);
        g_.getMultPower().value(A_3);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelAbilityData gSec_ = (GeneComponentModelAbilityData)c_.getGeneValue();
        assertEq(A_2,gSec_.getMultDamage().value());
        assertEq(A_3,gSec_.getMultPower().value());
    }
    @Test
    public void abForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<AbilityData> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelAbilityData g_ = (GeneComponentModelAbilityData) c_.getGeneValue();
        c_.getGeneKey().value(A_1);
        g_.getMultDamage().value(A_2);
        g_.getMultPower().value(A_3);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelAbilityData gSec_ = (GeneComponentModelAbilityData)c_.getGeneValue();
        gSec_.getMultDamage().value(A_3);
        gSec_.getMultPower().value(A_2);
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
        c_.getGeneKey().value(A_1);
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
        cm_.getGeneKey().value(A_1);
        tryClick(cm_.getValidAddEdit());
        CrudGeneFormEnt<PokemonData> c_ = crudPk(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        c_.getGeneKey().value(P_1);
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
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGeneValue();
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
