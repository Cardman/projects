package code.formathtml.exec.stacks;
import code.formathtml.exec.blocks.RendParentBlock;

public final class RendIfStack extends RendAbstractStask implements RendConditionBlockStack,RendEnteredStack {

    private RendParentBlock block;
    private RendParentBlock lastBlock;
    private RendParentBlock curentVisitedBlock;

    private boolean entered;

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }

    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }

    public RendParentBlock getLastBlock() {
        return lastBlock;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        curentVisitedBlock = _bl;
    }

    public void setLastBlock(RendParentBlock _lastBlock) {
        lastBlock = _lastBlock;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return curentVisitedBlock;
    }
}
