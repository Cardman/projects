package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.TypePointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.StringUtil;

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
    private final AbstractProgramInfos frames;

    public FrameTpFormContent(AbstractProgramInfos _c) {
        frames = _c;
        guiInsStackForm = new GuiStackForm(_c);
        guiStaStackForm = new GuiStackForm(_c);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valPointsKind(_d.getFrames().currentLg());
        edited = _d.getFrames().getCompoFactory().newPlainLabel(AbsEditorTabList.EMPTY_STRING);
        instanceType = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INSTANCE)));
        staticType = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_STATIC)));
        enabledBp = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENABLED)));
        fileName = _d.getFrames().getCompoFactory().newTextField();
        caret = _d.getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        ok = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_VALIDATE)));
        remove = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_REMOVE)));
        AbsPanel tpForm_ = _d.getFrames().getCompoFactory().newPageBox();
        tpForm_.add(enabledBp);
        tpForm_.add(instanceType);
        tpForm_.add(staticType);
        tabs = _d.getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(tabs, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INSTANCE)), guiInsStackForm.guiBuild(_d));
        putStForm(tabs, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_STATIC)), guiStaStackForm.guiBuild(_d));
        tpForm_.add(tabs);
        tpForm_.add(fileName);
        tpForm_.add(caret);
        tpForm_.add(ok);
        tpForm_.add(remove);
        contentPane = tpForm_;
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
            getEdited().setText(AbsEditorTabList.EMPTY_STRING);
            remove.setEnabled(false);
        }
    }

    public AbsTextField getFileName() {
        return fileName;
    }

    public AbsSpinner getCaret() {
        return caret;
    }

    public AbsButton getRemove() {
        return remove;
    }

    public AbsPlainLabel getEdited() {
        return edited;
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkTpFormEvent(_d, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveTpFormEvent(_d, this, _p, _r));
        getGuiInsStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiStaStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
    }

    public AbstractProgramInfos getFrames() {
        return frames;
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
