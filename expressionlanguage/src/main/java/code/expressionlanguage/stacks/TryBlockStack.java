package code.expressionlanguage.stacks;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.Eval;
import code.util.CustList;

public final class TryBlockStack extends TryStack implements BreakableBlockStack, RemovableVars {

    private final CustList<BracedBlock> catchBlocks = new CustList<BracedBlock>();

    private CallingFinally calling;

    private boolean finished;

    private BracedBlock block;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    @Override
    public void setBlock(BracedBlock _block) {
        block = _block;
    }
    @Override
    public BracedBlock getLastBlock() {
        return getLastCatchBlock();
    }

    public BracedBlock getLastCatchBlock() {
        return catchBlocks.last();
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return (BracedBlock) getCurrentBlock();
    }
    public Eval getCurrentBlock() {
        int index_ = getVisitedCatch();
        if (index_ >= CustList.FIRST_INDEX) {
            return (Eval) catchBlocks.get(index_);
        }
        return (Eval) getBlock();
    }

    public BracedBlock getCurrentCatchBlock() {
        return catchBlocks.get(getVisitedCatch());
    }

    public CustList<BracedBlock> getCatchBlocks() {
        return catchBlocks;
    }

    public CallingFinally getCalling() {
        return calling;
    }

    public void setCalling(CallingFinally _calling) {
        calling = _calling;
    }

    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        getCurrentBlock().processToFinally(_ip, this);
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void setFinished(boolean _finished) {
        finished = _finished;
    }
    @Override
    public String getInfos() {
        // TODO Auto-generated method stub
        return null;
    }
}
