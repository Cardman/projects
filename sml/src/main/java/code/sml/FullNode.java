package code.sml;

public abstract class FullNode implements MutableNode {

    private MutableNode firstChild;

    private MutableNode nextSibling;

    private MutableNode previousSibling;

    private MutableNode lastChild;

    private Document ownerDocument;

    private Element parentNode;

    protected FullNode(Document _ownerDocument) {
        ownerDocument = _ownerDocument;
    }

    @Override
    public final MutableNode getNextSibling() {
        return nextSibling;
    }

    @Override
    public final MutableNode getPreviousSibling() {
        return previousSibling;
    }

    @Override
    public final MutableNode getFirstChild() {
        return firstChild;
    }

    @Override
    public final MutableNode getLastChild() {
        return lastChild;
    }

    @Override
    public final void setNextSibling(MutableNode _node) {
        nextSibling = _node;
    }

    @Override
    public final void setPreviousSibling(MutableNode _node) {
        previousSibling = _node;
    }

    @Override
    public final void setFirstChild(MutableNode _node) {
        firstChild = _node;
    }

    @Override
    public final void setLastChild(MutableNode _node) {
        lastChild = _node;
    }

    @Override
    public Document getOwnerDocument() {
        return ownerDocument;
    }
    @Override
    public Element getParentNode() {
        return parentNode;
    }
    @Override
    public void setParentNode(Element _parentNode) {
        parentNode = _parentNode;
    }
    @Override
    public abstract NodeList getChildNodes();
    @Override
    public abstract ElementList getChildElements();

    @Override
    public abstract void appendChild(MutableNode _newChild);
    @Override
    public abstract void removeChild(MutableNode _oldChild);
    @Override
    public abstract void replaceChild(MutableNode _newChild, MutableNode _oldChild);
    @Override
    public abstract void insertBefore(MutableNode _newChild, MutableNode _refChild);
    @Override
    public abstract void insertAfter(MutableNode _newChild, MutableNode _refChild);

    @Override
    public abstract boolean hasChildNodes();

    @Override
    public abstract boolean hasAttributes();

    @Override
    public abstract String getTextContent();

}
