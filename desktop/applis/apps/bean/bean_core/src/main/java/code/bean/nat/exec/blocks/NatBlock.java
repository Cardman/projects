package code.bean.nat.exec.blocks;

import code.util.CustList;

public abstract class NatBlock {

    private NatParentBlock parent;

    private NatBlock nextSibling;

    NatBlock() {
    }

    public static CustList<NatBlock> getDirectChildren(NatBlock _block) {
        CustList<NatBlock> l_ = new CustList<NatBlock>();
        NatBlock child_ = _block.getFirstChild();
        while (child_ != null) {
            l_.add(child_);
            child_ = child_.getNextSibling();
        }
        return l_;
    }

    public abstract NatBlock getFirstChild();

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
