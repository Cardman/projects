package code.gui.events;

import code.gui.AutoCompleteDocument;

public final class AutoCompleteEnterEvent implements AbsActionListener {
    private final AutoCompleteDocument autoCompleteDocument;

    public AutoCompleteEnterEvent(AutoCompleteDocument _a) {
        this.autoCompleteDocument = _a;
    }

    @Override
    public void action() {
        autoCompleteDocument.enterEvent();
    }
}
