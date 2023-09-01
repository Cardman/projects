package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;

public final class FrameStdMpFormContent extends AbsFrameMpFormContent {
    private StdMethodPointBlockPair selectedMp;

    @Override
    public void guiBuildBase(AbsDebuggerGui _d) {
        guiBuild(_d);

    }

    public StdMethodPointBlockPair getSelectedMp() {
        return selectedMp;
    }

    public void setSelectedMp(StdMethodPointBlockPair _s) {
        this.selectedMp = _s;
    }

}
