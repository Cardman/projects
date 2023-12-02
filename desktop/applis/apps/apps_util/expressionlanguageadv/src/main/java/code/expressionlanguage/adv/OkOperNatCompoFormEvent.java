package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkOperNatCompoFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameOperNatCompoFormContent frameOperNatFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkOperNatCompoFormEvent(AbsDebuggerGui _w, FrameOperNatCompoFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameOperNatFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        CompoOperNatPointBlockPair exc_ = frameOperNatFormContent.getSelectedOperNat();
        if (exc_ == null) {
            AbsOperNatPointBlockPair added_ = currentResult.resolve(frameOperNatFormContent.getSymbol().getText(), frameOperNatFormContent.getFirst().getText(), frameOperNatFormContent.getSecond().getText());
            if (!(added_ instanceof CompoOperNatPointBlockPair)) {
                return;
            }
            currentResult.getContext().operCompoNatList().add(added_);
            exc_ = (CompoOperNatPointBlockPair) added_;
        }
        exc_.getValue().setEnabled(frameOperNatFormContent.getEnabledOperNat().isSelected());
        exc_.getValue().setSimple(frameOperNatFormContent.getSimple().isSelected());
        exc_.getValue().setCompound(frameOperNatFormContent.getCompound().isSelected());
        update(exc_, exc_.getValue().getResultSimple(), window, frameOperNatFormContent.getGuiSimpleStackForm(), currentResult);
        update(exc_, exc_.getValue().getResultCompound(), window, frameOperNatFormContent.getGuiCompoundStackForm(), currentResult);
        frameOperNatFormContent.setSelectedOperNat(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshOperNatCompo(currentResult);
        framePoints.getCommonFrame().pack();
    }
    private static void update(CompoOperNatPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _curr) {
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
