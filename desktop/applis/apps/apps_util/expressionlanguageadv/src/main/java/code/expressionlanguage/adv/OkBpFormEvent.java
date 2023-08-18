package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.ResultContextLambda;
import code.gui.events.AbsActionListener;

public final class OkBpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public OkBpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getFrameBpForm().getCommonFrame().setVisible(false);
        window.getFrameBpForm().getSelectedBp().getValue().setEnabled(window.getFrameBpForm().getEnabledBp().isSelected());
        window.getFrameBpForm().getSelectedBp().getValue().setInstanceType(window.getFrameBpForm().getInstanceType().isSelected());
        window.getFrameBpForm().getSelectedBp().getValue().setStaticType(window.getFrameBpForm().getStaticType().isSelected());
        if (window.getFrameBpForm().getSelectedBp().getValue().isEnabledChgtType()) {
            update(window.getFrameBpForm().getSelectedBp(),window.getFrameBpForm().getSelectedBp().getValue().getResultInstance(),window,window.getFrameBpForm().getGuiInsStackForm(),MethodAccessKind.INSTANCE);
            update(window.getFrameBpForm().getSelectedBp(),window.getFrameBpForm().getSelectedBp().getValue().getResultStatic(),window,window.getFrameBpForm().getGuiStaStackForm(),MethodAccessKind.STATIC);
        } else {
            update(window.getFrameBpForm().getSelectedBp(),window.getFrameBpForm().getSelectedBp().getValue().getResultStd(),window,window.getFrameBpForm().getGuiStdStackForm(),null);
        }
        window.getFrameBpForm().setSelectedBp(null);
    }
    private static void update(BreakPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, MethodAccessKind _flag) {
        String type_ = _window.getCurrentResult().getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_form.getConditional().getText(), ExecFileBlock.name(_mp.getFile()), _mp.getOffset(),  _window.getCurrentResult(), type_, _window.getResultContextNext().generateAdv(_window.getStopDbg()), _flag);
        OkMpFormEvent.update(_condition,_form,res_);
    }
}
