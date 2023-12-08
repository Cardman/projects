package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsPairPoint;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleWatchPointEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext currentResult;

    public ToggleWatchPointEvent(AbsDebuggerGui _dbg, ReadOnlyTabEditor _t, ResultContext _res) {
        this.window = _dbg;
        this.tabEditor = _t;
        this.currentResult = _res;
    }

    @Override
    public void action() {
        AbsPairPoint pair_ = currentResult.tryGetWatchPair(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        AbsPairPoint before_ = ToggleBreakPointEvent.state(currentResult,pair_);
        currentResult.toggleWatchPoint(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        AbsPairPoint after_ = ToggleBreakPointEvent.state(currentResult,pair_);
        FramePoints fp_ = window.getFramePoints();
        ToggleBreakPointEnabledEvent.removeIfUsed(before_,after_,fp_);
        fp_.refreshWatch(currentResult);
        fp_.refreshMethod(currentResult);
        fp_.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

}
