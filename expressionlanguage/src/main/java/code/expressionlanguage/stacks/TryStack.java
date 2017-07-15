package code.expressionlanguage.stacks;
import code.util.CustList;

public abstract class TryStack extends BlockStack {

//    private Element parentForNode;

//    private Element tryNode;

    private int visitedCatch = CustList.INDEX_NOT_FOUND_ELT;

//    private final CustList<Element> catchNodes = new CustList<Element>();

//    private final CustList<Block> catchBlocks = new CustList<Block>();

//    private Element finallyClause;

    private boolean visitedFinally;

//    private Element currentNode;

    private Throwable thrownException;

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

    public int getVisitedCatch() {
        return visitedCatch;
    }

    public void setVisitedCatch(int _visitedCatch) {
        visitedCatch = _visitedCatch;
    }
    
//    public Block getLastCatchBlock() {
//        return catchBlocks.last();
//    }
//
//    public Block getCurrentCatchBlock() {
//        return catchBlocks.get(visitedCatch);
//    }
//
//    public CustList<Block> getCatchBlocks() {
//        return catchBlocks;
//    }
//
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

    public boolean isVisitedFinally() {
        return visitedFinally;
    }

    public void setVisitedFinally(boolean _visitedFinally) {
        visitedFinally = _visitedFinally;
    }

    public Throwable getThrownException() {
        return thrownException;
    }

    public void setThrownException(Throwable _thrownException) {
        thrownException = _thrownException;
    }

//    public Element getCurrentNode() {
//        return currentNode;
//    }
//
//    public void setCurrentNode(Element _currentNode) {
//        currentNode = _currentNode;
//    }

}
