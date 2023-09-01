package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleWatchPointEvent implements AbsActionListener {
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext currentResult;

    public ToggleWatchPointEvent(ReadOnlyTabEditor _t, ResultContext _res) {
        this.tabEditor = _t;
        this.currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.toggleWatchPoint(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

}
