package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveBpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameBpFormContent frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveBpFormEvent(AbsDebuggerGui _w, FrameBpFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.bpList().remove(frameExcFormContent.getSelectedBp());
        frameExcFormContent.setSelectedBp(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshBp(currentResult);
        framePoints.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult,window.selectedTab());
    }
}
