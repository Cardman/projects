package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.ParPoint;
import code.expressionlanguage.exec.dbg.ParPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkParFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameParFormContent frameParFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkParFormEvent(AbsDebuggerGui _w, FrameParFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameParFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        ParPointBlockPair exc_ = frameParFormContent.getSelectedPar();
        if (exc_ == null) {
            ParPointBlockPair added_ = currentResult.tryBuild(frameParFormContent.getClName().getText(), frameParFormContent.getExact().isSelected());
            if (added_ == null) {
                return;
            }
            currentResult.getContext().parList().add(added_);
            exc_ = added_;
        }
        exc_.getValue().setEnabled(frameParFormContent.getEnabledPar().isSelected());
        exc_.getValue().setGet(frameParFormContent.getGet().isSelected());
        update(exc_, exc_.getValue().getResultGet(), window, frameParFormContent.getGuiGetStackForm(), currentResult);
        frameParFormContent.setSelectedPar(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshParent(currentResult);
        framePoints.getCommonFrame().pack();
    }
    private static void update(ParPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _curr) {
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(), _form.getWatches().getText(), _curr, _window.getResultContextNext().generateAdv(_curr.getContext().getInterrupt()));
        OkMpFormEvent.update(_condition, _form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ParPoint.BPC_GET)) {
            _condition.getOthers().add(_mp.getValue().getResultGet());
        }
    }

}
