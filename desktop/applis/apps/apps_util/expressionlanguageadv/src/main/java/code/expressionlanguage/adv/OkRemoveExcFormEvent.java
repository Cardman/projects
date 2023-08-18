package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.gui.events.AbsActionListener;

public final class OkRemoveExcFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameExcFormContent frameExcFormContent;
    private final FramePoints framePoints;

    public OkRemoveExcFormEvent(AbsDebuggerGui _w, FrameExcFormContent _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        BreakPointBlockList ls_ = window.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock();
        ls_.toggleExcPoint(frameExcFormContent.getClName().getText(),window.getCurrentResult(),frameExcFormContent.getExact().isSelected());
        framePoints.guiContentBuildClear();
        framePoints.refreshExc(window);
        framePoints.getCommonFrame().pack();
    }

}
