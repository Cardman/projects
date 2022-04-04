package code.bean.nat.exec.blocks;

public abstract class NatLeaf extends NatBlock {
    @Override
    public NatBlock getFirstChild() {
        return null;
    }
}
