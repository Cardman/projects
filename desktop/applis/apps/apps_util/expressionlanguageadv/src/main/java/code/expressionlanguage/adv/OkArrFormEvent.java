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
        arr_.getValue().setIntGet(frameArrFormContent.getIntGet().isSelected());
        arr_.getValue().setIntSet(frameArrFormContent.getIntSet().isSelected());
        arr_.getValue().setIntCompoundGet(frameArrFormContent.getIntCompoundGet().isSelected());
        arr_.getValue().setIntCompoundSet(frameArrFormContent.getIntCompoundSet().isSelected());
        arr_.getValue().setIntCompoundSetErr(frameArrFormContent.getIntCompoundSetErr().isSelected());
        arr_.getValue().setRangeGet(frameArrFormContent.getRangeGet().isSelected());
        arr_.getValue().setRangeSet(frameArrFormContent.getRangeSet().isSelected());
        arr_.getValue().setRangeCompoundGet(frameArrFormContent.getRangeCompoundGet().isSelected());
        arr_.getValue().setRangeCompoundSet(frameArrFormContent.getRangeCompoundSet().isSelected());
        arr_.getValue().setIntGetSet(frameArrFormContent.getIntGetSet().isSelected());
        arr_.getValue().setInitArray(frameArrFormContent.getInitArray().isSelected());
        update(arr_, arr_.getValue().getResultLength(), window, frameArrFormContent.getGuiLengthStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultIntGet(), window, frameArrFormContent.getGuiIntGetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultIntSet(), window, frameArrFormContent.getGuiIntSetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultIntCompoundGet(), window, frameArrFormContent.getGuiIntCompoundGetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultIntCompoundSet(), window, frameArrFormContent.getGuiIntCompoundSetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultIntCompoundSetErr(), window, frameArrFormContent.getGuiIntCompoundSetErrStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultRangeGet(), window, frameArrFormContent.getGuiRangeGetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultRangeSet(), window, frameArrFormContent.getGuiRangeSetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultRangeCompoundGet(), window, frameArrFormContent.getGuiRangeCompoundGetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultRangeCompoundSet(), window, frameArrFormContent.getGuiRangeCompoundSetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultIntGetSet(), window, frameArrFormContent.getGuiIntGetSetStackForm(), currentResult);
        update(arr_, arr_.getValue().getResultInitArray(), window, frameArrFormContent.getGuiInitArrayStackForm(), currentResult);
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
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_INT_GET)) {
            _condition.getOthers().add(_mp.getValue().getResultIntGet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_INT_SET)) {
            _condition.getOthers().add(_mp.getValue().getResultIntSet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_INT_COMPOUND_GET)) {
            _condition.getOthers().add(_mp.getValue().getResultIntCompoundGet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_INT_COMPOUND_SET)) {
            _condition.getOthers().add(_mp.getValue().getResultIntCompoundSet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_INT_COMPOUND_SET_ERR)) {
            _condition.getOthers().add(_mp.getValue().getResultIntCompoundSetErr());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_RANGE_GET)) {
            _condition.getOthers().add(_mp.getValue().getResultRangeGet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_RANGE_SET)) {
            _condition.getOthers().add(_mp.getValue().getResultRangeSet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_RANGE_COMPOUND_GET)) {
            _condition.getOthers().add(_mp.getValue().getResultRangeCompoundGet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_RANGE_COMPOUND_SET)) {
            _condition.getOthers().add(_mp.getValue().getResultRangeCompoundSet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_INT_GET_SET)) {
            _condition.getOthers().add(_mp.getValue().getResultIntGetSet());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(ArrPoint.BPC_INIT)) {
            _condition.getOthers().add(_mp.getValue().getResultInitArray());
        }
    }

}
