package code.sml;

import code.util.CustList;
import code.util.StringMap;

public final class DocumentResult {

    private Document document;

    private RowCol location;
    private StringMap<String> escaped;
    private CustList<EncodedChar> chs;
    private String input;
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

    public StringMap<String> getEscaped() {
        return escaped;
    }

    public void setEscaped(StringMap<String> _e) {
        this.escaped = _e;
    }

    public CustList<EncodedChar> getChs() {
        return chs;
    }

    public void setChs(CustList<EncodedChar> _c) {
        this.chs = _c;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String _i) {
        this.input = _i;
    }
}
