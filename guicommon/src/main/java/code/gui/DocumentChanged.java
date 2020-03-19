package code.gui;

final class DocumentChanged implements Runnable {

    private AutoCompleteDocument autoCompleteDocument;
    DocumentChanged(AutoCompleteDocument _autoCompleteDocument) {
        autoCompleteDocument = _autoCompleteDocument;
    }
    @Override
    public void run() {
        autoCompleteDocument.documentChanged();
    }
}
