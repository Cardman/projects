package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveArrFormEvent implements AbsActionListener {
    private final FrameArrFormContent frameArrFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveArrFormEvent(FrameArrFormContent _f, FramePoints _p, ResultContext _res) {
        this.frameArrFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.getContext().arrList().remove(frameArrFormContent.getSelectedArr());
        frameArrFormContent.setSelectedArr(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshArr(currentResult);
        framePoints.getCommonFrame().pack();
    }

}
