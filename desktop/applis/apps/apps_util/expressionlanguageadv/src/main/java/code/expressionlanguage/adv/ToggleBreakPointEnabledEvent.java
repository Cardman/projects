package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleBreakPointEnabledEvent implements AbsActionListener {
    private final ReadOnlyTabEditor tabEditor;
    private ResultContext result;

    public ToggleBreakPointEnabledEvent(ReadOnlyTabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        ResultContext r_ = getResult();
        if (r_ == null) {
            return;
        }
        r_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition(), r_);
        ToggleBreakPointEvent.afterToggle(r_, tabEditor);
    }

    public ResultContext getResult() {
        return result;
    }

    public void setResult(ResultContext _r) {
        this.result = _r;
    }
}
