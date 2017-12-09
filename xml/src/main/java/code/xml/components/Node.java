package code.xml.components;

public abstract class Node implements Info {

    private Node firstChild;

    private Node nextSibling;

    private Node previousSibling;

    private Node lastChild;

    private Document ownerDocument;

    private Element parentNode;

    protected Node(Document _ownerDocument) {
        ownerDocument = _ownerDocument;
    }

    public final Node getNextSibling() {
        return nextSibling;
    }

    public final Node getPreviousSibling() {
        return previousSibling;
    }

    public final Node getFirstChild() {
        return firstChild;
    }

    public final Node getLastChild() {
        return lastChild;
    }

    protected final void setNextSibling(Node _node) {
        nextSibling = _node;
    }

    protected final void setPreviousSibling(Node _node) {
        previousSibling = _node;
    }

    protected final void setFirstChild(Node _node) {
        firstChild = _node;
    }

    protected final void setLastChild(Node _node) {
        lastChild = _node;
    }

    public Document getOwnerDocument() {
        return ownerDocument;
    }
    public Element getParentNode() {
        return parentNode;
    }
    protected void setParentNode(Element _parentNode) {
        parentNode = _parentNode;
    }
    public abstract NamedNodeMap getAttributes();
    public abstract NodeList getChildNodes();
    public abstract String getNodeName();
    public abstract String getNodeValue();

    public abstract void appendChild(Node _newChild);
    public abstract void removeChild(Node _oldChild);
    public abstract void replaceChild(Node _newChild, Node _oldChild);
    public abstract void insertBefore(Node _newChild, Node _refChild);
    public abstract void insertAfter(Node _newChild, Node _refChild);

    public abstract boolean hasChildNodes();

    public abstract boolean hasAttributes();

    public abstract String getTextContent();

    public abstract boolean isEqualNode(Node _arg);
}
