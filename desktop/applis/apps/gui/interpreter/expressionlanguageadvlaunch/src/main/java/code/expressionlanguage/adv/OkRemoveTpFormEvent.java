package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveTpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameTpFormContent frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveTpFormEvent(AbsDebuggerGui _w, FrameTpFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.tpList().remove(frameExcFormContent.getSelectedTp());
        frameExcFormContent.setSelectedTp(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshTp(currentResult);
        framePoints.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult,window.selectedTab());
    }
}
