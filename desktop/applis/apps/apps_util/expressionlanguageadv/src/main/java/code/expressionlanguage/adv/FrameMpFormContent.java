package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;

public final class FrameMpFormContent extends AbsFrameMpFormContent {
    private MethodPointBlockPair selectedMp;

    @Override
    public void guiBuildBase(AbsDebuggerGui _d) {
        guiBuild(_d);
        getOk().addActionListener(new OkMpFormEvent(_d));
    }

    public MethodPointBlockPair getSelectedMp() {
        return selectedMp;
    }

    public void setSelectedMp(MethodPointBlockPair _s) {
        this.selectedMp = _s;
    }

}
