package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCommonFrame;
import code.gui.PackingWindowAfter;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class WatchPointFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext resultContext;

    public WatchPointFormEvent(AbsDebuggerGui _dbg, ReadOnlyTabEditor _t, ResultContext _res) {
        window = _dbg;
        this.tabEditor = _t;
        this.resultContext = _res;
    }

    @Override
    public void action() {
        int caret_ = tabEditor.getCenter().getCaretPosition();
        SynthFieldInfo o_ = ResultExpressionOperationNode.vexerChamps(resultContext.getPageEl(), tabEditor.getFullPath(),caret_);
        watchAction(resultContext, o_.isTrField(),o_.getRootBlockNb(), o_.getClassField().getFieldName(), window.getFramePoints().getFrameWpFormContent(), window, window.getFramePoints().getCommonFrame());
    }

    static void watchAction(ResultContext _r, boolean _trField, int _nb, String _field, FrameWpFormContent _content, AbsDebuggerGui _w, AbsCommonFrame _frame) {
        WatchPointBlockPair bp_ = _r.getPairWatch(_trField,_nb, _field);
        if (bp_ == null) {
            return;
        }
        _w.getFramePoints().init(_w, _r);
        _w.getFramePoints().guiContentBuild(bp_,_r);
        watchAction(_content, _frame, bp_, _r);
    }

    static void watchAction(FrameWpFormContent _content, AbsCommonFrame _frame, WatchPointBlockPair _bp, ResultContext _r) {
        _content.getEdited().setText(FramePoints.displayWatch(_bp));
        _content.getEnabledWp().setSelected(_bp.getValue().isEnabled());
        BreakPointFormEvent.specific(_content.getGuiReadStackForm(), _bp.getValue().getResultRead(), new CustList<BreakPointCondition>(), _frame,_r);
        BreakPointFormEvent.specific(_content.getGuiWriteStackForm(), _bp.getValue().getResultWrite(), new CustList<BreakPointCondition>(), _frame,_r);
        BreakPointFormEvent.specific(_content.getGuiCompoundReadStackForm(), _bp.getValue().getResultCompoundRead(), new CustList<BreakPointCondition>(), _frame,_r);
        BreakPointFormEvent.specific(_content.getGuiCompoundWriteStackForm(), _bp.getValue().getResultCompoundWrite(), new CustList<BreakPointCondition>(), _frame,_r);
        BreakPointFormEvent.specific(_content.getGuiCompoundWriteErrStackForm(), _bp.getValue().getResultCompoundWriteErr(), new CustList<BreakPointCondition>(), _frame,_r);
        _content.getRead().setSelected(_bp.getValue().isRead());
        _content.getWrite().setSelected(_bp.getValue().isWrite());
        _content.getCompoundRead().setSelected(_bp.getValue().isCompoundRead());
        _content.getCompoundWrite().setSelected(_bp.getValue().isCompoundWrite());
        _content.getCompoundWriteErr().setSelected(_bp.getValue().isCompoundWriteErr());
        _frame.setVisible(true);
        PackingWindowAfter.pack(_frame);
    }


}
