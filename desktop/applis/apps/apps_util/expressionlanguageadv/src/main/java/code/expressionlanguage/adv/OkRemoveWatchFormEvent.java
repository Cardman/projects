package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class OkRemoveWatchFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameWpFormContent frameExcFormContent;
    private final FramePoints framePoints;

    public OkRemoveWatchFormEvent(AbsDebuggerGui _w, FrameWpFormContent _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        window.getCurrentResult().getContext().watchList().remove(frameExcFormContent.getSelectedWp());
        framePoints.guiContentBuildClear();
        framePoints.refreshWatch(window);
        framePoints.getCommonFrame().pack();
    }
}
