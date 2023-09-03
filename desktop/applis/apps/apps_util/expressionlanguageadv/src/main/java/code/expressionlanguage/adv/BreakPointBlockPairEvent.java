package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class BreakPointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final BreakPointBlockPair excPointBlockPair;
    private final ResultContext resultContext;

    public BreakPointBlockPairEvent(FramePoints _f, BreakPointBlockPair _ex, ResultContext _r) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
        this.resultContext = _r;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair,resultContext);
    }
}
