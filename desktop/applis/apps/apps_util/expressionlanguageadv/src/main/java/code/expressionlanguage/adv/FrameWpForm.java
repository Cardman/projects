package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.gui.AbsCommonFrame;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPlainButton;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameWpForm {
    private final AbsCommonFrame commonFrame;
    private final FrameWpFormContent frameWpFormContent;
    public FrameWpForm(AbsDebuggerGui _d, String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelWpFormEvent(_d));
        frameWpFormContent = new FrameWpFormContent();
    }
    public void guiBuild(AbsDebuggerGui _d) {
        frameWpFormContent.guiBuild(_d);
        commonFrame.setContentPane(frameWpFormContent.getContentPane());
    }

    public void refresh(StringMap<String> _v) {
        getFrameWpFormContent().refresh(_v);
    }

    public FrameWpFormContent getFrameWpFormContent() {
        return frameWpFormContent;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public WatchPointBlockPair getSelectedWp() {
        return getFrameWpFormContent().getSelectedWp();
    }

    public void setSelectedWp(WatchPointBlockPair _s) {
        this.getFrameWpFormContent().setSelectedWp(_s);
    }

    public AbsCustCheckBox getRead() {
        return getFrameWpFormContent().getRead();
    }

    public AbsCustCheckBox getWrite() {
        return getFrameWpFormContent().getWrite();
    }

    public AbsCustCheckBox getCompoundRead() {
        return getFrameWpFormContent().getCompoundRead();
    }

    public AbsCustCheckBox getCompoundWrite() {
        return getFrameWpFormContent().getCompoundWrite();
    }

    public AbsCustCheckBox getCompoundWriteErr() {
        return getFrameWpFormContent().getCompoundWriteErr();
    }


    public AbsCustCheckBox getEnabledWp() {
        return getFrameWpFormContent().getEnabledWp();
    }

    public AbsPlainButton getOk() {
        return getFrameWpFormContent().getOk();
    }

    public GuiStackForm getGuiCompoundReadStackForm() {
        return getFrameWpFormContent().getGuiCompoundReadStackForm();
    }

    public GuiStackForm getGuiCompoundWriteErrStackForm() {
        return getFrameWpFormContent().getGuiCompoundWriteErrStackForm();
    }

    public GuiStackForm getGuiCompoundWriteStackForm() {
        return getFrameWpFormContent().getGuiCompoundWriteStackForm();
    }

    public GuiStackForm getGuiReadStackForm() {
        return getFrameWpFormContent().getGuiReadStackForm();
    }

    public GuiStackForm getGuiWriteStackForm() {
        return getFrameWpFormContent().getGuiWriteStackForm();
    }

}
