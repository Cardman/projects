package code.formathtml.stacks;

import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.util.NodeContainer;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.Longs;
import code.util.StringList;

public final class RendReadWrite {

    private RendBlock read;
    private Element write;
    private Document document;
    private Configuration conf;

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

    public CustList<LongTreeMap<NodeContainer>> getContainersMap() {
        return conf.getContainersMapStack();
    }

    public CustList<StringList> getFormatIdMap() {
        return conf.getFormatIdMapStack();
    }
    public Longs getFormsNb() {
        return conf.getFormsNb();
    }
    public Longs getInputs() {
        return conf.getInputs();
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration _conf) {
        conf = _conf;
    }
}
