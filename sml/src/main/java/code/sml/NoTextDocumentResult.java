package code.sml;

public final class NoTextDocumentResult {

    private NoTextDocument document;

    private RowCol location;

    public NoTextDocument getDocument() {
        return document;
    }

    public void setDocument(NoTextDocument _document) {
        document = _document;
    }

    public RowCol getLocation() {
        return location;
    }

    public void setLocation(RowCol _location) {
        location = _location;
    }
}
