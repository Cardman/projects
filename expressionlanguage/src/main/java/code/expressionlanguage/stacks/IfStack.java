package code.expressionlanguage.stacks;
import code.util.CustList;

public abstract class IfStack extends BlockStack {

    private boolean entered;

//    private Element parentForNode;

    private int visitedBlock = CustList.INDEX_NOT_FOUND_ELT;

//    private final CustList<Block> blocks = new CustList<Block>();

//    private final CustList<Element> nodes = new CustList<Element>();

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }

//    public Element getParentForNode() {
//        return parentForNode;
//    }
//
//    public void setParentForNode(Element _parentForNode) {
//        parentForNode = _parentForNode;
//    }

    public int getVisitedBlock() {
        return visitedBlock;
    }

    public void setVisitedBlock(int _visitedBlock) {
        visitedBlock = _visitedBlock;
    }
    
//    public Block lastVisitedBlock() {
//        return blocks.last();
//    }
//    
//    public Block getCurentVisitedBlock() {
//        return blocks.get(visitedBlock);
//    }
//    public CustList<Block> getBlocks() {
//        return blocks;
//    }
//    
//    public Element lastVisitedNode() {
//        return nodes.last();
//    }
//
//    public Element getCurentVisitedNode() {
//        return nodes.get(visitedBlock);
//    }
//    public CustList<Element> getNodes() {
//        return nodes;
//    }
}
