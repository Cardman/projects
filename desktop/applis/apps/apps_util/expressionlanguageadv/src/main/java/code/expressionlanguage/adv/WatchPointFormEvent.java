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
        watchAction(r_, true,o_.getRootBlockNb(), o_.getClassField().getFieldName(), window.getFrameWpForm());
    }

    static void watchAction(ResultContext _r, boolean _trField, int _nb, String _field, FrameWpForm _frame) {
        WatchPointBlockPair bp_ = _r.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_trField,_nb, _field);
        if (bp_ == null) {
            return;
        }
        _frame.setSelectedWp(bp_);
        _frame.getEnabledWp().setSelected(bp_.getValue().isEnabled());
        BreakPointFormEvent.specific(_frame.getGuiReadStackForm(), true, bp_.getValue().getResultRead(), _frame.getCommonFrame());
        BreakPointFormEvent.specific(_frame.getGuiWriteStackForm(), true, bp_.getValue().getResultWrite(), _frame.getCommonFrame());
        BreakPointFormEvent.specific(_frame.getGuiCompoundReadStackForm(), true, bp_.getValue().getResultCompoundRead(), _frame.getCommonFrame());
        BreakPointFormEvent.specific(_frame.getGuiCompoundWriteStackForm(), true, bp_.getValue().getResultCompoundWrite(), _frame.getCommonFrame());
        BreakPointFormEvent.specific(_frame.getGuiCompoundWriteErrStackForm(), true, bp_.getValue().getResultCompoundWriteErr(), _frame.getCommonFrame());
        _frame.getRead().setSelected(bp_.getValue().isRead());
        _frame.getWrite().setSelected(bp_.getValue().isWrite());
        _frame.getCompoundRead().setSelected(bp_.getValue().isCompoundRead());
        _frame.getCompoundWrite().setSelected(bp_.getValue().isCompoundWrite());
        _frame.getCompoundWriteErr().setSelected(bp_.getValue().isCompoundWriteErr());
        _frame.getCommonFrame().setVisible(true);
        PackingWindowAfter.pack(_frame.getCommonFrame());
    }


}
