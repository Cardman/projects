package code.sml;


public abstract class CoreDocument implements Node,OwnerDocument {
    private Element documentElement;

    private int tabWidth;

    protected CoreDocument(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public abstract Element createElement(String _tagName);

    public int getTabWidth() {
        return tabWidth;
    }

    static Attr createAttribute(String _name) {
        Attr attr_ = new Attr();
        attr_.setName(_name);
        return attr_;
    }

    @Override
    public Node getFirstChild() {
        return documentElement;
    }

    @Override
    public Node getLastChild() {
        return documentElement;
    }
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
        Element element_ = (Element) _newChild;
        documentElement = element_;
        setFirstChild(documentElement);
        setLastChild(documentElement);
    }
    @Override
    public void removeChild(Node _oldChild) {
        documentElement = null;
        setFirstChild(null);
        setLastChild(null);
    }
    @Override
    public void replaceChild(Node _newChild, Node _oldChild) {
        Element element_ = (Element) _newChild;
        documentElement = element_;
        setFirstChild(documentElement);
        setLastChild(documentElement);
    }
    @Override
    public void insertBefore(Node _newChild, Node _refChild) {
        Element element_ = (Element) _newChild;
        documentElement = element_;
        setFirstChild(documentElement);
        setLastChild(documentElement);
    }
    @Override
    public void insertAfter(Node _newChild, Node _refChild) {
        Element element_ = (Element) _newChild;
        documentElement = element_;
        setFirstChild(documentElement);
        setLastChild(documentElement);
    }

    public void renameNode(Node _node, String _name) {
        if (!(_node instanceof Element)) {
            return;
        }
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
    @Override
    public boolean hasChildNodes() {
        return documentElement != null;
    }
    @Override
    public boolean hasAttributes() {
        return false;
    }
    @Override
    public long compareDocumentPosition(Info _other) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public String getTextContent() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public boolean isEqualNode(Node _arg) {
        // TODO Auto-generated method stub
        return false;
    }
}
