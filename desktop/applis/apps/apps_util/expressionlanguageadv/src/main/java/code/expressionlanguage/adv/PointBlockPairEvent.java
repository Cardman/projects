package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.gui.events.AbsActionListener;

public final class PointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final MethodPointBlockPair excPointBlockPair;

    public PointBlockPairEvent(FramePoints _f, MethodPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
