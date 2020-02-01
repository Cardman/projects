package code.formathtml.stacks;
import code.formathtml.RendParentBlock;

public final class RendSwitchBlockStack implements RendRemovableVars {

    private RendParentBlock block;

    private RendParentBlock lastVisitedBlock;

    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }

    @Override
    public RendParentBlock getLastBlock() {
        return block;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return block;
    }

    public RendParentBlock getLastVisitedBlock() {
        return lastVisitedBlock;
    }

    public void setLastVisitedBlock(RendParentBlock _lastVisitedBlock) {
        lastVisitedBlock = _lastVisitedBlock;
    }

}
