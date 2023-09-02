package code.expressionlanguage.adv;

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
        currentResult.toggleWatchPoint(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        FramePoints fp_ = window.getFramePoints();
        fp_.guiContentBuildClear();
        fp_.refreshWatch(window, currentResult);
        fp_.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

}
