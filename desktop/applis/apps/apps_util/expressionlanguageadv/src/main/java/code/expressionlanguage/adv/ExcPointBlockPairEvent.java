package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.gui.events.AbsActionListener;

public final class ExcPointBlockPairEvent implements AbsActionListener {
    private final FramePoints frame;
    private final ExcPointBlockPair excPointBlockPair;

    public ExcPointBlockPairEvent(FramePoints _f, ExcPointBlockPair _ex) {
        this.frame = _f;
        this.excPointBlockPair = _ex;
    }

    @Override
    public void action() {
        frame.guiContentBuild(excPointBlockPair);
    }
}
