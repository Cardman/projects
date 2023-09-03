package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class PointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final MethodPointBlockPair excPointBlockPair;
    private final ResultContext resultContext;

    public PointBlockPairEvent(FramePoints _f, MethodPointBlockPair _ex, ResultContext _r) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
        this.resultContext = _r;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair,resultContext);
    }
}
