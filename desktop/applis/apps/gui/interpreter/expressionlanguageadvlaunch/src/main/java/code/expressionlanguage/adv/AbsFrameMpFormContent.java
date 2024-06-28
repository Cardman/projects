package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsButton;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

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
    protected AbsFrameMpFormContent(AbstractProgramInfos _c) {
        guiEnterStackForm = new GuiStackForm(_c);
        guiExitStackForm = new GuiStackForm(_c);
    }
    public abstract void guiBuildBase(AbsDebuggerGui _d);
    public void guiBuild(AbsDebuggerGui _d) {
        enterFunction = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enter");
        exitFunction = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("exit");
        enabledMp = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(enabledMp);
        bpForm_.add(enterFunction);
        bpForm_.add(exitFunction);
        AbsTabbedPane tab_ = _d.getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(_d, tab_, guiEnterStackForm, "enter");
        putStForm(_d, tab_, guiExitStackForm, "exit");
        bpForm_.add(tab_);
        contentPaneForm = bpForm_;
        contentPane = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
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
        getGuiEnterStackForm().refresh(_v, "", _r, _d);
        getGuiExitStackForm().refresh(_v, "", _r, _d);
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
