package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.gui.*;
import code.util.StringMap;

public final class FrameWpFormContent {
    private final GuiStackForm guiReadStackForm = new GuiStackForm();
    private final GuiStackForm guiWriteStackForm = new GuiStackForm();
    private final GuiStackForm guiCompoundReadStackForm = new GuiStackForm();
    private final GuiStackForm guiCompoundWriteStackForm = new GuiStackForm();
    private final GuiStackForm guiCompoundWriteErrStackForm = new GuiStackForm();
    private WatchPointBlockPair selectedWp;
    private AbsTextField className;
    private AbsTextField fieldName;
    private AbsPlainLabel edited;
    private AbsCustCheckBox trueField;
    private AbsCustCheckBox read;
    private AbsCustCheckBox write;
    private AbsCustCheckBox compoundRead;
    private AbsCustCheckBox compoundWrite;
    private AbsCustCheckBox compoundWriteErr;
    private AbsCustCheckBox enabledWp;
    private AbsPlainButton ok;
    private AbsPlainButton remove;
    private AbsPanel contentPane;
    public void guiBuild(AbsDebuggerGui _d, FramePoints _p) {
        edited = _d.getCommonFrame().getFrames().getCompoFactory().newPlainLabel("");
        className = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        fieldName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        read = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("read");
        write = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("write");
        trueField = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("true field");
        compoundRead = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound read");
        compoundWrite = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound write");
        compoundWriteErr = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound write error");
        enabledWp = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        remove.addActionListener(new OkRemoveWatchFormEvent(_d, this, _p));
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(edited);
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
        bpForm_.add(remove);
        contentPane = bpForm_;
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public void refresh(StringMap<String> _v) {
        getGuiCompoundReadStackForm().refresh(_v, "");
        getGuiCompoundWriteErrStackForm().refresh(_v, "");
        getGuiCompoundWriteStackForm().refresh(_v, "");
        getGuiReadStackForm().refresh(_v, "");
        getGuiWriteStackForm().refresh(_v, "");
    }

    public void initForm(WatchPointBlockPair _wp, AbsCommonFrame _c) {
        setSelectedWp(_wp);
        WatchPointBlockPair exc_ = getSelectedWp();
        if (exc_ != null) {
            WatchPointFormEvent.watchAction(this, _c,exc_);
            remove.setEnabled(true);
            className.setEnabled(false);
            fieldName.setEnabled(false);
            trueField.setEnabled(false);
        } else {
            getEdited().setText("");
            remove.setEnabled(false);
            className.setEnabled(true);
            fieldName.setEnabled(true);
            trueField.setEnabled(true);
        }
    }

    public AbsTextField getClassName() {
        return className;
    }

    public AbsCustCheckBox getTrueField() {
        return trueField;
    }

    public AbsTextField getFieldName() {
        return fieldName;
    }

    public AbsPlainButton getRemove() {
        return remove;
    }

    public AbsPlainLabel getEdited() {
        return edited;
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
