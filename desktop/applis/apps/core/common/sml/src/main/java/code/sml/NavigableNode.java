package code.sml;

public abstract class NavigableNode extends FullNode {

    private NavigableNode firstChild;

    private NavigableNode nextSibling;

    private NavigableNode previousSibling;

    private NavigableNode lastChild;
    protected NavigableNode(Document _ownerDocument) {
        super(_ownerDocument);
    }

    public final Node getNextSibling() {
        return nextSibling;
    }

    public final Node getPreviousSibling() {
        return previousSibling;
    }

    public final Node getFirstChild() {
        return firstChild;
    }

    public final Node getLastChild() {
        return lastChild;
    }

    public final void setNextSibling(NavigableNode _node) {
        nextSibling = _node;
    }

    public final void setPreviousSibling(NavigableNode _node) {
        previousSibling = _node;
    }

    public final void setFirstChild(NavigableNode _node) {
        firstChild = _node;
    }

    public final void setLastChild(NavigableNode _node) {
        lastChild = _node;
    }

}
