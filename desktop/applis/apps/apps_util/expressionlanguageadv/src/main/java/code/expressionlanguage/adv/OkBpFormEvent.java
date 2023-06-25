package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.gui.events.AbsActionListener;

public final class OkBpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public OkBpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getFrameBpForm().getCommonFrame().setVisible(false);
        window.getFrameBpForm().getSelectedPb().getValue().setEnabled(window.getFrameBpForm().getEnabledBp().isSelected());
        window.getFrameBpForm().getSelectedPb().getValue().setInstanceType(window.getFrameBpForm().getInstanceType().isSelected());
        window.getFrameBpForm().getSelectedPb().getValue().setStaticType(window.getFrameBpForm().getStaticType().isSelected());
        if (window.getFrameBpForm().getSelectedPb().getValue().isEnabledChgtType()) {
            BreakPointBlockList.breakPointCtxInstance(window.getFrameBpForm().getSelectedPb(),window.getCurrentResult(), window.getResultContextNext().generate(window.getStopDbg()),window.getFrameBpForm().getConditionalInstance().getText());
            BreakPointBlockList.breakPointCtxStatic(window.getFrameBpForm().getSelectedPb(),window.getCurrentResult(), window.getResultContextNext().generate(window.getStopDbg()),window.getFrameBpForm().getConditionalStatic().getText());
            BreakPointBlockList.breakPointCountInstance(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getCountInstance().getValue());
            BreakPointBlockList.breakPointCountStatic(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getCountStatic().getValue());
        } else {
            BreakPointBlockList.breakPointCtxStd(window.getFrameBpForm().getSelectedPb(),window.getCurrentResult(), window.getResultContextNext().generate(window.getStopDbg()),window.getFrameBpForm().getConditionalStd().getText());
            BreakPointBlockList.breakPointCountStd(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getCountStd().getValue());
        }
        window.getFrameBpForm().setSelectedPb(null);
    }
}
