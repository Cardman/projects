package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.gui.events.AbsActionListener;

public final class OkRemoveStdFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameStdMpForm frameExcFormContent;
    private final FramePoints framePoints;

    public OkRemoveStdFormEvent(AbsDebuggerGui _w, FrameStdMpForm _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        BreakPointBlockList ls_ = window.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock();
        ls_.toggleBreakPoint(frameExcFormContent.getSelectedMp().getType(),frameExcFormContent.getSelectedMp().getId(),window.getCurrentResult());
        framePoints.guiContentBuildClear();
        framePoints.refreshStdMethod(window);
        framePoints.getCommonFrame().pack();
    }

}
