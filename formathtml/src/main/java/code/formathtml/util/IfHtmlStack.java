package code.formathtml.util;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import code.expressionlanguage.stacks.IfStack;
import code.util.CustList;

public final class IfHtmlStack extends IfStack implements BlockHtml {

    private final CustList<Element> nodes = new CustList<Element>();
    
    private Element readNode;

    private Element writeNode;

    public void changeVisitedElement(Node _node) {
        int index_ = CustList.FIRST_INDEX;
        while (nodes.get(index_) != _node) {
            index_++;
        }
        setVisitedBlock(index_);
    }

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
