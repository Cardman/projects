package code.gui;

final class FocusLost implements Runnable {

    private AutoCompleteDocument autoCompleteDocument;
    FocusLost(AutoCompleteDocument _autoCompleteDocument) {
        autoCompleteDocument = _autoCompleteDocument;
    }
    @Override
    public void run() {
        autoCompleteDocument.hideAutocompletePopup();
    }
}
