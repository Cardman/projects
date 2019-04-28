package code.sml;


public final class NoTextDocument extends CoreDocument {

    protected NoTextDocument(int _tabWidth) {
        super(_tabWidth);
    }
    @Override
    public NotTextElement createElement(String _tagName) {
        NotTextElement element_ = new NotTextElement(this);
        element_.setTagName(_tagName);
        return element_;
    }


    public Node getNextSibling() {
        // TODO Auto-generated method stub
        return null;
    }

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

    public void setNextSibling(Node _node) {
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

}
