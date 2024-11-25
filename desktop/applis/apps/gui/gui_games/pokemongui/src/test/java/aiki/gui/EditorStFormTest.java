package aiki.gui;

import aiki.facade.*;
import aiki.fight.moves.*;
import aiki.fight.status.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import code.maths.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorStFormTest extends InitEditorPkForm {
    @Test
    public void stForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        g_.getCatchingRate().valueRate(Rate.one());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getStatus().size());
        assertEq(1,c_.getList().size());
        assertEq(S_1,c_.getList().get(0).getKey());
        assertEq(Rate.one(),c_.getList().get(0).getValue().getCatchingRate());
    }
    @Test
    public void stForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        g_.getCatchingRate().valueRate(Rate.one());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelStatus gSec_ = (GeneComponentModelStatus)c_.getGene();
        assertEq(Rate.one(),gSec_.getCatchingRate().valueRate());
    }
    @Test
    public void stForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        g_.getCatchingRate().valueRate(Rate.one());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        GeneComponentModelStatus gSec_ = (GeneComponentModelStatus)c_.getGene();
        gSec_.getCatchingRate().valueRate(new Rate(2));
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getStatus().size());
        assertEq(new Rate(2), facade_.getData().getStatus().getVal(S_1).getCatchingRate());
    }
    @Test
    public void stForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getStatus().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void stForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> cm_ = crud(sub_);
        tryClick(cm_.getAdd());
        ((GeneComponentModelStatus) cm_.getGene()).getGeneComponentModelSelectKey().setupValue(S_1);
        tryClick(cm_.getValidAddEdit());
        CrudGeneFormEnt<MoveData> c_ = crudMv(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelMoveData g_ = (GeneComponentModelMoveData)c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(M_1);
        g_.getRequiredStatus().setupValue(new StringList(S_1));
        g_.getDeletedStatus().setupValue(new StringList(S_1));
        tryClick(c_.getValidAddEdit());
        tryClick(cm_.getAllButtons().get(0));
        tryClick(cm_.getValidRemove());
        assertEq(1, facade_.getData().getStatus().size());
        assertEq(1, cm_.getList().size());
    }
    @Test
    public void stForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        GeneComponentModelTr gTr_ = (GeneComponentModelTr) cTr_.getGene();
        gTr_.getTranslations().getVal(pr_.getLanguage()).setText("p_2");
        tryClick(cTr_.getValidAddEdit());
        assertEq("p_2",facade_.getData().getTranslatedStatus().firstValue().getVal(S_2));
    }
    @Test
    public void stForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(S_3);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getStatus().contains(S_3));
    }
    @Test
    public void stForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(S_1);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        assertTrue(facade_.getData().getStatus().contains(S_1));
        assertTrue(facade_.getData().getStatus().contains(S_2));
    }
    @Test
    public void stForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        g_.getDisabledEffIfSwitch().setSelected(true);
        g_.getIncrementingEndRound().setSelected(true);
        g_.getStatusType().setSelected(true);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertTrue(facade_.getData().getStatus().getVal(S_1).getDisabledEffIfSwitch());
        assertTrue(facade_.getData().getStatus().getVal(S_1).getIncrementingEndRound());
    }
    @Test
    public void stForm10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        g_.getDisabledEffIfSwitch().setSelected(false);
        g_.getIncrementingEndRound().setSelected(false);
        g_.getStatusType().setSelected(false);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertFalse(facade_.getData().getStatus().getVal(S_1).getDisabledEffIfSwitch());
        assertFalse(facade_.getData().getStatus().getVal(S_1).getIncrementingEndRound());
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().completeQuickMembers(S_1, Instances.newStatusSimple());
        f_.getData().completeQuickMembers(S_2, Instances.newStatusSimple());
        return f_;
    }
    private CrudGeneFormEnt<Status> crud(WindowPkEditor _crud) {
        tryClick(_crud.getStMenu());
        return _crud.getCrudGeneFormSt();
    }
    private CrudGeneFormEnt<MoveData> crudMv(WindowPkEditor _crud) {
        tryClick(_crud.getMvMenu());
        return _crud.getCrudGeneFormMv();
    }
    private CrudGeneFormTr crudTr(WindowPkEditor _crud) {
        tryClick(_crud.getTrsStMenu());
        return _crud.getCrudGeneFormStTr();
    }
}
