package code.formathtml.stacks;
import code.expressionlanguage.exec.stacks.TryStack;
import code.formathtml.RendCallingFinally;
import code.formathtml.RendEval;
import code.formathtml.RendParentBlock;

public final class RendTryBlockStack extends TryStack implements RendRemovableVars {

    private RendAbruptCallingFinally calling;

    private RendParentBlock block;

    private RendParentBlock lastBlock;

    private RendParentBlock currentBlock;

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

    public void setLastBlock(RendParentBlock _lastBlock) {
        lastBlock = _lastBlock;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return currentBlock;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        currentBlock = _bl;
    }

    public RendAbruptCallingFinally getCalling() {
        return calling;
    }

    public void setCalling(RendAbruptCallingFinally _calling) {
        calling = _calling;
    }

}
