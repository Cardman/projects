package code.xml.components;

public abstract class Node {

    private Document ownerDocument;

    private Element parentNode;

    protected Node(Document _ownerDocument) {
        ownerDocument = _ownerDocument;
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
    public abstract ChangeableChild getNextSibling();
    public abstract ChangeableChild getPreviousSibling();
    public abstract ChangeableChild getFirstChild();
    public abstract ChangeableChild getLastChild();
    public abstract NodeList getChildNodes();
    public abstract String getNodeName();
    public abstract String getNodeValue();

    public abstract void appendChild(ChangeableChild _newChild);
    public abstract void removeChild(ChangeableChild _oldChild);
    public abstract void replaceChild(ChangeableChild _newChild, ChangeableChild _oldChild);
    public abstract void insertBefore(ChangeableChild _newChild, ChangeableChild _refChild);
    public abstract void insertAfter(ChangeableChild _newChild, ChangeableChild _refChild);

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
