package code.gui.events;

import code.gui.AutoCompleteDocument;

public final class AutoCompleteEscapeEvent implements AbsActionListener {
    private final AutoCompleteDocument autoCompleteDocument;

    public AutoCompleteEscapeEvent(AutoCompleteDocument _a) {
        this.autoCompleteDocument = _a;
    }

    @Override
    public void action() {
        autoCompleteDocument.getList().clear();
        autoCompleteDocument.getList().select(-1);
        autoCompleteDocument.getList().repaint();
        autoCompleteDocument.getList().revalidate();
        autoCompleteDocument.hideAutocompletePopup();
    }
}
