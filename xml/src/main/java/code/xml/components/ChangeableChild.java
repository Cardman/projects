package code.xml.components;

public abstract class ChangeableChild extends Node {

    private ChangeableChild firstChild;

    private ChangeableChild nextSibling;

    private ChangeableChild previousSibling;

    private ChangeableChild lastChild;

    protected ChangeableChild(Document _ownerDocument) {
        super(_ownerDocument);
    }

    @Override
    public final ChangeableChild getNextSibling() {
        return nextSibling;
    }

    @Override
    public final ChangeableChild getPreviousSibling() {
        return previousSibling;
    }

    @Override
    public final ChangeableChild getFirstChild() {
        return firstChild;
    }

    @Override
    public final ChangeableChild getLastChild() {
        return lastChild;
    }

    protected final void setNextSibling(ChangeableChild _node) {
        nextSibling = _node;
    }

    protected final void setPreviousSibling(ChangeableChild _node) {
        previousSibling = _node;
    }

    protected final void setFirstChild(ChangeableChild _node) {
        firstChild = _node;
    }

    protected final void setLastChild(ChangeableChild _node) {
        lastChild = _node;
    }

}
