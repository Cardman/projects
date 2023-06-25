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
            BreakPointBlockList.breakPointCtxInstance(window.getFrameBpForm().getSelectedPb(),window.getCurrentResult(), window.getResultContextNext().generate(window.getStopDbg()),window.getFrameBpForm().getGuiInsStackForm().getConditional().getText());
            BreakPointBlockList.breakPointCtxStatic(window.getFrameBpForm().getSelectedPb(),window.getCurrentResult(), window.getResultContextNext().generate(window.getStopDbg()),window.getFrameBpForm().getGuiStaStackForm().getConditional().getText());
            BreakPointBlockList.breakPointCountInstance(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiInsStackForm().getCount().getValue());
            BreakPointBlockList.breakPointCountStatic(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiStaStackForm().getCount().getValue());
            BreakPointBlockList.breakPointFileIndexUpdaterExcludeInstance(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiInsStackForm().getMustNotBe());
            BreakPointBlockList.breakPointFileIndexUpdaterExcludeStatic(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiStaStackForm().getMustNotBe());
            BreakPointBlockList.breakPointFileIndexUpdaterIncludeInstance(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiInsStackForm().getMustBe());
            BreakPointBlockList.breakPointFileIndexUpdaterIncludeStatic(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiStaStackForm().getMustBe());
        } else {
            BreakPointBlockList.breakPointCtxStd(window.getFrameBpForm().getSelectedPb(),window.getCurrentResult(), window.getResultContextNext().generate(window.getStopDbg()),window.getFrameBpForm().getGuiStdStackForm().getConditional().getText());
            BreakPointBlockList.breakPointCountStd(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiStdStackForm().getCount().getValue());
            BreakPointBlockList.breakPointFileIndexUpdaterExcludeStd(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiStdStackForm().getMustNotBe());
            BreakPointBlockList.breakPointFileIndexUpdaterIncludeStd(window.getFrameBpForm().getSelectedPb(),window.getFrameBpForm().getGuiStdStackForm().getMustBe());
        }
        window.getFrameBpForm().setSelectedPb(null);
    }
}
