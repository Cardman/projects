package code.expressionlanguage.stacks;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.Eval;

public final class TryBlockStack extends TryStack implements BreakableBlockStack, RemovableVars {

    private CallingFinally calling;

    private boolean finished;

    private BracedBlock block;

    private BracedBlock lastBlock;

    private Eval currentBlock;

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
        return (BracedBlock) currentBlock;
    }

    public void setCurrentBlock(Eval _currentBlock) {
        currentBlock = _currentBlock;
    }

    public CallingFinally getCalling() {
        return calling;
    }

    public void setCalling(CallingFinally _calling) {
        calling = _calling;
    }

    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        currentBlock.processToFinally(_ip, this);
    }

    @Override
    public void setFinished(boolean _finished) {
        finished = _finished;
    }
}
