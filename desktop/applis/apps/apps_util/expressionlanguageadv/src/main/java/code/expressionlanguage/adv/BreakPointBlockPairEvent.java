package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.gui.events.AbsActionListener;

public final class BreakPointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final BreakPointBlockPair excPointBlockPair;

    public BreakPointBlockPairEvent(FramePoints _f, BreakPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
