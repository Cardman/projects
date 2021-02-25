package code.sml;

public final class NotTextElement extends FullNode implements Element {

    private final ElementList childElements = new ElementList();
    protected NotTextElement(Document _ownerDocument) {
        super(_ownerDocument);
    }

    @Override
    public ElementList getChildElements() {
        return childElements;
    }

    @Override
    public void appendChild(Node _newChild) {
        ((Element)_newChild).setParentNode(this);
        childElements.add((Element) _newChild);
    }

    @Override
    public void removeChild(Node _oldChild) {
        int len_ = childElements.size();
        for (int i = 0; i < len_; i++) {
            Element e_ = childElements.get(i);
            if (e_ != _oldChild) {
                continue;
            }
            e_.setParentNode(null);
            childElements.remove(i);
            return;
        }
    }

    @Override
    public void replaceChild(Node _newChild, Node _oldChild) {
        int len_ = childElements.size();
        for (int i = 0; i < len_; i++) {
            Element e_ = childElements.get(i);
            if (e_ != _oldChild) {
                continue;
            }
            e_.setParentNode(null);
            ((MutableNode)_newChild).setParentNode(this);
            childElements.set(i, (Element) _newChild);
            return;
        }
    }

    @Override
    public void insertBefore(Node _newChild, Node _refChild) {
        int len_ = childElements.size();
        for (int i = 0; i < len_; i++) {
            Element e_ = childElements.get(i);
            if (e_ != _refChild) {
                continue;
            }
            ((MutableNode)_newChild).setParentNode(this);
            childElements.add(i, (Element) _newChild);
            return;
        }
    }

    @Override
    public void insertAfter(Node _newChild, Node _refChild) {
        int len_ = childElements.size();
        for (int i = 0; i < len_; i++) {
            Element e_ = childElements.get(i);
            if (e_ != _refChild) {
                continue;
            }
            ((MutableNode)_newChild).setParentNode(this);
            childElements.add(i + 1, (Element) _newChild);
            return;
        }
    }

    @Override
    public boolean hasChildNodes() {
        return getFirstChild() != null;
    }

    @Override
    public boolean hasAttributes() {
        return !getAttributes().isEmpty();
    }

    @Override
    public Node getNextSibling() {
        Element parentNode_ = getParentNode();
        if (!(parentNode_ instanceof NotTextElement)) {
            return null;
        }
        int index_ = -1;
        int size_ = ((NotTextElement) parentNode_).childElements.getLength() - 1;
        for (int i = 0; i < size_; i++) {
            if (((NotTextElement) parentNode_).childElements.item(i) == this) {
                index_ = i + 1;
            }
        }
        return tryGetElt((NotTextElement) parentNode_, index_);
    }

    @Override
    public Node getPreviousSibling() {
        Element parentNode_ = getParentNode();
        if (!(parentNode_ instanceof NotTextElement)) {
            return null;
        }
        int index_ = -1;
        int size_ = ((NotTextElement) parentNode_).childElements.getLength();
        for (int i = 1; i < size_; i++) {
            if (((NotTextElement) parentNode_).childElements.item(i) == this) {
                index_ = i - 1;
            }
        }
        return tryGetElt((NotTextElement) parentNode_, index_);
    }

    private static Node tryGetElt(NotTextElement _parentNode, int _index) {
        if (!_parentNode.childElements.isValidIndex(_index)) {
            return null;
        }
        return _parentNode.childElements.item(_index);
    }

    @Override
    public Node getFirstChild() {
        if (childElements.isEmpty()) {
            return null;
        }
        return childElements.first();
    }

    @Override
    public Node getLastChild() {
        if (childElements.isEmpty()) {
            return null;
        }
        return childElements.last();
    }

}
