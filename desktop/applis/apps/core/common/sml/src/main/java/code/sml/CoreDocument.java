package code.sml;


public abstract class CoreDocument implements Node,Document {
    private Element documentElement;

    private final int tabWidth;

    protected CoreDocument(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    @Override
    public Text createTextNode(String _data) {
        Text text_ = new Text(this);
        text_.setTextContent(_data);
        return text_;
    }
    @Override
    public Text createEscapedTextNode(String _data) {
        Text text_ = new Text(this);
        text_.setEscapedTextContent(_data);
        return text_;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    @Override
    public Document getOwnerDocument() {
        return null;
    }

    @Override
    public Element getParentNode() {
        return null;
    }

    @Override
    public Node getNextSibling() {
        return null;
    }

    @Override
    public Node getPreviousSibling() {
        return null;
    }

    static Attr createAttribute(String _name) {
        return new Attr(_name);
    }
    public static Attr createAttribute(String _name, String _value) {
        Attr attr_ = new Attr(_name);
        attr_.setEscapedValue(_value);
        return attr_;
    }

    @Override
    public Node getFirstChild() {
        return getDocumentElement();
    }

    @Override
    public Node getLastChild() {
        return getDocumentElement();
    }
    @Override
    public Element getDocumentElement() {
        return documentElement;
    }

    @Override
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override
    public NodeList getChildNodes() {
        NodeList list_ = new NodeList();
        if (documentElement != null) {
            list_.add(documentElement);
        }
        return list_;
    }

    @Override
    public ElementList getChildElements() {
        ElementList list_ = new ElementList();
        if (documentElement != null) {
            list_.add(documentElement);
        }
        return list_;
    }

    @Override
    public void appendChild(Node _newChild) {
        documentElement = (Element) _newChild;
    }

    public void renameNode(Node _node, String _name) {
        Element elt_ = (Element)_node;
        elt_.setTagName(_name);
    }

    public String export() {
        return documentElement.export();
    }
    public NodeList getDescNodes() {
        return documentElement.getDescNodes();
    }
    public NodeList getElementsByTagName() {
        return documentElement.getElementsByTagName();
    }

    public ElementList getElementsByTagName(String _tagName) {
        return documentElement.getElementsByTagName(_tagName);
    }

    public boolean hasChildNodes() {
        return documentElement != null;
    }

    public boolean hasAttributes() {
        return false;
    }

    public String getTextContent() {
        return documentElement.getTextContent();
    }

}
