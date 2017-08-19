package code.formathtml.util;
import org.w3c.dom.Element;

import code.expressionlanguage.stacks.TryStack;
import code.util.CustList;

public final class TryHtmlStack extends TryStack implements BlockHtml{

    private final CustList<Element> catchNodes = new CustList<Element>();
    private Element readNode;

    private Element writeNode;

    public Element getLastCatchNode() {
        return catchNodes.last();
    }

    public Element getCurrentCatchNode() {
        return catchNodes.get(getVisitedCatch());
    }

    public CustList<Element> getCatchNodes() {
        return catchNodes;
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
