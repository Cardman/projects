package code.formathtml.exec.stacks;

import code.formathtml.FormParts;
import code.formathtml.exec.blocks.RendBlock;
import code.sml.Document;
import code.sml.Element;

public final class RendReadWrite {

    private RendBlock read;
    private Element write;
    private Document document;
    private FormParts conf;

    public RendBlock getRead() {
        return read;
    }

    public void setRead(RendBlock _read) {
        read = _read;
    }

    public Element getWrite() {
        return write;
    }

    public void setWrite(Element _write) {
        write = _write;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document _document) {
        document = _document;
    }

    public FormParts getConf() {
        return conf;
    }

    public void setConf(FormParts _conf) {
        conf = _conf;
    }
}
