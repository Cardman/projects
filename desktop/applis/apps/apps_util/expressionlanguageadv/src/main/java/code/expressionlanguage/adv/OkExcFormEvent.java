package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.ExcPoint;
import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.gui.events.AbsActionListener;

public final class OkExcFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameExcFormContent frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkExcFormEvent(AbsDebuggerGui _w, FrameExcFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        ExcPointBlockPair exc_ = frameExcFormContent.getSelectedExc();
        if (exc_ == null) {
            ExcPointBlockPair added_ = currentResult.getContext().build(frameExcFormContent.getExact().isSelected(), frameExcFormContent.getClName().getText());
            if (added_ == null) {
                return;
            }
            currentResult.getContext().excList().add(added_);
            exc_ = added_;
        }
        exc_.getValue().setEnabled(frameExcFormContent.getEnabledExc().isSelected());
        exc_.getValue().setThrown(frameExcFormContent.getThrown().isSelected());
        exc_.getValue().setCaught(frameExcFormContent.getCaught().isSelected());
        exc_.getValue().setPropagated(frameExcFormContent.getPropagated().isSelected());
        update(exc_, exc_.getValue().getResultThrown(), window, frameExcFormContent.getGuiThrownStackForm(), currentResult);
        update(exc_, exc_.getValue().getResultCaught(), window, frameExcFormContent.getGuiCaughtStackForm(), currentResult);
        update(exc_, exc_.getValue().getResultPropagated(), window, frameExcFormContent.getGuiPropagatedStackForm(), currentResult);
        frameExcFormContent.setSelectedExc(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshExc(window, currentResult);
        framePoints.getCommonFrame().pack();
    }
    private static void update(ExcPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _curr) {
        String type_ = _curr.getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_form.getConditional().getText(), _mp.getEp().getClName(), _mp.getEp().isExact(), _curr, type_, _window.getResultContextNext().generateAdv(_curr.getContext().getInterrupt()));
        OkMpFormEvent.update(_condition, _form, res_);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ExcPoint.BPC_THROWN)) {
            _condition.getOthers().add(_mp.getValue().getResultThrown());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ExcPoint.BPC_CAUGHT)) {
            _condition.getOthers().add(_mp.getValue().getResultCaught());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ExcPoint.BPC_PROPAGATED)) {
            _condition.getOthers().add(_mp.getValue().getResultPropagated());
        }
    }

}
