package code.xml.components;


public final class Document extends Node {
    private Element documentElement;

    private int tabWidth;

    protected Document(int _tabWidth) {
        super(null);
        tabWidth = _tabWidth;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public Attr createAttribute(String _name) {
        Attr attr_ = new Attr();
        attr_.setName(_name);
        return attr_;
    }
    public Comment createComment(String _data) {
        // TODO Auto-generated method stub
        Comment comment_ = new Comment(this);
        return comment_;
    }
    public Element createElement(String _tagName) {
        Element element_ = new Element(this);
        element_.setTagName(_tagName);
        return element_;
    }

    public Text createTextNode(String _data) {
        Text text_ = new Text(this);
        text_.setTextContent(_data);
        return text_;
    }
    protected Text createEscapedTextNode(String _data) {
        Text text_ = new Text(this);
        text_.setEscapedTextContent(_data);
        return text_;
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

    public String exportHtml() {
        return documentElement.openTag();
    }

    public String export() {
        return documentElement.export();
    }
    public NodeList getElementsByTagName() {
        return documentElement.getElementsByTagName();
    }

    public NodeList getElementsByTagName(String _tagName) {
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
