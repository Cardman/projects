package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.util.StringMap;

public final class FrameBpFormContent {
    private final GuiStackForm guiStdStackForm = new GuiStackForm();
    private final GuiStackForm guiInsStackForm = new GuiStackForm();
    private final GuiStackForm guiStaStackForm = new GuiStackForm();
    private BreakPointBlockPair selectedBp;
    private AbsCustCheckBox instanceType;
    private AbsCustCheckBox staticType;
    private AbsCustCheckBox enabledBp;
    private AbsPlainButton ok;
    private AbsPanel contentPane;
    public void guiBuild(AbsDebuggerGui _d) {
        instanceType = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("instance");
        staticType = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("static");
        enabledBp = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledBp);
        bpForm_.add(instanceType);
        bpForm_.add(staticType);
        bpForm_.add(guiStdStackForm.guiBuild(_d));
        bpForm_.add(guiInsStackForm.guiBuild(_d));
        bpForm_.add(guiStaStackForm.guiBuild(_d));
        ok.addActionListener(new OkBpFormEvent(_d));
        bpForm_.add(ok);
        contentPane = bpForm_;
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public void refresh(StringMap<String> _v) {
        getGuiStdStackForm().refresh(_v, "");
        getGuiInsStackForm().refresh(_v, "");
        getGuiStaStackForm().refresh(_v, "");
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

    public AbsPlainButton getOk() {
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
