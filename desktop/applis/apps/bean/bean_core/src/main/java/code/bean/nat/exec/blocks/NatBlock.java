package code.bean.nat.exec.blocks;

public abstract class NatBlock implements NatRendWithEl{

    private NatParentBlock parent;

    private NatBlock nextSibling;

    protected NatBlock() {
    }

    public final NatBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(NatBlock _nextSibling) {
        nextSibling = _nextSibling;
    }

    public final NatParentBlock getParent() {
        return parent;
    }

    protected void setParent(NatParentBlock _par) {
        parent = _par;
    }
}
