package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleBreakPointEnabledEvent implements AbsActionListener {
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext currentResult;

    public ToggleBreakPointEnabledEvent(ReadOnlyTabEditor _t, ResultContext _res) {
        this.tabEditor = _t;
        this.currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.toggleBreakPointEnabled(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

}
