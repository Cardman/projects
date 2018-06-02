package code.formathtml.util;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.SwitchStack;
import code.sml.Element;
import code.util.CustList;

public final class SwitchHtmlStack extends SwitchStack implements BreakableHtmlStack {
    private Element readNode;

    private Element writeNode;

    private Struct value = NullStruct.NULL_VALUE;

    private int visitedBlock = CustList.INDEX_NOT_FOUND_ELT;

    private final CustList<Element> nodes = new CustList<Element>();
    public Element lastVisitedNode() {
        return nodes.last();
    }

    public Element getCurentVisitedNode() {
        return nodes.get(getVisitedBlock());
    }

    public int getVisitedBlock() {
        return visitedBlock;
    }

    public void increment() {
        visitedBlock++;
    }

    public void setVisitedBlock(int _visitedBlock) {
        visitedBlock = _visitedBlock;
    }

    public Struct getStruct() {
        return value;
    }
    public void setStruct(Struct _value) {
        value = _value;
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
