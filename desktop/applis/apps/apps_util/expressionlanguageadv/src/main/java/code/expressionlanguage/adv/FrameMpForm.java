package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.gui.AbsCommonFrame;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPlainButton;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameMpForm {
    private final AbsCommonFrame commonFrame;
    private final FrameMpFormContent frameMpFormContent;
    public FrameMpForm(AbsDebuggerGui _d, String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelMpFormEvent(_d));
        frameMpFormContent = new FrameMpFormContent();
    }
    public void guiBuild(AbsDebuggerGui _d) {
        frameMpFormContent.guiBuild(_d);
        commonFrame.setContentPane(frameMpFormContent.getContentPane());
    }

    public void refresh(StringMap<String> _v) {
        getFrameMpFormContent().refresh(_v);
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public FrameMpFormContent getFrameMpFormContent() {
        return frameMpFormContent;
    }

    public MethodPointBlockPair getSelectedMp() {
        return getFrameMpFormContent().getSelectedMp();
    }

    public void setSelectedMp(MethodPointBlockPair _s) {
        this.getFrameMpFormContent().setSelectedMp(_s);
    }

    public AbsCustCheckBox getEnterFunction() {
        return getFrameMpFormContent().getEnterFunction();
    }

    public AbsCustCheckBox getExitFunction() {
        return getFrameMpFormContent().getExitFunction();
    }

    public AbsCustCheckBox getEnabledMp() {
        return getFrameMpFormContent().getEnabledMp();
    }

    public AbsPlainButton getOk() {
        return getFrameMpFormContent().getOk();
    }

    public GuiStackForm getGuiEnterStackForm() {
        return getFrameMpFormContent().getGuiEnterStackForm();
    }

    public GuiStackForm getGuiExitStackForm() {
        return getFrameMpFormContent().getGuiExitStackForm();
    }
}
