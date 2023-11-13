package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPoint;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameBpFormContent {
    private AbsTextField fileName;
    private AbsSpinner caret;
    private final GuiStackForm guiStdStackForm;
    private final GuiStackForm guiInsStackForm;
    private final GuiStackForm guiStaStackForm;
    private BreakPointBlockPair selectedBp;
    private AbsCustCheckBox instanceType;
    private AbsCustCheckBox staticType;
    private AbsCustCheckBox enabledBp;
    private AbsButton ok;
    private AbsPlainLabel edited;
    private AbsButton remove;
    private AbsPanel contentPane;
    public FrameBpFormContent(AbstractProgramInfos _c) {
        guiStdStackForm = new GuiStackForm(_c);
        guiInsStackForm = new GuiStackForm(_c);
        guiStaStackForm = new GuiStackForm(_c);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        edited = _d.getCommonFrame().getFrames().getCompoFactory().newPlainLabel("");
        instanceType = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("instance");
        staticType = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("static");
        enabledBp = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        fileName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        caret = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledBp);
        bpForm_.add(instanceType);
        bpForm_.add(staticType);
        bpForm_.add(guiStdStackForm.guiBuild(_d));
        bpForm_.add(guiInsStackForm.guiBuild(_d));
        bpForm_.add(guiStaStackForm.guiBuild(_d));
        bpForm_.add(fileName);
        bpForm_.add(caret);
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }
    public void initForm(BreakPointBlockPair _wp, AbsCommonFrame _c, ResultContext _r) {
        setSelectedBp(_wp);
        BreakPointBlockPair exc_ = getSelectedBp();
        if (exc_ != null) {
            BreakPointFormEvent.bpAction(exc_, _c, this, _r);
            remove.setEnabled(true);
        } else {
            getGuiStdStackForm().getDependantPointsForm().init(_r, BreakPoint.BP);
            getGuiInsStackForm().getDependantPointsForm().init(_r, BreakPoint.BP);
            getGuiStaStackForm().getDependantPointsForm().init(_r, BreakPoint.BP);
            getEdited().setText("");
            remove.setEnabled(false);
        }
    }

    public AbsButton getRemove() {
        return remove;
    }

    public AbsPlainLabel getEdited() {
        return edited;
    }

    public AbsTextField getFileName() {
        return fileName;
    }

    public AbsSpinner getCaret() {
        return caret;
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkBpFormEvent(_d, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveBpFormEvent(_d, this, _p, _r));
        getGuiStdStackForm().refresh(_v, "", _r, _d);
        getGuiInsStackForm().refresh(_v, "", _r, _d);
        getGuiStaStackForm().refresh(_v, "", _r, _d);
    }

    public BreakPointBlockPair getSelectedBp() {
        return selectedBp;
    }

    public void setSelectedBp(BreakPointBlockPair _s) {
        this.selectedBp = _s;
    }

    public AbsCustCheckBox getInstanceType() {
        return instanceType;
    }

    public AbsCustCheckBox getStaticType() {
        return staticType;
    }

    public AbsCustCheckBox getEnabledBp() {
        return enabledBp;
    }

    public AbsButton getOk() {
        return ok;
    }

    public GuiStackForm getGuiInsStackForm() {
        return guiInsStackForm;
    }

    public GuiStackForm getGuiStdStackForm() {
        return guiStdStackForm;
    }

    public GuiStackForm getGuiStaStackForm() {
        return guiStaStackForm;
    }
}
