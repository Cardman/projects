package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class OkRemoveStdFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameStdMpForm frameExcFormContent;
    private final FramePoints framePoints;

    public OkRemoveStdFormEvent(AbsDebuggerGui _w, FrameStdMpForm _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        window.getCurrentResult().toggleBreakPoint(frameExcFormContent.getSelectedMp().getSm().getType(), frameExcFormContent.getSelectedMp().getSm().getId());
        framePoints.guiContentBuildClear();
        framePoints.refreshStdMethod(window);
        framePoints.getCommonFrame().pack();
    }

}
