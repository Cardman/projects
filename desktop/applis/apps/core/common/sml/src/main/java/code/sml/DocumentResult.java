package code.sml;

public final class DocumentResult {

    private Document document;

    private RowCol location;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document _document) {
        document = _document;
    }

    public RowCol getLocation() {
        return location;
    }

    public void setLocation(RowCol _location) {
        location = _location;
    }
}
