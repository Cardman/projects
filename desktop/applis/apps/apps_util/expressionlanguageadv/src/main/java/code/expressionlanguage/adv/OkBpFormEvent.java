package code.expressionlanguage.adv;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.gui.events.AbsActionListener;

public final class OkBpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public OkBpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getBpForm().setVisible(false);
        window.getSelectedPb().getValue().setEnabled(window.getEnabledBp().isSelected());
        window.getSelectedPb().getValue().setInstanceType(window.getInstanceType().isSelected());
        window.getSelectedPb().getValue().setStaticType(window.getStaticType().isSelected());
        if (window.getSelectedPb().getValue().isEnabledChgtType()) {
            BreakPointBlockList.breakPointCtxInstance(window.getSelectedPb(),window.getCurrentResult(),new AdvContextGenerator(window.getStopDbg()),window.getConditionalInstance().getText());
            BreakPointBlockList.breakPointCtxStatic(window.getSelectedPb(),window.getCurrentResult(),new AdvContextGenerator(window.getStopDbg()),window.getConditionalStatic().getText());
        } else {
            BreakPointBlockList.breakPointCtxStd(window.getSelectedPb(),window.getCurrentResult(),new AdvContextGenerator(window.getStopDbg()),window.getConditionalStd().getText());
        }
        window.setSelectedPb(null);
    }
}
