package code.sml;

public interface Node extends Info {

    public Node getNextSibling();

    public Node getPreviousSibling();

    public Node getFirstChild();

    public Node getLastChild();

    void setNextSibling(Node _node);

    void setPreviousSibling(Node _node);

    void setFirstChild(Node _node);

    void setLastChild(Node _node);

    Document getOwnerDocument();
    Element getParentNode();
    void setParentNode(Element _parentNode);
    public abstract NamedNodeMap getAttributes();
    public abstract NodeList getChildNodes();
    public abstract ElementList getChildElements();

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
