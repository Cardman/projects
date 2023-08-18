package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.gui.AbsCommonFrame;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameWpForm {
    private final AbsCommonFrame commonFrame;
    private final GuiStackForm guiReadStackForm = new GuiStackForm();
    private final GuiStackForm guiWriteStackForm = new GuiStackForm();
    private final GuiStackForm guiCompoundReadStackForm = new GuiStackForm();
    private final GuiStackForm guiCompoundWriteStackForm = new GuiStackForm();
    private final GuiStackForm guiCompoundWriteErrStackForm = new GuiStackForm();
    private WatchPointBlockPair selectedWp;
    private AbsCustCheckBox read;
    private AbsCustCheckBox write;
    private AbsCustCheckBox compoundRead;
    private AbsCustCheckBox compoundWrite;
    private AbsCustCheckBox compoundWriteErr;
    private AbsCustCheckBox enabledWp;
    private AbsPlainButton ok;
    public FrameWpForm(AbsDebuggerGui _d, String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelWpFormEvent(_d));
    }
    public void guiBuild(AbsDebuggerGui _d) {
        read = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("read");
        write = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("write");
        compoundRead = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound read");
        compoundWrite = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound write");
        compoundWriteErr = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound write error");
        enabledWp = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        AbsPanel bpForm_ = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledWp);
        bpForm_.add(read);
        bpForm_.add(write);
        bpForm_.add(compoundRead);
        bpForm_.add(compoundWrite);
        bpForm_.add(compoundWriteErr);
        bpForm_.add(guiReadStackForm.guiBuild(_d));
        bpForm_.add(guiWriteStackForm.guiBuild(_d));
        bpForm_.add(guiCompoundReadStackForm.guiBuild(_d));
        bpForm_.add(guiCompoundWriteStackForm.guiBuild(_d));
        bpForm_.add(guiCompoundWriteErrStackForm.guiBuild(_d));
        ok.addActionListener(new OkWpFormEvent(_d));
        bpForm_.add(ok);
        commonFrame.setContentPane(bpForm_);
    }

    public void refresh(StringMap<String> _v) {
        getGuiCompoundReadStackForm().refresh(_v, "");
        getGuiCompoundWriteErrStackForm().refresh(_v, "");
        getGuiCompoundWriteStackForm().refresh(_v, "");
        getGuiReadStackForm().refresh(_v, "");
        getGuiWriteStackForm().refresh(_v, "");
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public WatchPointBlockPair getSelectedWp() {
        return selectedWp;
    }

    public void setSelectedWp(WatchPointBlockPair _s) {
        this.selectedWp = _s;
    }

    public AbsCustCheckBox getRead() {
        return read;
    }

    public AbsCustCheckBox getWrite() {
        return write;
    }

    public AbsCustCheckBox getCompoundRead() {
        return compoundRead;
    }

    public AbsCustCheckBox getCompoundWrite() {
        return compoundWrite;
    }

    public AbsCustCheckBox getCompoundWriteErr() {
        return compoundWriteErr;
    }


    public AbsCustCheckBox getEnabledWp() {
        return enabledWp;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public GuiStackForm getGuiCompoundReadStackForm() {
        return guiCompoundReadStackForm;
    }

    public GuiStackForm getGuiCompoundWriteErrStackForm() {
        return guiCompoundWriteErrStackForm;
    }

    public GuiStackForm getGuiCompoundWriteStackForm() {
        return guiCompoundWriteStackForm;
    }

    public GuiStackForm getGuiReadStackForm() {
        return guiReadStackForm;
    }

    public GuiStackForm getGuiWriteStackForm() {
        return guiWriteStackForm;
    }

}
