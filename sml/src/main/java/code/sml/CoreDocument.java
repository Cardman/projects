package code.sml;


public abstract class CoreDocument implements Node,Document {
    private Element documentElement;

    private int tabWidth;

    protected CoreDocument(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public abstract Element createElement(String _tagName);

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
    static Attr createAttribute(String _name) {
        Attr attr_ = new Attr(_name);
        return attr_;
    }

    public Node getFirstChild() {
        return documentElement;
    }

    public Node getLastChild() {
        return documentElement;
    }
    public Element getDocumentElement() {
        return documentElement;
    }

    public NamedNodeMap getAttributes() {
        return null;
    }

    public NodeList getChildNodes() {
        NodeList list_ = new NodeList();
        if (documentElement != null) {
            list_.add(documentElement);
        }
        return list_;
    }

    public ElementList getChildElements() {
        ElementList list_ = new ElementList();
        if (documentElement != null) {
            list_.add(documentElement);
        }
        return list_;
    }

    public void appendChild(Node _newChild) {
        Element element_ = (Element) _newChild;
        documentElement = element_;
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
        // TODO Auto-generated method stub
        return null;
    }

}
