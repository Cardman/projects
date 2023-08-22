package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContextLambda;
import code.gui.events.AbsActionListener;

public final class OkWpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public OkWpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getFrameWpForm().getCommonFrame().setVisible(false);
        window.getFrameWpForm().getSelectedWp().getValue().setEnabled(window.getFrameWpForm().getEnabledWp().isSelected());
        window.getFrameWpForm().getSelectedWp().getValue().setRead(window.getFrameWpForm().getRead().isSelected());
        window.getFrameWpForm().getSelectedWp().getValue().setWrite(window.getFrameWpForm().getWrite().isSelected());
        window.getFrameWpForm().getSelectedWp().getValue().setCompoundRead(window.getFrameWpForm().getCompoundRead().isSelected());
        window.getFrameWpForm().getSelectedWp().getValue().setCompoundWrite(window.getFrameWpForm().getCompoundWrite().isSelected());
        window.getFrameWpForm().getSelectedWp().getValue().setCompoundWriteErr(window.getFrameWpForm().getCompoundWriteErr().isSelected());
        update(window.getFrameWpForm().getSelectedWp(),window.getFrameWpForm().getSelectedWp().getValue().getResultRead(),window,window.getFrameWpForm().getGuiReadStackForm(),false);
        update(window.getFrameWpForm().getSelectedWp(),window.getFrameWpForm().getSelectedWp().getValue().getResultWrite(),window,window.getFrameWpForm().getGuiWriteStackForm(),true);
        update(window.getFrameWpForm().getSelectedWp(),window.getFrameWpForm().getSelectedWp().getValue().getResultCompoundRead(),window,window.getFrameWpForm().getGuiCompoundReadStackForm(),false);
        update(window.getFrameWpForm().getSelectedWp(),window.getFrameWpForm().getSelectedWp().getValue().getResultCompoundWrite(),window,window.getFrameWpForm().getGuiCompoundWriteStackForm(),true);
        update(window.getFrameWpForm().getSelectedWp(),window.getFrameWpForm().getSelectedWp().getValue().getResultCompoundWriteErr(),window,window.getFrameWpForm().getGuiCompoundWriteErrStackForm(),true);
        window.getFrameWpForm().setSelectedWp(null);
    }
    private static void update(WatchPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, boolean _setting) {
        String type_ = _window.getCurrentResult().getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeField(_form.getConditional().getText(), _mp, _window.getCurrentResult(), type_, _window.getResultContextNext().generateAdv(_window.getStopDbg()), _setting);
        OkMpFormEvent.update(_condition,_form,res_);
    }
}
