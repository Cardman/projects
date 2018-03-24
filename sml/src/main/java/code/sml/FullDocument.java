package code.sml;


public final class FullDocument extends CoreDocument implements Document {

    protected FullDocument(int _tabWidth) {
        super(_tabWidth);
    }

    public Comment createComment(String _data) {
        // TODO Auto-generated method stub
        Comment comment_ = new Comment(this);
        return comment_;
    }
    @Override
    public Element createElement(String _tagName) {
        Element element_ = new FullElement(this);
        element_.setTagName(_tagName);
        return element_;
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

    @Override
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override
    public void renameNode(Node _node, String _name) {
        if (!(_node instanceof Element)) {
            return;
        }
        Element elt_ = (Element)_node;
        elt_.setTagName(_name);
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

    @Override
    public Node getNextSibling() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getPreviousSibling() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void setNextSibling(Node _node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPreviousSibling(Node _node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setFirstChild(Node _node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setLastChild(Node _node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Document getOwnerDocument() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element getParentNode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setParentNode(Element _parentNode) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String exportHtml() {
        // TODO Auto-generated method stub
        return null;
    }
}
