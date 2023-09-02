package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveWatchFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameWpFormContent frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveWatchFormEvent(AbsDebuggerGui _w, FrameWpFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.getContext().watchList().remove(frameExcFormContent.getSelectedWp());
        frameExcFormContent.setSelectedWp(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshWatch(window, currentResult);
        framePoints.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult,window.selectedTab());
    }
}
