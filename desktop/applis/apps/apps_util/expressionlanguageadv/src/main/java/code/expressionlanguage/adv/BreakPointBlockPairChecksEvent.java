package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.gui.events.AbsActionListener;

public final class BreakPointBlockPairChecksEvent implements AbsActionListener {
    private final DependantPointsForm frame;
    private final BreakPointBlockPair excPointBlockPair;

    public BreakPointBlockPairChecksEvent(DependantPointsForm _f, BreakPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
