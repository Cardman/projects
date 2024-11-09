package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.*;
import code.gui.*;
import code.gui.events.*;

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
            ExcPointBlockPair added_ = currentResult.getContext().build(frameExcFormContent.getExactForm().code(), frameExcFormContent.getClName().getText());
            if (added_ == null) {
                return;
            }
            ExcPointBlockKey ep_ = added_.getEp();
            RenderPointPair r_ = new RenderPointPair(ep_.isExact(), ep_.getClName(), window.getCaller());
            window.getRenderList().add(r_);
            exc_ = r_;
        }
        exc_.getExcPointBlockPair().getValue().setEnabled(frameExcFormContent.getEnabledExc().isSelected());
        exc_.setEnableExpand(frameExcFormContent.getEnabledExpand().isSelected());
        exc_.setEnableBothExpand(frameExcFormContent.getEnabledExpandRender().isSelected());
        exc_.setEnableBothRender(frameExcFormContent.getEnabledRenderExpand().isSelected());
        exc_.setExpandFirst(frameExcFormContent.getExpandFirst().isSelected());
        exc_.setExpandRenderFirst(frameExcFormContent.getExpandRenderChoice().isSelected());
        exc_.setGlobalEnabled(frameExcFormContent.getEnabledExcGlobal().isSelected());
        exc_.setPref(frameExcFormContent.getPref().getValue());
        exc_.prefsMap(GuiBaseUtil.retrieve(frameExcFormContent.getPrefs()));
        exc_.analyze(frameExcFormContent.getRenderText().getText(),frameExcFormContent.getExpandText().getText(),frameExcFormContent.getRenderExpandText().getText(),frameExcFormContent.getExpandRenderText().getText(), currentResult, window.getResultContextNext().generateAdv(currentResult.getContext().getInterrupt()));
//        exc_.getValue().setThrown(frameExcFormContent.getThrown().isSelected());
//        exc_.getValue().setCaught(frameExcFormContent.getCaught().isSelected());
//        exc_.getValue().setPropagated(frameExcFormContent.getPropagated().isSelected());
//        update(exc_, exc_.getValue().getResultThrown(), window, frameExcFormContent.getGuiThrownStackForm(), currentResult);
//        update(exc_, exc_.getValue().getResultCaught(), window, frameExcFormContent.getGuiCaughtStackForm(), currentResult);
//        update(exc_, exc_.getValue().getResultPropagated(), window, frameExcFormContent.getGuiPropagatedStackForm(), currentResult);
        frameExcFormContent.setSelectedExc(null);
        framePoints.refreshRender(window.getRenderList());
        framePoints.getCommonFrame().pack();
    }

}
