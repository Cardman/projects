package code.gui;

final class FocusGained implements Runnable {

    private AutoCompleteDocument autoCompleteDocument;
    FocusGained(AutoCompleteDocument _autoCompleteDocument) {
        autoCompleteDocument = _autoCompleteDocument;
    }
    @Override
    public void run() {
        autoCompleteDocument.showAutocompletePopup();
    }
}
