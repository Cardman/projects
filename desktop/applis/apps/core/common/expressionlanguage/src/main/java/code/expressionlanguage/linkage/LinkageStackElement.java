package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.opers.OperationNode;

public final class LinkageStackElement {
    private Block block;
    private OperationNode current;
    private int indexLoop;
    private int indexAnnotationGroup=-1;
    private int indexAnnotation;
    private int indexEnd;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block _v) {
        this.block = _v;
    }

    public OperationNode getCurrent() {
        return current;
    }

    public void setCurrent(OperationNode _v) {
        this.current = _v;
    }

    public int getIndexLoop() {
        return indexLoop;
    }

    public void setIndexLoop(int _v) {
        this.indexLoop = _v;
    }

    public int getIndexAnnotationGroup() {
        return indexAnnotationGroup;
    }

    public void setIndexAnnotationGroup(int _v) {
        this.indexAnnotationGroup = _v;
    }

    public int getIndexAnnotation() {
        return indexAnnotation;
    }

    public void setIndexAnnotation(int _v) {
        this.indexAnnotation = _v;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int _v) {
        this.indexEnd = _v;
    }

}
