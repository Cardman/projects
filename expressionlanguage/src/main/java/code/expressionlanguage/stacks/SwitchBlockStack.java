package code.expressionlanguage.stacks;
import code.expressionlanguage.methods.BracedBlock;

public final class SwitchBlockStack implements RemovableVars {

    private BracedBlock block;

    private BracedBlock lastVisitedBlock;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    public void setBlock(BracedBlock _block) {
        block = _block;
    }

    @Override
    public BracedBlock getLastBlock() {
        return block;
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return block;
    }

    public BracedBlock getLastVisitedBlock() {
        return lastVisitedBlock;
    }

    public void setLastVisitedBlock(BracedBlock _lastVisitedBlock) {
        lastVisitedBlock = _lastVisitedBlock;
    }

}
