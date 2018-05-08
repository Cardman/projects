package code.expressionlanguage.stacks;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.Eval;
import code.util.CustList;

public final class TryBlockStack extends TryStack implements RemovableVars {

    private final CustList<BracedBlock> catchBlocks = new CustList<BracedBlock>();

    private CallingFinally calling;

    public BracedBlock getLastCatchBlock() {
        return catchBlocks.last();
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
    public void removeVarAndLoop(PageEl _ip) {
        getCurrentBlock().processToFinally(_ip, this);
    }

    @Override
    public String getInfos() {
        // TODO Auto-generated method stub
        return null;
    }
}
