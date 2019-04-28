package code.sml;


public interface Element extends MutableNode {

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

    public String export();
    @Override
    public boolean hasChildNodes();

    @Override
    public boolean hasAttributes();

    public NodeList getDescNodes();
    public NodeList getElementsByTagName();

    public ElementList getElementsByTagName(String _tagName);
    @Override
    public String getTextContent();

}
