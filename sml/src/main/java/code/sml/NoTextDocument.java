package code.sml;


public final class NoTextDocument extends CoreDocument implements Document {

    protected NoTextDocument(int _tabWidth) {
        super(_tabWidth);
    }
    @Override
    public NotTextElement createElement(String _tagName) {
        NotTextElement element_ = new NotTextElement(this);
        element_.setTagName(_tagName);
        return element_;
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
    public Node getFirstChild() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getLastChild() {
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
    public Text createTextNode(String _data) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Text createEscapedTextNode(String _data) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String exportHtml() {
        // TODO Auto-generated method stub
        return null;
    }

}
