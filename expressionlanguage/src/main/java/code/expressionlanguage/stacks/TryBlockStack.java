package code.expressionlanguage.stacks;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.CatchEval;
import code.expressionlanguage.methods.FinallyEval;
import code.util.CustList;

public final class TryBlockStack extends TryStack implements RemovableVars {

    private final CustList<BracedBlock> catchBlocks = new CustList<BracedBlock>();

    private CallingFinally calling;

    public BracedBlock getLastCatchBlock() {
        return catchBlocks.last();
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
        boolean isFinallyBlock_ = false;
        if (getVisitedCatch() >= CustList.FIRST_INDEX) {
            BracedBlock catchBlock_ = getCurrentCatchBlock();
            if (catchBlock_ instanceof CatchEval) {
                String var_ = ((CatchEval)catchBlock_).getVariableName();
                _ip.getCatchVars().removeKey(var_);
            } else {
                isFinallyBlock_ = true;
            }
            catchBlock_.removeLocalVars(_ip);
        } else {
            getBlock().removeLocalVars(_ip);
        }
        if (isFinallyBlock_) {
            _ip.removeLastBlock();
            return;
        }
        if (getLastCatchBlock() instanceof FinallyEval) {
            _ip.clearCurrentEls();
            _ip.getReadWrite().setBlock(getLastCatchBlock());
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeLastBlock();
    }

    @Override
    public String getInfos() {
        // TODO Auto-generated method stub
        return null;
    }
}
