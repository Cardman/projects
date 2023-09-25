package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRenderFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameRenderFormContent frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRenderFormEvent(AbsDebuggerGui _w, FrameRenderFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        RenderPointPair exc_ = frameExcFormContent.getSelectedExc();
        if (exc_ == null) {
            ExcPointBlockPair added_ = currentResult.getContext().build(frameExcFormContent.getExact().isSelected(), frameExcFormContent.getClName().getText());
            if (added_ == null) {
                return;
            }
            ExcPointBlockKey ep_ = added_.getEp();
            RenderPointPair r_ = new RenderPointPair(ep_.isExact(), ep_.getClName(), window.getCaller(), false);
            window.getRenderList().add(r_);
            exc_ = r_;
        }
        exc_.getExcPointBlockPair().getValue().setEnabled(frameExcFormContent.getEnabledExc().isSelected());
        exc_.setGlobalEnabled(frameExcFormContent.getEnabledExcGlobal().isSelected());
        exc_.analyze(frameExcFormContent.getRenderText().getText(),currentResult, window.getResultContextNext().generateAdv(currentResult.getContext().getInterrupt()));
//        exc_.getValue().setThrown(frameExcFormContent.getThrown().isSelected());
//        exc_.getValue().setCaught(frameExcFormContent.getCaught().isSelected());
//        exc_.getValue().setPropagated(frameExcFormContent.getPropagated().isSelected());
//        update(exc_, exc_.getValue().getResultThrown(), window, frameExcFormContent.getGuiThrownStackForm(), currentResult);
//        update(exc_, exc_.getValue().getResultCaught(), window, frameExcFormContent.getGuiCaughtStackForm(), currentResult);
//        update(exc_, exc_.getValue().getResultPropagated(), window, frameExcFormContent.getGuiPropagatedStackForm(), currentResult);
        frameExcFormContent.setSelectedExc(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshRender(window.getRenderList());
        framePoints.getCommonFrame().pack();
    }

}
