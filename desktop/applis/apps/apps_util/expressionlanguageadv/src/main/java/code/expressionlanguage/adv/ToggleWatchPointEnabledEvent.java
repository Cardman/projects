package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.dbg.AbsPairPoint;
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
        SynthFieldInfo o_ = ResultExpressionOperationNode.vexerChamps(currentResult.getPageEl(), tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        refreshEnabled(fp_, currentResult.watch(true, o_));
        fp_.refreshWatch(window, currentResult);
        fp_.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

    static void refreshEnabled(FramePoints _fp, AbsPairPoint _ret) {
        if (_ret instanceof WatchPointBlockPair) {
            WatchPointBlockPair wp_ = _fp.getFrameWpFormContent().getSelectedWp();
            if (wp_ != null && wp_.getWp().match(((WatchPointBlockPair) _ret).getWp())) {
                _fp.getFrameWpFormContent().getEnabledWp().setSelected(wp_.getValue().isEnabled());
            }
        }

    }

}
