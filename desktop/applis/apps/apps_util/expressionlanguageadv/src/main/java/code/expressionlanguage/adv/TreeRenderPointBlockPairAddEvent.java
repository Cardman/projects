package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class TreeRenderPointBlockPairAddEvent implements AbsActionListener {
    private final FramePoints frame;

    public TreeRenderPointBlockPairAddEvent(FramePoints _f) {
        this.frame = _f;
    }

    @Override
    public void action() {
        frame.guiContentBuild(null);
    }
}
