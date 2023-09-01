package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class OkRemoveBpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameBpFormContent frameExcFormContent;
    private final FramePoints framePoints;

    public OkRemoveBpFormEvent(AbsDebuggerGui _w, FrameBpFormContent _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        window.getCurrentResult().bpList().remove(frameExcFormContent.getSelectedBp());
        framePoints.guiContentBuildClear();
        framePoints.refreshWatch(window);
        framePoints.getCommonFrame().pack();
    }
}
