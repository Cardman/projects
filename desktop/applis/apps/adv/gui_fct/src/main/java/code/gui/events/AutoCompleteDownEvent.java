package code.gui.events;

import code.gui.AutoCompleteDocument;

public final class AutoCompleteDownEvent implements AbsActionListener {
    private final AutoCompleteDocument autoCompleteDocument;

    public AutoCompleteDownEvent(AutoCompleteDocument _a) {
        this.autoCompleteDocument = _a;
    }

    @Override
    public void action() {
        autoCompleteDocument.downEvent();
    }
}
