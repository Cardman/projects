package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveExcFormEvent implements AbsActionListener {
    private final FrameExcFormContent frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveExcFormEvent(FrameExcFormContent _f, FramePoints _p, ResultContext _res) {
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.getContext().excList().remove(frameExcFormContent.getSelectedExc());
        frameExcFormContent.setSelectedExc(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshExc(currentResult);
        framePoints.getCommonFrame().pack();
    }

}
