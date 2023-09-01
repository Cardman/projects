package code.expressionlanguage.adv;

import code.expressionlanguage.common.DisplayedStrings;
import code.gui.events.AbsActionListener;

public final class OkRemoveMpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameMpForm frameExcFormContent;
    private final FramePoints framePoints;

    public OkRemoveMpFormEvent(AbsDebuggerGui _w, FrameMpForm _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        DisplayedStrings disp_ = window.getCurrentResult().getPageEl().getDisplayedStrings();
        window.getCurrentResult().getContext().metList().remove(window.getCurrentResult().method(disp_,frameExcFormContent.getSelectedMp().getMp().getId()));
        framePoints.guiContentBuildClear();
        framePoints.refreshWatch(window);
        framePoints.getCommonFrame().pack();
    }
}
