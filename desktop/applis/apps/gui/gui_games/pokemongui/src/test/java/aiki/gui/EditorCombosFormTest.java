package aiki.gui;

import aiki.facade.*;
import aiki.gui.components.editor.*;
import code.maths.Rate;
import code.mock.*;
import code.util.StringList;
import org.junit.Test;

public final class EditorCombosFormTest extends InitEditorPkForm {
    @Test
    public void comboForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormCombos c_ = crud(sub_);
        tryClick(c_.getAdd());
        c_.getValue().getKey().setupValues(new StringList(M_1));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRoundFoe)c_.getValue().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(Rate.one());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValue().getTeamMove().getCrud().getAdd());
        tryClick(c_.getValue().getTeamMove().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getTeamMove().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getTeamMove().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getCombos().getEffects().size());
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getList().size());
        assertEq(M_1,facade_.getData().getCombos().getEffects().get(0).getList().get(0));
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getCombo().getEffectEndRound().size());
        assertEq(Rate.one(),facade_.getData().getCombos().getEffects().get(0).getCombo().getEffectEndRound().get(0).getInflictedRateHpTarget());
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getCombo().getTeamMove().size());
    }
    @Test
    public void comboForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormCombos c_ = crud(sub_);
        tryClick(c_.getAdd());
        c_.getValue().getKey().setupValues(new StringList(M_1));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRoundFoe)c_.getValue().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(Rate.one());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValue().getTeamMove().getCrud().getAdd());
        tryClick(c_.getValue().getTeamMove().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getTeamMove().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getTeamMove().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAllButtons().get(0));
        ((GeneComponentModelSubscribeEffectEndRoundFoe)c_.getValue().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(new Rate(2));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getCombos().getEffects().size());
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getList().size());
        assertEq(M_1,facade_.getData().getCombos().getEffects().get(0).getList().get(0));
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getCombo().getEffectEndRound().size());
        assertEq(new Rate(2),facade_.getData().getCombos().getEffects().get(0).getCombo().getEffectEndRound().get(0).getInflictedRateHpTarget());
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getCombo().getTeamMove().size());
    }
    @Test
    public void comboForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormCombos c_ = crud(sub_);
        tryClick(c_.getAdd());
        c_.getValue().getKey().setupValues(new StringList(M_1));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRoundFoe)c_.getValue().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(Rate.one());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValue().getTeamMove().getCrud().getAdd());
        tryClick(c_.getValue().getTeamMove().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getTeamMove().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getTeamMove().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getValidRemove());
        assertEq(0,facade_.getData().getCombos().getEffects().size());
    }
    @Test
    public void comboForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormCombos c_ = crud(sub_);
        tryClick(c_.getAdd());
        c_.getValue().getKey().setupValues(new StringList(M_1));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRoundFoe)c_.getValue().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(Rate.one());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValue().getTeamMove().getCrud().getAdd());
        tryClick(c_.getValue().getTeamMove().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getTeamMove().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getTeamMove().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        c_.getFrame().getWindowListenersDef().get(0).windowClosing();
        CrudGeneFormCombos sec_ = crud(sub_);
        assertEq(1,sec_.getList().size());
    }
    @Test
    public void comboForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormCombos c_ = crud(sub_);
        tryClick(c_.getAdd());
        c_.getValue().getKey().setupValues(new StringList(M_1));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRoundFoe)c_.getValue().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(Rate.one());
        CrudGeneFormTr cTr_ = crudTrMv(sub_);
        tryClick(cTr_.getAllButtons().get(1));
        String move_ = "move";
        cTr_.getDestination().setText(move_);
        ((MockTextField)cTr_.getDestination()).getAbsAdvActionListeners().get(0).action(null,null);
        tryClick(c_.getValue().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValue().getTeamMove().getCrud().getAdd());
        tryClick(c_.getValue().getTeamMove().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getTeamMove().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getTeamMove().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        c_.getFrame().getWindowListenersDef().get(0).windowClosing();
        CrudGeneFormCombos sec_ = crud(sub_);
        assertEq(1,sec_.getList().size());
    }
    @Test
    public void comboForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormCombos c_ = crud(sub_);
        tryClick(c_.getAdd());
        c_.getValue().getKey().setupValues(new StringList(M_1));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAdd());
        ((GeneComponentModelSubscribeEffectEndRoundFoe)c_.getValue().getEffectEndRound().getCrud().getGenePair().getKey()).getCrud().getContentEffectEndRoundFoe().getInflictedRateHpTarget().valueRate(Rate.one());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getEffectEndRound().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getEffectEndRound().getCrud().getCancel());
        tryClick(c_.getValue().getTeamMove().getCrud().getAdd());
        tryClick(c_.getValue().getTeamMove().getCrud().getValidAddEdit());
        tryClick(c_.getValue().getTeamMove().getCrud().getAllButtons().get(0));
        tryClick(c_.getValue().getTeamMove().getCrud().getCancel());
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAllButtons().get(0));
        tryClick(c_.getCancel());
        assertEq(1,facade_.getData().getCombos().getEffects().size());
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getList().size());
        assertEq(M_1,facade_.getData().getCombos().getEffects().get(0).getList().get(0));
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getCombo().getEffectEndRound().size());
        assertEq(Rate.one(),facade_.getData().getCombos().getEffects().get(0).getCombo().getEffectEndRound().get(0).getInflictedRateHpTarget());
        assertEq(1,facade_.getData().getCombos().getEffects().get(0).getCombo().getTeamMove().size());
    }
    private CrudGeneFormCombos crud(WindowPkEditor _crud) {
        tryClick(_crud.getCombosMenu());
        return _crud.getCrudGeneFormCombos();
    }

    private CrudGeneFormTr crudTrMv(WindowPkEditor _crud) {
        tryClick(_crud.getTrsMvMenu());
        return _crud.getCrudGeneFormMvTr();
    }
}
