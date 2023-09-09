package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveStdFormEvent implements AbsActionListener {
    private final FrameStdMpForm frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveStdFormEvent(FrameStdMpForm _f, FramePoints _p, ResultContext _res) {
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.getContext().stdList().remove(frameExcFormContent.getSelectedMp());
        frameExcFormContent.setSelectedMp(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshStdMethod(currentResult);
        framePoints.getCommonFrame().pack();
    }

}
