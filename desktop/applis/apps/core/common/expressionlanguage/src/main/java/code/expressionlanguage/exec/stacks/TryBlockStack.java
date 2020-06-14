package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.CallingFinally;

public final class TryBlockStack extends TryStack implements RemovableVars {

    private AbruptCallingFinally calling;

    private BracedBlock block;

    private BracedBlock lastBlock;

    private BracedBlock currentBlock;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    public void setBlock(BracedBlock _block) {
        block = _block;
    }
    @Override
    public BracedBlock getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(BracedBlock _lastBlock) {
        lastBlock = _lastBlock;
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return currentBlock;
    }

    @Override
    public void setCurrentVisitedBlock(BracedBlock _bl) {
        currentBlock = _bl;
    }

    public AbruptCallingFinally getCalling() {
        return calling;
    }

    public void setCalling(AbruptCallingFinally _calling) {
        calling = _calling;
    }


}
