package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleWatchPointEnabledEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext currentResult;

    public ToggleWatchPointEnabledEvent(AbsDebuggerGui _dbg, ReadOnlyTabEditor _t, ResultContext _res) {
        this.window = _dbg;
        this.tabEditor = _t;
        this.currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.toggleWatchPointEnabled(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        FramePoints fp_ = window.getFramePoints();
        ToggleBreakPointEnabledEvent.updateSelectedChecked(keyCle(currentResult,tabEditor),fp_);
        fp_.refreshWatch(currentResult);
        fp_.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

    static WatchPointBlockPair keyCle(ResultContext _res, ReadOnlyTabEditor _t) {
        SynthFieldInfo o_ = ResultExpressionOperationNode.vexerChamps(_res.getPageEl(), _t.getFullPath(), _t.getCenter().getCaretPosition());
        return _res.watch(o_.isTrField(), o_);
    }
}
