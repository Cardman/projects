package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.options.ResultContextLambda;
import code.gui.events.AbsActionListener;

public final class OkExcFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameExcFormContent frameExcFormContent;
    private final FramePoints framePoints;

    public OkExcFormEvent(AbsDebuggerGui _w, FrameExcFormContent _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        ExcPointBlockPair exc_ = frameExcFormContent.getSelectedExc();
        if (exc_ == null) {
            BreakPointBlockList ls_ = window.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock();
            window.getCurrentResult().toggleExcPoint(frameExcFormContent.getClName().getText(), frameExcFormContent.getExact().isSelected());
            ExcPointBlockPair added_ = ls_.getPairExc(frameExcFormContent.getClName().getText(), frameExcFormContent.getExact().isSelected());
            if (added_ == null) {
                return;
            }
            exc_ = added_;
        }
        exc_.getValue().setEnabled(frameExcFormContent.getEnabledExc().isSelected());
        exc_.getValue().setThrown(frameExcFormContent.getThrown().isSelected());
        exc_.getValue().setCaught(frameExcFormContent.getCaught().isSelected());
        exc_.getValue().setPropagated(frameExcFormContent.getPropagated().isSelected());
        update(exc_, exc_.getValue().getResultThrown(), window, frameExcFormContent.getGuiThrownStackForm());
        update(exc_, exc_.getValue().getResultCaught(), window, frameExcFormContent.getGuiCaughtStackForm());
        update(exc_, exc_.getValue().getResultPropagated(), window, frameExcFormContent.getGuiPropagatedStackForm());
        frameExcFormContent.setSelectedExc(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshExc(window);
        framePoints.getCommonFrame().pack();
    }
    private static void update(ExcPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form) {
        String type_ = _window.getCurrentResult().getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_form.getConditional().getText(), _mp.getEp().getClName(), _mp.getEp().isExact(), _window.getCurrentResult(), type_, _window.getResultContextNext().generateAdv(_window.getStopDbg()));
        OkMpFormEvent.update(_condition, _form, res_);
    }

}
