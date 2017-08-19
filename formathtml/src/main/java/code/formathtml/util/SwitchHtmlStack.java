package code.formathtml.util;
import org.w3c.dom.Element;

import code.expressionlanguage.stacks.SwitchStack;
import code.util.CustList;

public final class SwitchHtmlStack extends SwitchStack implements BreakableHtmlStack {
    
    private Element readNode;

    private Element writeNode;

    private final CustList<Element> nodes = new CustList<Element>();
    
    public Element lastVisitedNode() {
        return nodes.last();
    }

    public Element getCurentVisitedNode() {
        return nodes.get(getVisitedBlock());
    }

    public CustList<Element> getNodes() {
        return nodes;
    }
    @Override
    public Element getReadNode() {
        return readNode;
    }

    @Override
    public void setReadNode(Element _readNode) {
        readNode = _readNode;
    }

    @Override
    public Element getWriteNode() {
        return writeNode;
    }

    @Override
    public void setWriteNode(Element _writeNode) {
        writeNode = _writeNode;
    }
}
