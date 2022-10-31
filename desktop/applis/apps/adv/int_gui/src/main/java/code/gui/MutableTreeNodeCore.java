package code.gui;

public final class MutableTreeNodeCore implements AbstractMutableTreeNodeCore{
    private AbstractMutableTreeNodeCore parent;
    private AbstractMutableTreeNodeCore firstChild;
    private AbstractMutableTreeNodeCore nextSibling;

    public AbstractMutableTreeNodeCore getParent() {
        return parent;
    }

    public AbstractMutableTreeNodeCore getFirstChild() {
        return firstChild;
    }

    public AbstractMutableTreeNodeCore getNextSibling() {
        return nextSibling;
    }

    public void setParent(AbstractMutableTreeNodeCore _v) {
        parent = _v;
    }

    public void setFirstChild(AbstractMutableTreeNodeCore _v) {
        firstChild = _v;
    }

    public void setNextSibling(AbstractMutableTreeNodeCore _v) {
        nextSibling = _v;
    }
}
