package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleWatchPointEvent implements AbsActionListener {
    private final ReadOnlyTabEditor tabEditor;

    public ToggleWatchPointEvent(ReadOnlyTabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        ResultContext r_ = tabEditor.getDebuggerGui().getCurrentResult();
        r_.toggleWatchPoint(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        ToggleBreakPointEvent.afterToggle(r_, tabEditor);
    }

}
