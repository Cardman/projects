package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.gui.events.AbsActionListener;

public final class StdPointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final StdMethodPointBlockPair excPointBlockPair;

    public StdPointBlockPairEvent(FramePoints _f, StdMethodPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
