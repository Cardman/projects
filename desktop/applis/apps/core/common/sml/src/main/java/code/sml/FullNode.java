package code.sml;

public abstract class FullNode implements Node {

    private final Document ownerDocument;

    private Element parentNode;

    protected FullNode(Document _ownerDocument) {
        ownerDocument = _ownerDocument;
    }

    protected static Node next(Node _current, Node _root) {
        Node ch_ = _current.getFirstChild();
        if (ch_ != null) {
            return ch_;
        }
        Node current_ = _current;
        while (true) {
            Node next_ = current_.getNextSibling();
            if (next_ != null) {
                current_ = next_;
                break;
            }
            Element parent_ = current_.getParentNode();
            if (parent_ == _root) {
                current_ = null;
                break;
            }
            current_ = parent_;
        }
        return current_;
    }

    @Override
    public Document getOwnerDocument() {
        return ownerDocument;
    }
    @Override
    public Element getParentNode() {
        return parentNode;
    }
    public void setParentNode(Element _parentNode) {
        parentNode = _parentNode;
    }

}
