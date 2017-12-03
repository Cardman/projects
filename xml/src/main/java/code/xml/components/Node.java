package code.xml.components;

public abstract class Node {

    private Document ownerDocument;

    private Node parentNode;

    protected Node(Document _ownerDocument) {
        ownerDocument = _ownerDocument;
    }

    public Document getOwnerDocument() {
        return ownerDocument;
    }
    public Node getParentNode() {
        return parentNode;
    }
    protected void setParentNode(Node _parentNode) {
        parentNode = _parentNode;
    }
    public abstract NamedNodeMap getAttributes();
    public abstract Node getNextSibling();
    public abstract Node getPreviousSibling();
    public abstract Node getFirstChild();
    public abstract Node getLastChild();
    public abstract NodeList getChildNodes();
    public abstract String getNodeName();
    public abstract String getNodeValue();

    public abstract void appendChild(Node _newChild);
    public abstract void removeChild(Node _oldChild);
    public abstract void replaceChild(Node _newChild, Node _oldChild);
    public abstract void insertBefore(Node _newChild, Node _refChild);

    public abstract boolean hasChildNodes();

    public abstract String getNamespace();

    public abstract String getPrefix();

    public abstract void setPrefix(String _prefix);

    public abstract boolean hasAttributes();

    public abstract long compareDocumentPosition(Node _other);

    public abstract String getTextContent();

    public abstract void setTextContent(String _textContent);

    public abstract String lookupPrefix(String _namespace);

    public abstract boolean isDefaultNamespace(String _namespace);

    public abstract String lookupNamespace(String _prefix);

    public abstract boolean isEqualNode(Node _arg);
}
