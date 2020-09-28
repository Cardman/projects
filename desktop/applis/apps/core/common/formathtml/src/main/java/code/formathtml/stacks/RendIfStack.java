package code.formathtml.stacks;
import code.formathtml.exec.blocks.RendParentBlock;

public final class RendIfStack implements RendRemovableVars {

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
    @Override
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
    }}
