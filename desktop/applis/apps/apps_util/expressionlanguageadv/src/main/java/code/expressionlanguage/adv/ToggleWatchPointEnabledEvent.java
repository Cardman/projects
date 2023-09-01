package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleWatchPointEnabledEvent implements AbsActionListener {
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext currentResult;

    public ToggleWatchPointEnabledEvent(ReadOnlyTabEditor _t, ResultContext _res) {
        this.tabEditor = _t;
        this.currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.toggleWatchPointEnabled(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

}
