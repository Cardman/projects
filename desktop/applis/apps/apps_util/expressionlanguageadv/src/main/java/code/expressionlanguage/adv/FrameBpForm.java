package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameBpForm {
    private final AbsCommonFrame commonFrame;
    private final FrameBpFormContent frameBpFormContent;
    public FrameBpForm(AbsDebuggerGui _d,String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelBpFormEvent(_d));
        frameBpFormContent = new FrameBpFormContent();
    }
    public void guiBuild(AbsDebuggerGui _d) {
        frameBpFormContent.guiBuild(_d);
        commonFrame.setContentPane(frameBpFormContent.getContentPane());
    }

    public void refresh(StringMap<String> _v) {
        getFrameBpFormContent().refresh(_v);
    }

    public FrameBpFormContent getFrameBpFormContent() {
        return frameBpFormContent;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public BreakPointBlockPair getSelectedBp() {
        return getFrameBpFormContent().getSelectedBp();
    }

    public void setSelectedBp(BreakPointBlockPair _s) {
        this.getFrameBpFormContent().setSelectedBp(_s);
    }

    public AbsCustCheckBox getInstanceType() {
        return getFrameBpFormContent().getInstanceType();
    }

    public AbsCustCheckBox getStaticType() {
        return getFrameBpFormContent().getStaticType();
    }

    public AbsCustCheckBox getEnabledBp() {
        return getFrameBpFormContent().getEnabledBp();
    }

    public AbsPlainButton getOk() {
        return getFrameBpFormContent().getOk();
    }

    public GuiStackForm getGuiInsStackForm() {
        return getFrameBpFormContent().getGuiInsStackForm();
    }

    public GuiStackForm getGuiStdStackForm() {
        return getFrameBpFormContent().getGuiStdStackForm();
    }

    public GuiStackForm getGuiStaStackForm() {
        return getFrameBpFormContent().getGuiStaStackForm();
    }
}
