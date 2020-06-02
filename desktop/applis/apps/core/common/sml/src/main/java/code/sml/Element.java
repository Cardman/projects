package code.sml;


public interface Element extends MutableNode {

    String getTagName();

    void setTagName(String _tagName);

    String getAttribute(String _name);

    boolean hasAttribute(String _name);

    void removeAttribute(String _name);

    void setAttribute(String _name, String _value);

    void setAttributes(NamedNodeMap _attributes);

    String export();

    NodeList getDescNodes();
    NodeList getElementsByTagName();

    ElementList getElementsByTagName(String _tagName);

}
