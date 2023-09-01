package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveMpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameMpForm frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveMpFormEvent(AbsDebuggerGui _w, FrameMpForm _f, FramePoints _p, ResultContext _r) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _r;
    }

    @Override
    public void action() {
        currentResult.getContext().metList().remove(frameExcFormContent.getSelectedMp());
        frameExcFormContent.setSelectedMp(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshWatch(window, currentResult);
        framePoints.getCommonFrame().pack();
    }
}
