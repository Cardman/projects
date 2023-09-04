package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.gui.initialize.AbstractProgramInfos;

public final class FrameStdMpFormContent extends AbsFrameMpFormContent {
    private StdMethodPointBlockPair selectedMp;
    public FrameStdMpFormContent(AbstractProgramInfos _c) {
        super(_c);
    }
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
