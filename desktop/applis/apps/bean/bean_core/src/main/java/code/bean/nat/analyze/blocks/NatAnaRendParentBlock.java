package code.bean.nat.analyze.blocks;

public abstract class NatAnaRendParentBlock extends NatAnaRendBlock {

    private NatAnaRendBlock nat;
    protected NatAnaRendParentBlock() {
    }

    public final void appendChild(NatAnaRendBlock _child) {
        _child.setParent(this);
        if (nat == null) {
            nat = _child;
            return;
        }
        NatAnaRendBlock child_ = nat;
        while (true) {
            NatAnaRendBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    @Override
    public final NatAnaRendBlock getFirstChild() {
        return nat;
    }
}
