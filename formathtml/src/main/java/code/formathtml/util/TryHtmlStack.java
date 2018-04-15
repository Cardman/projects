package code.formathtml.util;
import code.expressionlanguage.exceptions.WrapperException;
import code.expressionlanguage.stacks.TryStack;
import code.sml.Element;
import code.util.CustList;

public final class TryHtmlStack extends TryStack implements BlockHtml{

    private final CustList<Element> catchNodes = new CustList<Element>();
    private Element readNode;

    private Element writeNode;

    private Element finallyNode;

    private WrapperException thrownException;

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

    public WrapperException getThrownException() {
        return thrownException;
    }

    public void setThrownException(WrapperException _thrownException) {
        thrownException = _thrownException;
    }

    public Element getFinallyNode() {
        return finallyNode;
    }

    public void setFinallyNode(Element _finallyNode) {
        finallyNode = _finallyNode;
    }
}
