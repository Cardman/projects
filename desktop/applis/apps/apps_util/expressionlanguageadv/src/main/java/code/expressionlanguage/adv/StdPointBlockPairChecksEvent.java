package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.gui.events.AbsActionListener;

public final class StdPointBlockPairChecksEvent implements AbsActionListener {
    private final DependantPointsForm frame;
    private final StdMethodPointBlockPair excPointBlockPair;

    public StdPointBlockPairChecksEvent(DependantPointsForm _f, StdMethodPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
