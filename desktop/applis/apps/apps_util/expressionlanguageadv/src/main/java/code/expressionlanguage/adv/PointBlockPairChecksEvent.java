package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.gui.events.AbsActionListener;

public final class PointBlockPairChecksEvent implements AbsActionListener {
    private final DependantPointsForm frame;
    private final MethodPointBlockPair excPointBlockPair;

    public PointBlockPairChecksEvent(DependantPointsForm _f, MethodPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
