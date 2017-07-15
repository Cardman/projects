package code.formathtml.util;
import org.w3c.dom.Element;

import code.expressionlanguage.stacks.TryStack;
import code.util.CustList;

public final class TryHtmlStack extends TryStack implements BlockHtml{

//    private Element parentForNode;

//    private Element tryNode;

//    private int visitedCatch = List.INDEX_NOT_FOUND_ELT;

    private final CustList<Element> catchNodes = new CustList<Element>();
    
    private Element readNode;

    private Element writeNode;

//    private final List<Block> catchBlocks = new List<Block>();

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
    
//    public Block getLastCatchBlock() {
//        return catchBlocks.last();
//    }
//
//    public Block getCurrentCatchBlock() {
//        return catchBlocks.get(visitedCatch);
//    }
//
//    public List<Block> getCatchBlocks() {
//        return catchBlocks;
//    }

    public Element getLastCatchNode() {
        return catchNodes.last();
    }

    public Element getCurrentCatchNode() {
//        return catchNodes.get(visitedCatch);
        return catchNodes.get(getVisitedCatch());
    }

    public CustList<Element> getCatchNodes() {
        return catchNodes;
    }
    @Override
    public Element getReadNode() {
        return readNode;
    }

    @Override
    public void setReadNode(Element _readNode) {
        readNode = _readNode;
    }

    @Override
    public Element getWriteNode() {
        return writeNode;
    }

    @Override
    public void setWriteNode(Element _writeNode) {
        writeNode = _writeNode;
    }
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
