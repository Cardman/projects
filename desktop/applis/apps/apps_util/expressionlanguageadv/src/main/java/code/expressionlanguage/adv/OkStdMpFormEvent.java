package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.MethodPoint;
import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.gui.events.AbsActionListener;

public final class OkStdMpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameStdMpForm frameExcFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkStdMpFormEvent(AbsDebuggerGui _w, FrameStdMpForm _f, FramePoints _p, ResultContext _res) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        act(frameExcFormContent,null,null, window, framePoints, currentResult);
    }

    static void act(FrameStdMpForm _frCont, StandardType _type, StandardNamedFunction _fct, AbsDebuggerGui _w, FramePoints _fp, ResultContext _curr) {
        StdMethodPointBlockPair exc_ = _frCont.getSelectedMp();
        if (exc_ == null) {
            StdMethodPointBlockPair added_;
            if (_type != null && _fct != null) {
                added_ = _curr.getContext().std(_type, _fct);
                _curr.getContext().stdList().add(added_);
            } else {
                added_ = null;
            }
            if (added_ == null) {
                return;
            }
            exc_ = added_;
        }
        exc_.getValue().setEnabled(_frCont.getEnabledMp().isSelected());
        exc_.getValue().setEntry(_frCont.getEnterFunction().isSelected());
        exc_.getValue().setExit(_frCont.getExitFunction().isSelected());
        update(exc_, exc_.getValue().getResultEntry(), _w, _frCont.getGuiEnterStackForm(), _curr);
        update(exc_, exc_.getValue().getResultExit(), _w, _frCont.getGuiExitStackForm(), _curr);
        _frCont.setSelectedMp(null);
        _fp.guiContentBuildClear();
        _fp.refreshStdMethod(_curr);
        _fp.getCommonFrame().pack();
    }

    private static void update(StdMethodPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _cur) {
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(), _form.getWatches().getText(), _cur, _window.getResultContextNext().generateAdv(_cur.getContext().getInterrupt()));
        OkMpFormEvent.update(_condition, _form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(MethodPoint.BPC_ENTRY)) {
            _condition.getOthers().add(_mp.getValue().getResultEntry());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(MethodPoint.BPC_EXIT)) {
            _condition.getOthers().add(_mp.getValue().getResultExit());
        }
    }

}
