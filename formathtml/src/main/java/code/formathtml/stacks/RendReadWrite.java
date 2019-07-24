package code.formathtml.stacks;

import code.formathtml.RendBlock;
import code.sml.Document;
import code.sml.Node;

public final class RendReadWrite {

    private RendBlock read;
    private Node write;
    private Document document;

    public RendBlock getRead() {
        return read;
    }

    public void setRead(RendBlock _read) {
        read = _read;
    }

    public Node getWrite() {
        return write;
    }

    public void setWrite(Node _write) {
        write = _write;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document _document) {
        document = _document;
    }
}
