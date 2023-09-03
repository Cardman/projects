package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.gui.events.AbsActionListener;

public final class ExcPointBlockPairChecksEvent implements AbsActionListener {
    private final DependantPointsForm frame;
    private final ExcPointBlockPair excPointBlockPair;

    public ExcPointBlockPairChecksEvent(DependantPointsForm _f, ExcPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
