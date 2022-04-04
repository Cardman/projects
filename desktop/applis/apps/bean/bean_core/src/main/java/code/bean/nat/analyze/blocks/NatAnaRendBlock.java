package code.bean.nat.analyze.blocks;


public abstract class NatAnaRendBlock {
    private NatAnaRendParentBlock parent;

    private NatAnaRendBlock nextSibling;

    protected NatAnaRendBlock() {
    }

    public abstract NatAnaRendBlock getFirstChild();

    public final NatAnaRendBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(NatAnaRendBlock _nextSibling) {
        nextSibling = _nextSibling;
    }

    public final NatAnaRendParentBlock getParent() {
        return parent;
    }

    public void setParent(NatAnaRendParentBlock _parent) {
        this.parent = _parent;
    }

}
