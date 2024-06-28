package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.gui.initialize.AbstractProgramInfos;

public final class FrameMpFormContent extends AbsFrameMpFormContent {
    private MethodPointBlockPair selectedMp;
    public FrameMpFormContent(AbstractProgramInfos _c) {
        super(_c);
    }

    @Override
    public void guiBuildBase(AbsDebuggerGui _d) {
        guiBuild(_d);
        getGuiEnterStackForm().showPrefs();
        getGuiExitStackForm().showPrefs();
    }

    public MethodPointBlockPair getSelectedMp() {
        return selectedMp;
    }

    public void setSelectedMp(MethodPointBlockPair _s) {
        this.selectedMp = _s;
    }
}
