package code.sml;


public interface Element extends Node {

    public String getTagName();

    public void setTagName(String _tagName);

    public String getAttribute(String _name);

    public Attr getAttributeNode(String _name);

    public boolean hasAttribute(String _name);

    public void removeAttribute(String _name);
    public void removeAttributeNode(Attr _oldAttr);
    public void setAttribute(String _name, String _value);
    void setEscapedAttribute(String _name, String _value);
    public void setAttributeNode(Attr _newAttr);

    @Override
    public NamedNodeMap getAttributes();

    void setAttributes(NamedNodeMap _attributes);

    @Override
    public NodeList getChildNodes();

    @Override
    public ElementList getChildElements();

    @Override
    public void appendChild(Node _newChild);

    @Override
    public void removeChild(Node _oldChild);

    @Override
    public void replaceChild(Node _newChild, Node _oldChild);

    @Override
    public void insertBefore(Node _newChild, Node _refChild);

    @Override
    public void insertAfter(Node _newChild, Node _refChild);

    public String export();
    @Override
    public boolean hasChildNodes();

    @Override
    public boolean hasAttributes();

    @Override
    public long compareDocumentPosition(Info _other);
    public NodeList getDescNodes();
    public NodeList getElementsByTagName();

    public ElementList getElementsByTagName(String _tagName);
    @Override
    public String getTextContent();

    @Override
    public boolean isEqualNode(Node _arg);
}
