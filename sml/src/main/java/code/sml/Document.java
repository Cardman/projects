package code.sml;


public interface Document {

    public int getTabWidth();

    public Element createElement(String _tagName);

    public Element getDocumentElement();

    public Text createTextNode(String _data);
    Text createEscapedTextNode(String _data);

    public NodeList getChildNodes();

    public ElementList getChildElements();

    public void appendChild(Node _newChild);

    public void removeChild(Node _oldChild);

    public void replaceChild(Node _newChild, Node _oldChild);

    public void insertBefore(Node _newChild, Node _refChild);

    public void insertAfter(Node _newChild, Node _refChild);

    public String exportHtml();

    public void renameNode(Node _node, String _name);

    public String export();
    public NodeList getDescNodes();
    public NodeList getElementsByTagName();

    public ElementList getElementsByTagName(String _tagName);

    public boolean hasChildNodes();

    public String getTextContent();

    public boolean isEqualNode(Node _arg);
}
