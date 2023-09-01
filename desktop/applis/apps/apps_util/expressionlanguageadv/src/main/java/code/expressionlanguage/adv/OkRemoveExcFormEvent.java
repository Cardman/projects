package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveExcFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameExcFormContent frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveExcFormEvent(AbsDebuggerGui _w, FrameExcFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.getContext().excList().remove(frameExcFormContent.getSelectedExc());
        frameExcFormContent.setSelectedExc(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshExc(window, currentResult);
        framePoints.getCommonFrame().pack();
    }

}
