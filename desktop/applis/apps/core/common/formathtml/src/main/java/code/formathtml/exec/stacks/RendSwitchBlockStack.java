package code.formathtml.exec.stacks;
import code.formathtml.exec.blocks.RendParentBlock;

public final class RendSwitchBlockStack extends RendAbstractStask implements RendConditionBlockStack {

    private RendParentBlock block;

    private RendParentBlock lastVisitedBlock;
    private RendParentBlock currentVisitedBlock;
    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return currentVisitedBlock;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _currentVisitedBlock) {
        currentVisitedBlock = _currentVisitedBlock;
    }

    public RendParentBlock getLastVisitedBlock() {
        return lastVisitedBlock;
    }

    public void setLastVisitedBlock(RendParentBlock _lastVisitedBlock) {
        lastVisitedBlock = _lastVisitedBlock;
    }

}
