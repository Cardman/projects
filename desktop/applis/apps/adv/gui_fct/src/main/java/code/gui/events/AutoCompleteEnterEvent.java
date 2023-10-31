package code.gui.events;

import code.gui.AutoCompleteDocument;

public final class AutoCompleteEnterEvent implements AbsActionListener {
    private final AutoCompleteDocument autoCompleteDocument;
    private final AfterValidateText af;

    public AutoCompleteEnterEvent(AutoCompleteDocument _a, AfterValidateText _after) {
        this.autoCompleteDocument = _a;
        this.af = _after;
    }

    @Override
    public void action() {
        autoCompleteDocument.enterEvent();
        if (af instanceof AbsActionListener) {
            ((AbsActionListener)af).action();
        }
    }
}
