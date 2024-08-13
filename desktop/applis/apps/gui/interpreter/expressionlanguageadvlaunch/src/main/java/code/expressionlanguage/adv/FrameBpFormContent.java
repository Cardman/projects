package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPoint;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FrameBpFormContent {
    private AbsTextField fileName;
    private AbsSpinner caret;
    private final GuiStackForm guiStdStackForm;
    private BreakPointBlockPair selectedBp;
    private AbsCustCheckBox enabledBp;
    private AbsButton ok;
    private AbsPlainLabel edited;
    private AbsButton remove;
    private AbsPanel contentPane;
    private AbsTabbedPane tabs;

    public FrameBpFormContent(AbstractProgramInfos _c) {
        guiStdStackForm = new GuiStackForm(_c);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valPointsKind(_d.getFrames().currentLg());
        edited = _d.getCommonFrame().getFrames().getCompoFactory().newPlainLabel(AbsEditorTabList.EMPTY_STRING);
        enabledBp = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENABLED)));
        fileName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        caret = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_VALIDATE)));
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_REMOVE)));
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledBp);
        tabs = _d.getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(tabs, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_STD)), guiStdStackForm.guiBuild(_d));
        bpForm_.add(tabs);
        bpForm_.add(fileName);
        bpForm_.add(caret);
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }

    private void putStForm(AbsTabbedPane _tab, String _title, AbsScrollPane _compo) {
        _tab.addIntTab(_title, _compo);
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }
    public void initForm(BreakPointBlockPair _wp, AbsCommonFrame _c, ResultContext _r) {
        setSelectedBp(_wp);
        BreakPointBlockPair exc_ = getSelectedBp();
        tabs.removeAll();
        if (exc_ != null) {
            BreakPointFormEvent.bpAction(exc_, _c, this, _r);
            remove.setEnabled(true);
        } else {
            getGuiStdStackForm().getDependantPointsForm().init(_r, BreakPoint.BP);
            getEdited().setText(AbsEditorTabList.EMPTY_STRING);
            remove.setEnabled(false);
        }
    }

    public AbsButton getRemove() {
        return remove;
    }

    public AbsPlainLabel getEdited() {
        return edited;
    }

    public AbsTextField getFileName() {
        return fileName;
    }

    public AbsSpinner getCaret() {
        return caret;
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkBpFormEvent(_d, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveBpFormEvent(_d, this, _p, _r));
        getGuiStdStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
    }

    public BreakPointBlockPair getSelectedBp() {
        return selectedBp;
    }

    public void setSelectedBp(BreakPointBlockPair _s) {
        this.selectedBp = _s;
    }

    public AbsCustCheckBox getEnabledBp() {
        return enabledBp;
    }

    public AbsButton getOk() {
        return ok;
    }

    public GuiStackForm getGuiStdStackForm() {
        return guiStdStackForm;
    }

}
