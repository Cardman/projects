package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.TypePointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameTpFormContent {
    private AbsTextField fileName;
    private AbsSpinner caret;
    private final GuiStackForm guiInsStackForm;
    private final GuiStackForm guiStaStackForm;
    private TypePointBlockPair selectedTp;
    private AbsCustCheckBox instanceType;
    private AbsCustCheckBox staticType;
    private AbsCustCheckBox enabledBp;
    private AbsButton ok;
    private AbsPlainLabel edited;
    private AbsButton remove;
    private AbsPanel contentPane;
    private AbsTabbedPane tabs;

    public FrameTpFormContent(AbstractProgramInfos _c) {
        guiInsStackForm = new GuiStackForm(_c);
        guiStaStackForm = new GuiStackForm(_c);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        edited = _d.getCommonFrame().getFrames().getCompoFactory().newPlainLabel("");
        instanceType = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("instance");
        staticType = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("static");
        enabledBp = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        fileName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        caret = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledBp);
        bpForm_.add(instanceType);
        bpForm_.add(staticType);
        tabs = _d.getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(tabs, "instance", guiInsStackForm.guiBuild(_d));
        putStForm(tabs, "static", guiStaStackForm.guiBuild(_d));
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
    public void initForm(TypePointBlockPair _wp, AbsCommonFrame _c, ResultContext _r) {
        setSelectedTp(_wp);
        TypePointBlockPair exc_ = getSelectedTp();
        tabs.removeAll();
        if (exc_ != null) {
            BreakPointFormEvent.tpAction(exc_, _c, this, _r);
            remove.setEnabled(true);
        } else {
            getGuiInsStackForm().getDependantPointsForm().init(_r, FramePointsTree.SORT_TP);
            getGuiStaStackForm().getDependantPointsForm().init(_r, FramePointsTree.SORT_TP);
            getEdited().setText("");
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
        ok.addActionListener(new OkTpFormEvent(_d, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveTpFormEvent(_d, this, _p, _r));
        getGuiInsStackForm().refresh(_v, "", _r, _d);
        getGuiStaStackForm().refresh(_v, "", _r, _d);
    }

    public TypePointBlockPair getSelectedTp() {
        return selectedTp;
    }

    public void setSelectedTp(TypePointBlockPair _s) {
        this.selectedTp = _s;
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

    public AbsButton getOk() {
        return ok;
    }

    public GuiStackForm getGuiInsStackForm() {
        return guiInsStackForm;
    }

    public GuiStackForm getGuiStaStackForm() {
        return guiStaStackForm;
    }
}
