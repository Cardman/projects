package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameBpForm {
    private final AbsCommonFrame commonFrame;
    private final GuiStackForm guiStdStackForm = new GuiStackForm();
    private final GuiStackForm guiInsStackForm = new GuiStackForm();
    private final GuiStackForm guiStaStackForm = new GuiStackForm();
    private BreakPointBlockPair selectedPb;
    private AbsCustCheckBox instanceType;
    private AbsCustCheckBox staticType;
    private AbsCustCheckBox enabledBp;
    private AbsPlainButton ok;
    public FrameBpForm(AbsDebuggerGui _d,String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelBpFormEvent(_d));
    }
    public void guiBuild(AbsDebuggerGui _d) {
        instanceType = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("instance");
        staticType = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("static");
        enabledBp = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        AbsPanel bpForm_ = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledBp);
        bpForm_.add(instanceType);
        bpForm_.add(staticType);
        bpForm_.add(guiStdStackForm.guiBuild(_d));
        bpForm_.add(guiInsStackForm.guiBuild(_d));
        bpForm_.add(guiStaStackForm.guiBuild(_d));
        ok.addActionListener(new OkBpFormEvent(_d));
        bpForm_.add(ok);
        commonFrame.setContentPane(bpForm_);
    }

    public void refresh(StringMap<String> _v) {
        getGuiStdStackForm().refresh(_v, "");
        getGuiInsStackForm().refresh(_v, "");
        getGuiStaStackForm().refresh(_v, "");
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public BreakPointBlockPair getSelectedPb() {
        return selectedPb;
    }

    public void setSelectedPb(BreakPointBlockPair _s) {
        this.selectedPb = _s;
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
