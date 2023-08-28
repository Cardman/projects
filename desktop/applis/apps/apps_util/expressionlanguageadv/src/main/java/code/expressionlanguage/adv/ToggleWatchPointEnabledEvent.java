package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleWatchPointEnabledEvent implements AbsActionListener {
    private final ReadOnlyTabEditor tabEditor;

    public ToggleWatchPointEnabledEvent(ReadOnlyTabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        ResultContext r_ = tabEditor.getDebuggerGui().getCurrentResult();
        r_.toggleWatchPointEnabled(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        ToggleBreakPointEvent.afterToggle(r_, tabEditor);
    }

}
