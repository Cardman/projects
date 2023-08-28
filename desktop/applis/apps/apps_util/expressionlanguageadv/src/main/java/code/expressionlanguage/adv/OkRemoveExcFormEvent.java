package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class OkRemoveExcFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameExcFormContent frameExcFormContent;
    private final FramePoints framePoints;

    public OkRemoveExcFormEvent(AbsDebuggerGui _w, FrameExcFormContent _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        window.getCurrentResult().toggleExcPoint(frameExcFormContent.getClName().getText(), frameExcFormContent.getExact().isSelected());
        framePoints.guiContentBuildClear();
        framePoints.refreshExc(window);
        framePoints.getCommonFrame().pack();
    }

}
