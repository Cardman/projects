package aiki.gui;

import aiki.facade.*;
import aiki.fight.util.TypesDuo;
import aiki.gui.components.editor.*;
import code.maths.Rate;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorTyFormTest extends InitEditorPkForm {
    @Test
    public void tyForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq("p_2",facade_.getData().getTranslatedTypes().firstValue().getVal(T_2));
    }
    @Test
    public void tyForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(T_3);
        enterTextField(cTr_.getDestination());
        assertTrue(facade_.getData().getTranslatedTypes().firstValue().contains(T_3));
    }
    @Test
    public void tyForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(T_1);
        enterTextField(cTr_.getDestination());
        assertTrue(facade_.getData().getTranslatedTypes().firstValue().contains(T_1));
        assertTrue(facade_.getData().getTranslatedTypes().firstValue().contains(T_2));
    }
    @Test
    public void eff1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTypes cTypes_ = crud(sub_);
        tryClick(cTypes_.getAdd());
        cTypes_.getValue().getKey().setupValue(new TypesDuo(T_1,T_2));
        cTypes_.getValue().getEff().valueRate(Rate.one());
        tryClick(cTypes_.getValidAddEdit());
        assertEq(1,facade_.getData().getTableTypes().size());
        assertEq(T_2,facade_.getData().getTableTypes().getKey(0).getPokemonType());
        assertEq(T_1,facade_.getData().getTableTypes().getKey(0).getDamageType());
        assertEq(Rate.one(),facade_.getData().getTableTypes().getValue(0));
    }
    @Test
    public void eff2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTypes cTypes_ = crud(sub_);
        tryClick(cTypes_.getAdd());
        cTypes_.getValue().getKey().setupValue(new TypesDuo(T_1,T_2));
        cTypes_.getValue().getEff().valueRate(Rate.one());
        tryClick(cTypes_.getValidAddEdit());
        tryClick(cTypes_.getAllButtons().get(0));
        cTypes_.getValue().getEff().valueRate(new Rate(2));
        tryClick(cTypes_.getValidAddEdit());
        assertEq(1,facade_.getData().getTableTypes().size());
        assertEq(T_2,facade_.getData().getTableTypes().getKey(0).getPokemonType());
        assertEq(T_1,facade_.getData().getTableTypes().getKey(0).getDamageType());
        assertEq(new Rate(2),facade_.getData().getTableTypes().getValue(0));
    }
    @Test
    public void eff3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTypes cTypes_ = crud(sub_);
        tryClick(cTypes_.getAdd());
        cTypes_.getValue().getKey().setupValue(new TypesDuo(T_1,T_2));
        cTypes_.getValue().getEff().valueRate(Rate.one());
        tryClick(cTypes_.getValidAddEdit());
        tryClick(cTypes_.getAllButtons().get(0));
        tryClick(cTypes_.getValidRemove());
        assertEq(0,facade_.getData().getTableTypes().size());
    }
    @Test
    public void eff4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTypes cTypes_ = crud(sub_);
        tryClick(cTypes_.getAdd());
        cTypes_.getValue().getKey().setupValue(new TypesDuo(T_1,T_2));
        cTypes_.getValue().getEff().valueRate(Rate.one());
        tryClick(cTypes_.getValidAddEdit());
        cTypes_.getFrame().getWindowListenersDef().get(0).windowClosing();
        assertEq(1,crud(sub_).getList().size());
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().setVarParamsMove(new StringMap<StringList>());
        return f_;
    }
    private CrudGeneFormTr crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsTyMenu());
        return _crud.getCrudGeneFormTyTr();
    }
    private CrudGeneFormTypes crud(WindowPkEditor _crud) {
        tryClick(_crud.getTypesMenu());
        return _crud.getCrudGeneFormTypes();
    }
}
