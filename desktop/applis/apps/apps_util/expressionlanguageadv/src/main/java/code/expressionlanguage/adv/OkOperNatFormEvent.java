package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.OperNatPoint;
import code.expressionlanguage.exec.dbg.OperNatPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkOperNatFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameOperNatFormContent frameOperNatFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkOperNatFormEvent(AbsDebuggerGui _w, FrameOperNatFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameOperNatFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        OperNatPointBlockPair exc_ = frameOperNatFormContent.getSelectedOperNat();
        if (exc_ == null) {
            OperNatPointBlockPair added_ = currentResult.resolve(frameOperNatFormContent.getSymbol().getText(), frameOperNatFormContent.getFirst().getText(), frameOperNatFormContent.getSecond().getText());
            if (added_ == null) {
                return;
            }
            currentResult.getContext().operNatList().add(added_);
            exc_ = added_;
        }
        exc_.getValue().setEnabled(frameOperNatFormContent.getEnabledOperNat().isSelected());
        exc_.getValue().setSimple(frameOperNatFormContent.getSimple().isSelected());
        update(exc_, exc_.getValue().getResultSimple(), window, frameOperNatFormContent.getGuiSimpleStackForm(), currentResult);
        frameOperNatFormContent.setSelectedOperNat(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshOperNat(currentResult);
        framePoints.getCommonFrame().pack();
    }
    private static void update(OperNatPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _curr) {
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(), _form.getWatches().getText(), _curr, _window.getResultContextNext().generateAdv(_curr.getContext().getInterrupt()));
        OkMpFormEvent.update(_condition, _form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(OperNatPoint.BPC_SIMPLE)) {
            _condition.getOthers().add(_mp.getValue().getResultSimple());
        }
    }

}
