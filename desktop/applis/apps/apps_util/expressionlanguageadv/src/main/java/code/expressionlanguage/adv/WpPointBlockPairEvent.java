package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.gui.events.AbsActionListener;

public final class WpPointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final WatchPointBlockPair excPointBlockPair;

    public WpPointBlockPairEvent(FramePoints _f, WatchPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
