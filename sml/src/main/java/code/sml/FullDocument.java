package code.sml;


public final class FullDocument extends CoreDocument {

    protected FullDocument(int _tabWidth) {
        super(_tabWidth);
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
    public String getTextContent() {
        // TODO Auto-generated method stub
        return null;
    }

    public Node getNextSibling() {
        // TODO Auto-generated method stub
        return null;
    }

    public Node getPreviousSibling() {
        // TODO Auto-generated method stub
        return null;
    }


    public void setNextSibling(Node _node) {
        // TODO Auto-generated method stub
        
    }

    public void setPreviousSibling(Node _node) {
        // TODO Auto-generated method stub
        
    }

    public void setFirstChild(Node _node) {
        // TODO Auto-generated method stub
        
    }

    public void setLastChild(Node _node) {
        // TODO Auto-generated method stub
        
    }

    public Document getOwnerDocument() {
        // TODO Auto-generated method stub
        return null;
    }

    public Element getParentNode() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setParentNode(Element _parentNode) {
        // TODO Auto-generated method stub
        
    }

}
