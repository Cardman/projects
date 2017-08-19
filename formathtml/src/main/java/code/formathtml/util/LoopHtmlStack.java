package code.formathtml.util;
import org.w3c.dom.Element;

import code.expressionlanguage.stacks.LoopStack;

public final class LoopHtmlStack extends LoopStack implements BreakableHtmlStack {

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
