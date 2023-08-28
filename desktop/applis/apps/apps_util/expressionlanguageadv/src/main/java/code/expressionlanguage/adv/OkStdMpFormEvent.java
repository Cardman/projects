package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.gui.events.AbsActionListener;

public final class OkStdMpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameStdMpForm frameExcFormContent;
    private final FramePoints framePoints;

    public OkStdMpFormEvent(AbsDebuggerGui _w, FrameStdMpForm _f, FramePoints _p) {
        this.window = _w;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        act(frameExcFormContent,null,null, window, framePoints);
    }

    static void act(FrameStdMpForm _frCont, StandardType _type, StandardNamedFunction _fct, AbsDebuggerGui _w, FramePoints _fp) {
        StdMethodPointBlockPair exc_ = _frCont.getSelectedMp();
        if (exc_ == null) {
            BreakPointBlockList ls_ = _w.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock();
            if (_type != null && _fct != null) {
                _w.getCurrentResult().toggleBreakPoint(_type,_fct);
            }
            StdMethodPointBlockPair added_ = ls_.getPair(_fct);
            if (added_ == null) {
                return;
            }
            exc_ = added_;
        }
        exc_.getValue().setEnabled(_frCont.getEnabledMp().isSelected());
        exc_.getValue().setEntry(_frCont.getEnterFunction().isSelected());
        exc_.getValue().setExit(_frCont.getExitFunction().isSelected());
        update(exc_, exc_.getValue().getResultEntry(), _w, _frCont.getGuiEnterStackForm());
        update(exc_, exc_.getValue().getResultExit(), _w, _frCont.getGuiExitStackForm());
        _frCont.setSelectedMp(null);
        _fp.guiContentBuildClear();
        _fp.refreshStdMethod(_w);
        _fp.getCommonFrame().pack();
    }

    private static void update(StdMethodPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form) {
        String type_ = _window.getCurrentResult().getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_form.getConditional().getText(), _mp, _window.getCurrentResult(), type_, _window.getResultContextNext().generateAdv(_window.getStopDbg()));
        OkMpFormEvent.update(_condition, _form, res_);
    }

}
