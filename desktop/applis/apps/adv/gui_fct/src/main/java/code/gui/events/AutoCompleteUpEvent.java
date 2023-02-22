package code.gui.events;

import code.gui.AutoCompleteDocument;

public final class AutoCompleteUpEvent implements AbsActionListener {
    private final AutoCompleteDocument autoCompleteDocument;

    public AutoCompleteUpEvent(AutoCompleteDocument _a) {
        this.autoCompleteDocument = _a;
    }

    @Override
    public void action() {
        autoCompleteDocument.upEvent();
    }
}
