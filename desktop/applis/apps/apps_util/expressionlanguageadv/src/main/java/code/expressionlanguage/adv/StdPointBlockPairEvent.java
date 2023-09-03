package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class StdPointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final StdMethodPointBlockPair excPointBlockPair;
    private final ResultContext resultContext;

    public StdPointBlockPairEvent(FramePoints _f, StdMethodPointBlockPair _ex, ResultContext _r) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
        this.resultContext = _r;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair,resultContext);
    }
}
