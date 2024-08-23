package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.WatchPoint;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FrameWpFormContent {
    private final GuiStackForm guiReadStackForm;
    private final GuiStackForm guiWriteStackForm;
    private final GuiStackForm guiCompoundReadStackForm;
    private final GuiStackForm guiCompoundWriteStackForm;
    private final GuiStackForm guiCompoundWriteErrStackForm;
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
    private AbsButton ok;
    private AbsButton remove;
    private AbsPanel contentPane;
    private final AbstractProgramInfos frames;

    public FrameWpFormContent(AbstractProgramInfos _c) {
        frames = _c;
        guiReadStackForm = new GuiStackForm(_c);
        guiWriteStackForm = new GuiStackForm(_c);
        guiCompoundReadStackForm = new GuiStackForm(_c);
        guiCompoundWriteStackForm = new GuiStackForm(_c);
        guiCompoundWriteErrStackForm = new GuiStackForm(_c);
    }

    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valPointsKind(_d.getFrames().currentLg());
        edited = _d.getFrames().getCompoFactory().newPlainLabel(AbsEditorTabList.EMPTY_STRING);
        className = _d.getFrames().getCompoFactory().newTextField();
        fieldName = _d.getFrames().getCompoFactory().newTextField();
        read = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_GET)));
        write = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_SET)));
        trueField = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_TRUE_FIELD)));
        compoundRead = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_COMPOUND_GET)));
        compoundWrite = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_COMPOUND_SET)));
        compoundWriteErr = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_COMPOUND_SET_ERR)));
        enabledWp = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENABLED)));
        ok = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_VALIDATE)));
        remove = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_REMOVE)));
        AbsPanel bpForm_ = _d.getFrames().getCompoFactory().newPageBox();
        bpForm_.add(edited);
        bpForm_.add(enabledWp);
        bpForm_.add(read);
        bpForm_.add(write);
        bpForm_.add(compoundRead);
        bpForm_.add(compoundWrite);
        bpForm_.add(compoundWriteErr);
        AbsTabbedPane tab_ = _d.getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(_d, tab_, guiReadStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_GET)));
        putStForm(_d, tab_, guiWriteStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_SET)));
        putStForm(_d, tab_, guiCompoundReadStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_COMPOUND_GET)));
        putStForm(_d, tab_, guiCompoundWriteStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_COMPOUND_SET)));
        putStForm(_d, tab_, guiCompoundWriteErrStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_WP_COMPOUND_SET_ERR)));
        bpForm_.add(tab_);
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }

    private void putStForm(AbsDebuggerGui _d, AbsTabbedPane _tab, GuiStackForm _target, String _title) {
        _tab.addIntTab(_title,_target.guiBuild(_d));
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveWatchFormEvent(_d, this, _p, _r));
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkWpFormEvent(_d, _r));
        getGuiCompoundReadStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiCompoundWriteErrStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiCompoundWriteStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiReadStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiWriteStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
    }

    public void initForm(WatchPointBlockPair _wp, AbsCommonFrame _c, ResultContext _r) {
        setSelectedWp(_wp);
        WatchPointBlockPair exc_ = getSelectedWp();
        if (exc_ != null) {
            WatchPointFormEvent.watchAction(this, _c,exc_,_r, frames);
            remove.setEnabled(true);
            className.setEnabled(false);
            fieldName.setEnabled(false);
            trueField.setEnabled(false);
        } else {
            getGuiReadStackForm().getDependantPointsForm().init(_r, WatchPoint.WP);
            getGuiWriteStackForm().getDependantPointsForm().init(_r,WatchPoint.WP);
            getGuiCompoundReadStackForm().getDependantPointsForm().init(_r,WatchPoint.WP);
            getGuiCompoundWriteStackForm().getDependantPointsForm().init(_r,WatchPoint.WP);
            getGuiCompoundWriteErrStackForm().getDependantPointsForm().init(_r,WatchPoint.WP);
            getEdited().setText(AbsEditorTabList.EMPTY_STRING);
            remove.setEnabled(false);
            className.setEnabled(true);
            fieldName.setEnabled(true);
            trueField.setEnabled(true);
        }
    }

    public AbstractProgramInfos getFrames() {
        return frames;
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

    public AbsButton getRemove() {
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

    public AbsButton getOk() {
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
