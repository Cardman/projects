package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ArrPoint;
import code.expressionlanguage.exec.dbg.ArrPointBlockPair;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkArrFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameArrFormContent frameArrFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkArrFormEvent(AbsDebuggerGui _w, FrameArrFormContent _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameArrFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        ArrPointBlockPair arr_ = frameArrFormContent.getSelectedArr();
        if (arr_ == null) {
            ArrPointBlockPair added_ = currentResult.getContext().buildArr(frameArrFormContent.getExact().isSelected(), frameArrFormContent.getClName().getText());
            if (added_ == null) {
                return;
            }
            currentResult.getContext().arrList().add(added_);
            arr_ = added_;
        }
        arr_.getValue().setEnabled(frameArrFormContent.getEnabledExc().isSelected());
        arr_.getValue().setLength(frameArrFormContent.getLength().isSelected());
        update(arr_, arr_.getValue().getResultLength(), window, frameArrFormContent.getGuiLengthStackForm(), currentResult);
        frameArrFormContent.setSelectedArr(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshArr(currentResult);
        framePoints.getCommonFrame().pack();
    }
    private static void update(ArrPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _curr) {
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(),_curr, _window.getResultContextNext().generateAdv(_curr.getContext().getInterrupt()));
        OkMpFormEvent.update(_condition, _form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_LENGTH)) {
            _condition.getOthers().add(_mp.getValue().getResultLength());
        }
//        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ExcPoint.BPC_CAUGHT)) {
//            _condition.getOthers().add(_mp.getValue().getResultCaught());
//        }
//        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ExcPoint.BPC_PROPAGATED)) {
//            _condition.getOthers().add(_mp.getValue().getResultPropagated());
//        }
    }

}
