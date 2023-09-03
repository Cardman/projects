package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ExcPointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final ExcPointBlockPair excPointBlockPair;
    private final ResultContext resultContext;

    public ExcPointBlockPairEvent(FramePoints _f, ExcPointBlockPair _ex, ResultContext _r) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
        this.resultContext = _r;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair,resultContext);
    }
}
