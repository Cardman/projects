package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class WpPointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final WatchPointBlockPair excPointBlockPair;
    private final ResultContext resultContext;

    public WpPointBlockPairEvent(FramePoints _f, WatchPointBlockPair _ex, ResultContext _r) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
        this.resultContext = _r;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair,resultContext);
    }
}
