package code.gui;

final class FocusLost implements Runnable {

    private final AutoCompleteDocument autoCompleteDocument;
    FocusLost(AutoCompleteDocument _autoCompleteDocument) {
        autoCompleteDocument = _autoCompleteDocument;
    }
    @Override
    public void run() {
        autoCompleteDocument.hideAutocompletePopup();
    }
}
