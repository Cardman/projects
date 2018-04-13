package code.sml;

public abstract class FullNode implements Node {

    private Node firstChild;

    private Node nextSibling;

    private Node previousSibling;

    private Node lastChild;

    private Document ownerDocument;

    private Element parentNode;

    protected FullNode(Document _ownerDocument) {
        ownerDocument = _ownerDocument;
    }

    @Override
    public final Node getNextSibling() {
        return nextSibling;
    }

    @Override
    public final Node getPreviousSibling() {
        return previousSibling;
    }

    @Override
    public final Node getFirstChild() {
        return firstChild;
    }

    @Override
    public final Node getLastChild() {
        return lastChild;
    }

    @Override
    public final void setNextSibling(Node _node) {
        nextSibling = _node;
    }

    @Override
    public final void setPreviousSibling(Node _node) {
        previousSibling = _node;
    }

    @Override
    public final void setFirstChild(Node _node) {
        firstChild = _node;
    }

    @Override
    public final void setLastChild(Node _node) {
        lastChild = _node;
    }

    @Override
    public Document getOwnerDocument() {
        return ownerDocument;
    }
    @Override
    public Element getParentNode() {
        return parentNode;
    }
    @Override
    public void setParentNode(Element _parentNode) {
        parentNode = _parentNode;
    }
    @Override
    public abstract NamedNodeMap getAttributes();
    @Override
    public abstract NodeList getChildNodes();
    @Override
    public abstract ElementList getChildElements();

    @Override
    public abstract void appendChild(Node _newChild);
    @Override
    public abstract void removeChild(Node _oldChild);
    @Override
    public abstract void replaceChild(Node _newChild, Node _oldChild);
    @Override
    public abstract void insertBefore(Node _newChild, Node _refChild);
    @Override
    public abstract void insertAfter(Node _newChild, Node _refChild);

    @Override
    public abstract boolean hasChildNodes();

    @Override
    public abstract boolean hasAttributes();

    @Override
    public abstract String getTextContent();

    @Override
    public abstract boolean isEqualNode(Node _arg);
}
