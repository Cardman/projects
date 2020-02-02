package code.formathtml.stacks;
import code.expressionlanguage.stacks.IfStack;
import code.formathtml.*;

public final class RendIfStack extends IfStack implements RendRemovableVars {

    private RendParentBlock block;
    private RendParentBlock lastBlock;
    private RendParentBlock curentVisitedBlock;

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
