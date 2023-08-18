package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.PackingWindowAfter;
import code.gui.events.AbsActionListener;

public final class WatchPointFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;

    public WatchPointFormEvent(AbsDebuggerGui _dbg, ReadOnlyTabEditor _t) {
        window = _dbg;
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        ResultContext r_ = tabEditor.getDebuggerGui().getCurrentResult();
        int caret_ = tabEditor.getCenter().getCaretPosition();
        SynthFieldInfo o_ = ResultExpressionOperationNode.vexerChamps(r_.getPageEl(), tabEditor.getFullPath(),caret_);
        WatchPointBlockPair bp_ = r_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(o_.getRootBlockNb(),o_.getClassField().getFieldName());
        if (bp_ == null) {
            return;
        }
        window.getFrameWpForm().setSelectedWp(bp_);
        window.getFrameWpForm().getEnabledWp().setSelected(bp_.getValue().isEnabled());
        BreakPointFormEvent.specific(window.getFrameWpForm().getGuiReadStackForm(), true, bp_.getValue().getResultRead(),window);
        BreakPointFormEvent.specific(window.getFrameWpForm().getGuiWriteStackForm(), true, bp_.getValue().getResultWrite(),window);
        BreakPointFormEvent.specific(window.getFrameWpForm().getGuiCompoundReadStackForm(), true, bp_.getValue().getResultCompoundRead(),window);
        BreakPointFormEvent.specific(window.getFrameWpForm().getGuiCompoundWriteStackForm(), true, bp_.getValue().getResultCompoundWrite(),window);
        BreakPointFormEvent.specific(window.getFrameWpForm().getGuiCompoundWriteErrStackForm(), true, bp_.getValue().getResultCompoundWriteErr(),window);
        window.getFrameWpForm().getRead().setSelected(bp_.getValue().isRead());
        window.getFrameWpForm().getWrite().setSelected(bp_.getValue().isWrite());
        window.getFrameWpForm().getCompoundRead().setSelected(bp_.getValue().isCompoundRead());
        window.getFrameWpForm().getCompoundWrite().setSelected(bp_.getValue().isCompoundWrite());
        window.getFrameWpForm().getCompoundWriteErr().setSelected(bp_.getValue().isCompoundWriteErr());
        window.getFrameWpForm().getCommonFrame().setVisible(true);
        PackingWindowAfter.pack(window.getFrameWpForm().getCommonFrame());
    }


}
