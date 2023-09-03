package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.util.StringMap;

public final class FrameExcFormContent {
    private final GuiStackForm guiThrownStackForm = new GuiStackForm();
    private final GuiStackForm guiCaughtStackForm = new GuiStackForm();
    private final GuiStackForm guiPropagatedStackForm = new GuiStackForm();
    private ExcPointBlockPair selectedExc;
    private AbsTextField clName;
    private AbsCustCheckBox exact;
    private AbsCustCheckBox thrown;
    private AbsCustCheckBox caught;
    private AbsCustCheckBox propagated;
    private AbsCustCheckBox enabledExc;
    private AbsPlainButton ok;
    private AbsPlainButton remove;
    private AbsPanel contentPane;
    public void guiBuild(AbsDebuggerGui _d) {
        thrown = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("thrown");
        caught = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("caught");
        propagated = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("propagated");
        enabledExc = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        clName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        exact = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("exact");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(exact);
        bpForm_.add(clName);
        bpForm_.add(enabledExc);
        bpForm_.add(thrown);
        bpForm_.add(caught);
        bpForm_.add(propagated);
        bpForm_.add(guiThrownStackForm.guiBuild(_d));
        bpForm_.add(guiCaughtStackForm.guiBuild(_d));
        bpForm_.add(guiPropagatedStackForm.guiBuild(_d));
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }
    public void initForm(ExcPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedExc(_s);
        ExcPointBlockPair exc_ = getSelectedExc();
        if (exc_ != null) {
            exact.setEnabled(false);
            exact.setSelected(exc_.getEp().isExact());
            clName.setEnabled(false);
            clName.setText(exc_.getEp().getClName());
            remove.setEnabled(true);
            getEnabledExc().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getGuiThrownStackForm(), true, exc_.getValue().getResultThrown(), _f,_r);
            BreakPointFormEvent.specific(getGuiCaughtStackForm(), true, exc_.getValue().getResultCaught(), _f,_r);
            BreakPointFormEvent.specific(getGuiPropagatedStackForm(), true, exc_.getValue().getResultPropagated(), _f,_r);
            getThrown().setSelected(exc_.getValue().isThrown());
            getCaught().setSelected(exc_.getValue().isCaught());
            getPropagated().setSelected(exc_.getValue().isPropagated());
        } else {
            getGuiThrownStackForm().getDependantPointsForm().init(_r);
            getGuiCaughtStackForm().getDependantPointsForm().init(_r);
            getGuiPropagatedStackForm().getDependantPointsForm().init(_r);
            exact.setEnabled(true);
            clName.setEnabled(true);
            remove.setEnabled(false);
        }
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkExcFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveExcFormEvent(_d, this, _p, _r));
        getGuiThrownStackForm().refresh(_v, "", _r, _d);
        getGuiCaughtStackForm().refresh(_v, "", _r, _d);
        getGuiPropagatedStackForm().refresh(_v, "", _r, _d);
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public ExcPointBlockPair getSelectedExc() {
        return selectedExc;
    }

    public void setSelectedExc(ExcPointBlockPair _s) {
        this.selectedExc = _s;
    }

    public AbsCustCheckBox getExact() {
        return exact;
    }

    public AbsTextField getClName() {
        return clName;
    }

    public AbsCustCheckBox getThrown() {
        return thrown;
    }

    public AbsCustCheckBox getCaught() {
        return caught;
    }

    public AbsCustCheckBox getPropagated() {
        return propagated;
    }

    public AbsCustCheckBox getEnabledExc() {
        return enabledExc;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public AbsPlainButton getRemove() {
        return remove;
    }

    public GuiStackForm getGuiThrownStackForm() {
        return guiThrownStackForm;
    }

    public GuiStackForm getGuiCaughtStackForm() {
        return guiCaughtStackForm;
    }

    public GuiStackForm getGuiPropagatedStackForm() {
        return guiPropagatedStackForm;
    }
}
