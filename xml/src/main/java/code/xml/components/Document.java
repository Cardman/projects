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
        Attr attr_ = new Attr(this);
        attr_.setName(_name);
        return attr_;
    }
    public Attr createAttributeNS(String _namespace, String _qualifiedName) {
        // TODO Auto-generated method stub
        return null;
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

    public Element createElementNS(String _namespace, String _qualifiedName) {
        // TODO Auto-generated method stub
        return null;
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
    public ChangeableChild getNextSibling() {
        return null;
    }
    @Override
    public ChangeableChild getPreviousSibling() {
        return null;
    }
    @Override
    public ChangeableChild getFirstChild() {
        return getDocumentElement();
    }
    @Override
    public ChangeableChild getLastChild() {
        return getDocumentElement();
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
    public String getNodeName() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String getNodeValue() {
        return null;
    }
    @Override
    public void appendChild(ChangeableChild _newChild) {
        Element element_ = (Element) _newChild;
        documentElement = element_;
    }
    @Override
    public void removeChild(ChangeableChild _oldChild) {
        documentElement = null;
    }
    @Override
    public void replaceChild(ChangeableChild _newChild, ChangeableChild _oldChild) {
        Element element_ = (Element) _newChild;
        documentElement = element_;
    }
    @Override
    public void insertBefore(ChangeableChild _newChild, ChangeableChild _refChild) {
        Element element_ = (Element) _newChild;
        documentElement = element_;
    }
    @Override
    public void insertAfter(ChangeableChild _newChild, ChangeableChild _refChild) {
        Element element_ = (Element) _newChild;
        documentElement = element_;
    }

    public String export() {
        return documentElement.export();
    }
    @Override
    public boolean hasChildNodes() {
        return documentElement != null;
    }
    @Override
    public String getNamespace() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String getPrefix() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void setPrefix(String _prefix) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean hasAttributes() {
        return false;
    }
    @Override
    public long compareDocumentPosition(Node _other) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public String getTextContent() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void setTextContent(String _textContent) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public String lookupPrefix(String _namespace) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public boolean isDefaultNamespace(String _namespace) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public String lookupNamespace(String _prefix) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public boolean isEqualNode(Node _arg) {
        // TODO Auto-generated method stub
        return false;
    }
}
