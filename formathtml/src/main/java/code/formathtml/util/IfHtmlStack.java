package code.formathtml.util;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import code.expressionlanguage.stacks.IfStack;
import code.util.CustList;

public final class IfHtmlStack extends IfStack implements BlockHtml {

//    private boolean entered;

//    private Element parentForNode;

//    private int visitedBlock = List.INDEX_NOT_FOUND_ELT;

//    private final List<Block> blocks = new List<Block>();

    private final CustList<Element> nodes = new CustList<Element>();
    
    private Element readNode;

    private Element writeNode;

//    @Override
//    public boolean isEntered() {
//        return entered;
//    }
//
//    @Override
//    public void setEntered(boolean _entered) {
//        entered = _entered;
//    }

//    public Element getParentForNode() {
//        return parentForNode;
//    }
//
//    public void setParentForNode(Element _parentForNode) {
//        parentForNode = _parentForNode;
//    }

//    @Override
//    public int getVisitedBlock() {
//        return visitedBlock;
//    }
//
//    @Override
//    public void setVisitedBlock(int _visitedBlock) {
//        visitedBlock = _visitedBlock;
//    }
    
//    public Block lastVisitedBlock() {
//        return blocks.last();
//    }
//    
//    public Block getCurentVisitedBlock() {
//        return blocks.get(getVisitedBlock());
//    }
//    public List<Block> getBlocks() {
//        return blocks;
//    }
    public void changeVisitedElement(Node _node) {
        int index_ = CustList.FIRST_INDEX;
        while (nodes.get(index_) != _node) {
            index_++;
        }
        setVisitedBlock(index_);
    }

    public Element lastVisitedNode() {
        return nodes.last();
    }

    public Element getCurentVisitedNode() {
        return nodes.get(getVisitedBlock());
    }
    public CustList<Element> getNodes() {
        return nodes;
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
}
