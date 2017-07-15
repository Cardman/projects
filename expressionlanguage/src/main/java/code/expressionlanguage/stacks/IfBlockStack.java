package code.expressionlanguage.stacks;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.util.CustList;


public final class IfBlockStack extends IfStack implements RemovableVars {

//    private boolean entered;

//    private Element parentForNode;

//    private int visitedBlock = List.INDEX_NOT_FOUND_ELT;
//
    private final CustList<BracedBlock> blocks = new CustList<BracedBlock>();
//
//    private final List<Element> nodes = new List<Element>();
//    private Element readNode;
//
//    private Element writeNode;
//
//    public boolean isEntered() {
//        return entered;
//    }
//
//    public void setEntered(boolean _entered) {
//        entered = _entered;
//    }
//
////    public Element getParentForNode() {
////        return parentForNode;
////    }
////
////    public void setParentForNode(Element _parentForNode) {
////        parentForNode = _parentForNode;
////    }
//
//    public int getVisitedBlock() {
//        return visitedBlock;
//    }
//
//    public void setVisitedBlock(int _visitedBlock) {
//        visitedBlock = _visitedBlock;
//    }
//    
    public BracedBlock lastVisitedBlock() {
        return blocks.last();
    }
    
    public BracedBlock getCurentVisitedBlock() {
        return blocks.get(getVisitedBlock());
    }
    public CustList<BracedBlock> getBlocks() {
        return blocks;
    }
//    
//    public Element lastVisitedNode() {
//        return nodes.last();
//    }
//
//    public Element getCurentVisitedNode() {
//        return nodes.get(visitedBlock);
//    }
//    public List<Element> getNodes() {
//        return nodes;
//    }

    @Override
    public void removeVarAndLoop(PageEl _ip) {
        BracedBlock cur_ = getCurentVisitedBlock();
        cur_.removeLocalVars(_ip);
        _ip.removeLastBlock();
    }
}
