package code.expressionlanguage.stacks;
import code.expressionlanguage.methods.BracedBlock;

public final class SwitchBlockStack implements RemovableVars {

    private BracedBlock block;

    private BracedBlock lastVisitedBlock;

    private BracedBlock currentVisitedBlock;
    @Override
    public BracedBlock getBlock() {
        return block;
    }

    public void setBlock(BracedBlock _block) {
        block = _block;
    }

    @Override
    public void setCurrentVisitedBlock(BracedBlock _bl) {
        currentVisitedBlock = _bl;
    }

    @Override
    public BracedBlock getLastBlock() {
        return block;
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return currentVisitedBlock;
    }

    public BracedBlock getLastVisitedBlock() {
        return lastVisitedBlock;
    }

    public void setLastVisitedBlock(BracedBlock _lastVisitedBlock) {
        lastVisitedBlock = _lastVisitedBlock;
    }

}
