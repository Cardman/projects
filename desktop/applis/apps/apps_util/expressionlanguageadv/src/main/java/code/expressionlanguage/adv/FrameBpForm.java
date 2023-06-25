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
    private AbsTextArea conditionalInstance;
    private AbsSpinner countInstance;
    private AbsCustCheckBox staticType;
    private AbsTextArea conditionalStatic;
    private AbsSpinner countStatic;
    private AbsCustCheckBox enabledBp;
    private AbsTextArea conditionalStd;
    private AbsSpinner countStd;
    private AbsPlainButton ok;
    public FrameBpForm(AbsDebuggerGui _d,String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelBpFormEvent(_d));
    }
    public void guiBuild(AbsDebuggerGui _d) {
        instanceType = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("instance");
        conditionalInstance = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        countInstance = getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        staticType = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("static");
        conditionalStatic = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        countStatic = getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        enabledBp = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        conditionalStd = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        countStd = getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        ok = getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        AbsPanel bpForm_ = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledBp);
        bpForm_.add(conditionalStd);
        bpForm_.add(countStd);
        bpForm_.add(instanceType);
        bpForm_.add(conditionalInstance);
        bpForm_.add(countInstance);
        bpForm_.add(staticType);
        bpForm_.add(conditionalStatic);
        bpForm_.add(countStatic);
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

    public AbsTextArea getConditionalInstance() {
        return conditionalInstance;
    }

    public AbsSpinner getCountInstance() {
        return countInstance;
    }

    public AbsTextArea getConditionalStatic() {
        return conditionalStatic;
    }

    public AbsSpinner getCountStatic() {
        return countStatic;
    }

    public AbsTextArea getConditionalStd() {
        return conditionalStd;
    }

    public AbsSpinner getCountStd() {
        return countStd;
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
