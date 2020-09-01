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

    public int getIndexAnnotationGroup() {
        return indexAnnotationGroup;
    }

    public void setIndexAnnotationGroup(int indexAnnotationGroup) {
        this.indexAnnotationGroup = indexAnnotationGroup;
    }

    public int getIndexAnnotation() {
        return indexAnnotation;
    }

    public void setIndexAnnotation(int indexAnnotation) {
        this.indexAnnotation = indexAnnotation;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int indexEnd) {
        this.indexEnd = indexEnd;
    }

}
