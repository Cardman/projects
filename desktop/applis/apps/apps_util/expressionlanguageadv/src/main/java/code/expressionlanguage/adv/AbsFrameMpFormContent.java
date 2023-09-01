package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.util.StringMap;

public abstract class AbsFrameMpFormContent {
    private final GuiStackForm guiEnterStackForm = new GuiStackForm();
    private final GuiStackForm guiExitStackForm = new GuiStackForm();
    private AbsCustCheckBox enterFunction;
    private AbsCustCheckBox exitFunction;
    private AbsCustCheckBox enabledMp;
    private AbsPlainButton ok;
    private AbsPlainButton remove;
    private AbsPanel contentPaneForm;
    private AbsPanel contentPane;
    protected AbsFrameMpFormContent() {
    }
    public abstract void guiBuildBase(AbsDebuggerGui _d);
    public void guiBuild(AbsDebuggerGui _d) {
        enterFunction = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enter");
        exitFunction = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("exit");
        enabledMp = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledMp);
        bpForm_.add(enterFunction);
        bpForm_.add(exitFunction);
        bpForm_.add(guiEnterStackForm.guiBuild(_d));
        bpForm_.add(guiExitStackForm.guiBuild(_d));
        contentPaneForm = bpForm_;
        contentPane = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
    }

    public AbsPlainButton getRemove() {
        return remove;
    }

    public AbsPanel getContentPaneForm() {
        return contentPaneForm;
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d) {
        getGuiEnterStackForm().refresh(_v, "", _r, _d);
        getGuiExitStackForm().refresh(_v, "", _r, _d);
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public AbsCustCheckBox getEnterFunction() {
        return enterFunction;
    }

    public AbsCustCheckBox getExitFunction() {
        return exitFunction;
    }

    public AbsCustCheckBox getEnabledMp() {
        return enabledMp;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public GuiStackForm getGuiEnterStackForm() {
        return guiEnterStackForm;
    }

    public GuiStackForm getGuiExitStackForm() {
        return guiExitStackForm;
    }
}
