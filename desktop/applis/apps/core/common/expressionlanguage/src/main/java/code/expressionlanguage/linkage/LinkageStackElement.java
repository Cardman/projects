package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.opers.OperationNode;

public final class LinkageStackElement {
    private Block block;
    private OperationNode current;
    private int indexLoop;
    private int indexEnd;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public OperationNode getCurrent() {
        return current;
    }

    public void setCurrent(OperationNode current) {
        this.current = current;
    }

    public int getIndexLoop() {
        return indexLoop;
    }

    public void setIndexLoop(int indexLoop) {
        this.indexLoop = indexLoop;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int indexEnd) {
        this.indexEnd = indexEnd;
    }

}
