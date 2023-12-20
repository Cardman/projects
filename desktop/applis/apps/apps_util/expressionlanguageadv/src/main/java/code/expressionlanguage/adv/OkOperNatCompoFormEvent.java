package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkOperNatCompoFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameOperNatCompoFormContent frameOperNatCompoFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkOperNatCompoFormEvent(AbsDebuggerGui _w, FrameOperNatCompoFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameOperNatCompoFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        OperNatPointBlockPair exc_ = frameOperNatCompoFormContent.getSelectedOperNat();
        if (exc_ == null) {
            OperNatPointBlockPair added_ = currentResult.resolve(frameOperNatCompoFormContent.getSymbol().getText(), frameOperNatCompoFormContent.getFirst().getText(), frameOperNatCompoFormContent.getSecond().getText());
            if (added_ == null) {
                return;
            }
            currentResult.getContext().operNatList().add(added_);
            exc_ = added_;
        }
        exc_.getValue().setEnabled(frameOperNatCompoFormContent.getEnabledOperNat().isSelected());
        exc_.getValue().setSimple(frameOperNatCompoFormContent.getSimple().isSelected());
        exc_.getValue().setCompound(frameOperNatCompoFormContent.getCompound().isSelected());
        update(exc_, exc_.getValue().getResultSimple(), window, frameOperNatCompoFormContent.getGuiSimpleStackForm(), currentResult);
        update(exc_, exc_.getValue().getResultCompound(), window, frameOperNatCompoFormContent.getGuiCompoundStackForm(), currentResult);
        frameOperNatCompoFormContent.setSelectedOperNat(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshOperNatCompo(currentResult);
        framePoints.getCommonFrame().pack();
    }
    private static void update(OperNatPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _curr) {
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(), _form.getWatches().getText(), _curr, _window.getResultContextNext().generateAdv(_curr.getContext().getInterrupt()));
        OkMpFormEvent.update(_condition, _form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(OperNatPoint.BPC_SIMPLE)) {
            _condition.getOthers().add(_mp.getValue().getResultSimple());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(OperNatPoint.BPC_COMPOUND)) {
            _condition.getOthers().add(_mp.getValue().getResultCompound());
        }
    }

}
