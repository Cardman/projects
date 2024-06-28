package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveParFormEvent implements AbsActionListener {
    private final FrameParFormContent frameParFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveParFormEvent(FrameParFormContent _f, FramePoints _p, ResultContext _res) {
        this.frameParFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.getContext().parList().remove(frameParFormContent.getSelectedPar());
        frameParFormContent.setSelectedPar(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshParent(currentResult);
        framePoints.getCommonFrame().pack();
    }

}
