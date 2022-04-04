package code.bean.nat.exec.blocks;

public abstract class NatParentBlock extends NatBlock {
    private NatBlock nat;

    public final void appendChild(NatBlock _child) {
        _child.setParent(this);
        if (nat == null) {
            nat = _child;
            return;
        }
        NatBlock child_ = nat;
        while (true) {
            NatBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    @Override
    public final NatBlock getFirstChild() {
        return nat;
    }
}
