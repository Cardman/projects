package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.gui.AbsPlainButton;

public final class FrameStdMpFormContent extends AbsFrameMpFormContent {
    private StdMethodPointBlockPair selectedMp;
    private AbsPlainButton remove;

    @Override
    public void guiBuildBase(AbsDebuggerGui _d) {
        guiBuild(_d);
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");

    }

    public AbsPlainButton getRemove() {
        return remove;
    }

    public StdMethodPointBlockPair getSelectedMp() {
        return selectedMp;
    }

    public void setSelectedMp(StdMethodPointBlockPair _s) {
        this.selectedMp = _s;
    }

}
