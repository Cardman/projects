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
        watchAction(r_, window, true,o_.getRootBlockNb(), o_.getClassField().getFieldName());
    }

    static void watchAction(ResultContext _r, AbsDebuggerGui _window, boolean _trField, int _nb, String _field) {
        WatchPointBlockPair bp_ = _r.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_trField,_nb, _field);
        if (bp_ == null) {
            return;
        }
        _window.getFrameWpForm().setSelectedWp(bp_);
        _window.getFrameWpForm().getEnabledWp().setSelected(bp_.getValue().isEnabled());
        BreakPointFormEvent.specific(_window.getFrameWpForm().getGuiReadStackForm(), true, bp_.getValue().getResultRead(), _window);
        BreakPointFormEvent.specific(_window.getFrameWpForm().getGuiWriteStackForm(), true, bp_.getValue().getResultWrite(), _window);
        BreakPointFormEvent.specific(_window.getFrameWpForm().getGuiCompoundReadStackForm(), true, bp_.getValue().getResultCompoundRead(), _window);
        BreakPointFormEvent.specific(_window.getFrameWpForm().getGuiCompoundWriteStackForm(), true, bp_.getValue().getResultCompoundWrite(), _window);
        BreakPointFormEvent.specific(_window.getFrameWpForm().getGuiCompoundWriteErrStackForm(), true, bp_.getValue().getResultCompoundWriteErr(), _window);
        _window.getFrameWpForm().getRead().setSelected(bp_.getValue().isRead());
        _window.getFrameWpForm().getWrite().setSelected(bp_.getValue().isWrite());
        _window.getFrameWpForm().getCompoundRead().setSelected(bp_.getValue().isCompoundRead());
        _window.getFrameWpForm().getCompoundWrite().setSelected(bp_.getValue().isCompoundWrite());
        _window.getFrameWpForm().getCompoundWriteErr().setSelected(bp_.getValue().isCompoundWriteErr());
        _window.getFrameWpForm().getCommonFrame().setVisible(true);
        PackingWindowAfter.pack(_window.getFrameWpForm().getCommonFrame());
    }


}
