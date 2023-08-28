package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.ExecFileBlockTraceIndex;
import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.expressionlanguage.options.ResultContextLambda;
import code.gui.events.AbsActionListener;

public final class OkMpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public OkMpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getFrameMpForm().getCommonFrame().setVisible(false);
        window.getFrameMpForm().getSelectedMp().getValue().setEnabled(window.getFrameMpForm().getEnabledMp().isSelected());
        window.getFrameMpForm().getSelectedMp().getValue().setEntry(window.getFrameMpForm().getEnterFunction().isSelected());
        window.getFrameMpForm().getSelectedMp().getValue().setExit(window.getFrameMpForm().getExitFunction().isSelected());
        update(window.getFrameMpForm().getSelectedMp(),window.getFrameMpForm().getSelectedMp().getValue().getResultEntry(), window, window.getFrameMpForm().getGuiEnterStackForm());
        update(window.getFrameMpForm().getSelectedMp(),window.getFrameMpForm().getSelectedMp().getValue().getResultExit(), window, window.getFrameMpForm().getGuiExitStackForm());
        window.getFrameMpForm().setSelectedMp(null);
    }
    private static void update(MethodPointBlockPair _mp,BreakPointCondition _condition,AbsDebuggerGui _window, GuiStackForm _form) {
        String type_ = _window.getCurrentResult().getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_form.getConditional().getText(), _mp, _window.getCurrentResult(), type_, _window.getResultContextNext().generateAdv(_window.getStopDbg()));
        update(_condition, _form, res_);
    }

    static void update(BreakPointCondition _condition, GuiStackForm _form, ResultContextLambda _res) {
        _condition.result(_res, _form.getConditional().getText());
        _condition.getCountModulo().set(_form.getCount().getValue());
        _condition.getCount().set(_form.getCountSub().getValue());
        ExecFileBlockTraceIndex.setAll(_condition.getExclude(),_form.getMustNotBe());
        ExecFileBlockTraceIndex.setAll(_condition.getInclude(),_form.getMustBe());
        _condition.getEnabled().set(_form.getEnabledSub().isSelected());
        _condition.getHit().set(_form.getHit().isSelected());
        _condition.getDisableWhenHit().set(_form.getDisabledWhenHit().isSelected());

    }
}
