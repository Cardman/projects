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
        cTr_.getDestination().setText(S_4);
        enterTextField(cTr_.getDestination());
        assertTrue(facade_.getData().getStatus().contains(S_4));
    }
    @Test
    public void stForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTr cTr_ = crudTr(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        cTr_.getDestination().setText(S_1);
        enterTextField(cTr_.getDestination());
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
    @Test
    public void stForm11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        tryClick(g_.getEffectEndRound().getCrud().getAdd());
        tryClick(g_.getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(g_.getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(g_.getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getStatus().getVal(S_1).getEffectEndRound().size());
    }
    @Test
    public void stForm12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        tryClick(g_.getEffectsPartner().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectPartnerStatus)g_.getEffectsPartner().getCrud().getGenePair().getKey()).getWeddingAlly().setSelected(true);
        tryClick(g_.getEffectsPartner().getCrud().getValidAddEdit());
        tryClick(g_.getEffectsPartner().getCrud().getAllButtons().get(0));
        tryClick(g_.getEffectsPartner().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertTrue(facade_.getData().getStatus().getVal(S_1).getEffectsPartner().get(0).getWeddingAlly());
    }
    @Test
    public void stForm13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        tryClick(g_.getEffectsPartner().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectPartnerStatus)g_.getEffectsPartner().getCrud().getGenePair().getKey()).getWeddingAlly().setSelected(false);
        tryClick(g_.getEffectsPartner().getCrud().getValidAddEdit());
        tryClick(g_.getEffectsPartner().getCrud().getAllButtons().get(0));
        tryClick(g_.getEffectsPartner().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertFalse(facade_.getData().getStatus().getVal(S_1).getEffectsPartner().get(0).getWeddingAlly());
    }
    @Test
    public void stForm14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        ConverterCommonMapUtil.trigger(g_.getEffectKind(),MessagesEditorSelect.STAT_AUTO);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getStatus().size());
    }
    @Test
    public void stForm15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        ConverterCommonMapUtil.trigger(g_.getEffectKind(),MessagesEditorSelect.STAT_BEGIN);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getStatus().size());
    }
    @Test
    public void stForm16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        ConverterCommonMapUtil.trigger(g_.getEffectKind(),MessagesEditorSelect.STAT_SIMPLE);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getStatus().size());
    }
    @Test
    public void stForm17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormEnt<Status> c_ = crud(sub_);
        tryClick(c_.getAdd());
        GeneComponentModelStatus g_ = (GeneComponentModelStatus) c_.getGene();
        g_.getGeneComponentModelSelectKey().setupValue(S_1);
        tryClick(g_.getEffectEndRound().getCrud().getAdd());
        CrudGeneFormTr cTr_ = crudTrMv(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        enterTextField(cTr_.getDestination());
        tryClick(g_.getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(g_.getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(g_.getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getStatus().getVal(S_1).getEffectEndRound().size());
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

    private CrudGeneFormTr crudTrMv(WindowPkEditor _crud) {
        tryClick(_crud.getTrsMvMenu());
        return _crud.getCrudGeneFormMvTr();
    }
}
