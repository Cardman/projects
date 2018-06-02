package code.formathtml.util;
import code.expressionlanguage.stacks.TryStack;
import code.sml.Element;
import code.util.CustList;

public final class TryHtmlStack extends TryStack implements BlockHtml{

    private final CustList<Element> catchNodes = new CustList<Element>();
    private Element readNode;

    private Element writeNode;

    private Element finallyNode;

    private int visitedCatch = CustList.INDEX_NOT_FOUND_ELT;

    public int getVisitedCatch() {
        return visitedCatch;
    }

    public void setVisitedCatch(int _visitedCatch) {
        visitedCatch = _visitedCatch;
    }

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

    public Element getFinallyNode() {
        return finallyNode;
    }

    public void setFinallyNode(Element _finallyNode) {
        finallyNode = _finallyNode;
    }
}
