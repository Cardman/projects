package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.options.ResultContext;
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
            ExcPointBlockPair added_ = window.getCurrentResult().getContext().build(frameExcFormContent.getExact().isSelected(), frameExcFormContent.getClName().getText());
            if (added_ == null) {
                return;
            }
            window.getCurrentResult().getContext().excList().add(added_);
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
        ResultContext curr_ = _window.getCurrentResult();
        String type_ = curr_.getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_form.getConditional().getText(), _mp.getEp().getClName(), _mp.getEp().isExact(), curr_, type_, _window.getResultContextNext().generateAdv(curr_.getContext().getInterrupt()));
        OkMpFormEvent.update(_condition, _form, res_);
    }

}
