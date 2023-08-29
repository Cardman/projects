package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.gui.AbsSpinner;

public final class FrameMpFormContent extends AbsFrameMpFormContent {
    private MethodPointBlockPair selectedMp;
    private AbsSpinner pref;

    @Override
    public void guiBuildBase(AbsDebuggerGui _d) {
        guiBuild(_d);
        pref = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        getOk().addActionListener(new OkMpFormEvent(_d));
    }

    public MethodPointBlockPair getSelectedMp() {
        return selectedMp;
    }

    public void setSelectedMp(MethodPointBlockPair _s) {
        this.selectedMp = _s;
    }

    public AbsSpinner getPref() {
        return pref;
    }
}
