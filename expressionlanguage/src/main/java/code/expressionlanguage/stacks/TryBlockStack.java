package code.expressionlanguage.stacks;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.CatchEval;
import code.expressionlanguage.methods.FinallyEval;
import code.util.CustList;

public final class TryBlockStack extends TryStack implements RemovableVars {

//    private Element parentForNode;

//    private Element tryNode;

//    private int visitedCatch = CustList.INDEX_NOT_FOUND_ELT;

//    private final CustList<Element> catchNodes = new CustList<Element>();

    private final CustList<BracedBlock> catchBlocks = new CustList<BracedBlock>();

    private CallingFinally calling;
//    private Element finallyClause;

//    private boolean visitedFinally;

//    private Element currentNode;

//    public Element getParentForNode() {
//        return parentForNode;
//    }
//
//    public void setParentForNode(Element _parentForNode) {
//        parentForNode = _parentForNode;
//    }

//    public Element getTryNode() {
//        return tryNode;
//    }
//
//    public void setTryNode(Element _tryNode) {
//        tryNode = _tryNode;
//    }

//    public int getVisitedCatch() {
//        return visitedCatch;
//    }
//
//    public void setVisitedCatch(int _visitedCatch) {
//        visitedCatch = _visitedCatch;
//    }
    
    public BracedBlock getLastCatchBlock() {
        return catchBlocks.last();
    }

    public BracedBlock getCurrentCatchBlock() {
//        return catchBlocks.get(visitedCatch);
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
            _ip.getCurrentEls().clear();
            _ip.getReadWrite().setBlock(getLastCatchBlock());
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeLastBlock();
    }

//    public Element getLastCatchNode() {
//        return catchNodes.last();
//    }
//
//    public Element getCurrentCatchNode() {
//        return catchNodes.get(visitedCatch);
//    }
//
//    public CustList<Element> getCatchNodes() {
//        return catchNodes;
//    }

//    public Element getFinallyClause() {
//        return finallyClause;
//    }
//
//    public void setFinallyClause(Element _finallyClause) {
//        finallyClause = _finallyClause;
//    }

//    public boolean isVisitedFinally() {
//        return visitedFinally;
//    }
//
//    public void setVisitedFinally(boolean _visitedFinally) {
//        visitedFinally = _visitedFinally;
//    }

//    public Element getCurrentNode() {
//        return currentNode;
//    }
//
//    public void setCurrentNode(Element _currentNode) {
//        currentNode = _currentNode;
//    }
}
