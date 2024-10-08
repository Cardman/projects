package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsButton;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class AbsFrameMpFormContent {
    private final GuiStackForm guiEnterStackForm;
    private final GuiStackForm guiExitStackForm;
    private AbsCustCheckBox enterFunction;
    private AbsCustCheckBox exitFunction;
    private AbsCustCheckBox enabledMp;
    private AbsButton ok;
    private AbsButton remove;
    private AbsPanel contentPaneForm;
    private AbsPanel contentPane;
    private final AbstractProgramInfos frames;
    protected AbsFrameMpFormContent(AbstractProgramInfos _c) {
        frames = _c;
        guiEnterStackForm = new GuiStackForm(_c);
        guiExitStackForm = new GuiStackForm(_c);
    }
    public abstract void guiBuildBase(AbsDebuggerGui _d);
    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valPointsKind(_d.getFrames().currentLg());
        enterFunction = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENTRY)));
        exitFunction = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_EXIT)));
        enabledMp = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENABLED)));
        ok = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_VALIDATE)));
        remove = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_REMOVE)));
        AbsPanel bpForm_ = _d.getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledMp);
        bpForm_.add(enterFunction);
        bpForm_.add(exitFunction);
        AbsTabbedPane tab_ = _d.getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(_d, tab_, guiEnterStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENTRY)));
        putStForm(_d, tab_, guiExitStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_EXIT)));
        bpForm_.add(tab_);
        contentPaneForm = bpForm_;
        contentPane = _d.getFrames().getCompoFactory().newPageBox();
    }

    private void putStForm(AbsDebuggerGui _d, AbsTabbedPane _tab, GuiStackForm _target, String _title) {
        _tab.addIntTab(_title,_target.guiBuild(_d));
    }

    public AbsButton getRemove() {
        return remove;
    }

    public AbsPanel getContentPaneForm() {
        return contentPaneForm;
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d) {
        getGuiEnterStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiExitStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
    }

    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public AbsCustCheckBox getEnterFunction() {
        return enterFunction;
    }

    public AbsCustCheckBox getExitFunction() {
        return exitFunction;
    }

    public AbsCustCheckBox getEnabledMp() {
        return enabledMp;
    }

    public AbsButton getOk() {
        return ok;
    }

    public GuiStackForm getGuiEnterStackForm() {
        return guiEnterStackForm;
    }

    public GuiStackForm getGuiExitStackForm() {
        return guiExitStackForm;
    }
}
