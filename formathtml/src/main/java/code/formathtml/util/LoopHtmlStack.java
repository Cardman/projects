package code.formathtml.util;
import org.w3c.dom.Element;

import code.expressionlanguage.stacks.LoopStack;

public final class LoopHtmlStack extends LoopStack implements BreakableHtmlStack {

    

//    private boolean finished;

//    private boolean iterate;

//    private Iterator<?> iterator;

//    private boolean keyValue;

//    private Element forNode;

//    private Element currentNode;

//    private long index;

//    private long maxIteration;
    private Element readNode;

    private Element writeNode;

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
