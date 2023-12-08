package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsPairPoint;
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
        AbsPairPoint pair_ = currentResult.tryGetWatchPair(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        ToggleBreakPointEnabledEvent.updateSelectedChecked(pair_,fp_);
        fp_.refreshWatch(currentResult);
        fp_.refreshMethod(currentResult);
        fp_.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

}
