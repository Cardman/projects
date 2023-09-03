package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.gui.events.AbsActionListener;

public final class WpPointBlockPairChecksEvent implements AbsActionListener {
    private final DependantPointsForm frame;
    private final WatchPointBlockPair excPointBlockPair;

    public WpPointBlockPairChecksEvent(DependantPointsForm _f, WatchPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
