package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.util.StringMap;

public final class FrameMpFormContent {
    private final GuiStackForm guiEnterStackForm = new GuiStackForm();
    private final GuiStackForm guiExitStackForm = new GuiStackForm();
    private MethodPointBlockPair selectedMp;
    private AbsCustCheckBox enterFunction;
    private AbsCustCheckBox exitFunction;
    private AbsCustCheckBox enabledMp;
    private AbsPlainButton ok;
    private AbsPanel contentPane;
    public void guiBuild(AbsDebuggerGui _d) {
        enterFunction = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enter");
        exitFunction = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("exit");
        enabledMp = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledMp);
        bpForm_.add(enterFunction);
        bpForm_.add(exitFunction);
        bpForm_.add(guiEnterStackForm.guiBuild(_d));
        bpForm_.add(guiExitStackForm.guiBuild(_d));
        ok.addActionListener(new OkMpFormEvent(_d));
        bpForm_.add(ok);
        contentPane = bpForm_;
    }

    public void refresh(StringMap<String> _v) {
        getGuiEnterStackForm().refresh(_v, "");
        getGuiExitStackForm().refresh(_v, "");
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public MethodPointBlockPair getSelectedMp() {
        return selectedMp;
    }

    public void setSelectedMp(MethodPointBlockPair _s) {
        this.selectedMp = _s;
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
