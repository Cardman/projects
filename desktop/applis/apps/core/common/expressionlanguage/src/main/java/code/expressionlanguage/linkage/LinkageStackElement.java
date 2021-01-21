package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public final class LinkageStackElement {
    private Block block;
    private OperationNode current;
    private int indexLoop;
    private int indexAnnotationGroup=-1;
    private int indexAnnotation;
    private int indexEnd;
    private boolean annotationMode;
    private boolean stopVisit;
    private final CustList<PartOffset> partsAfter = new CustList<PartOffset>();
    private boolean visitedParams;

    public void element(LinkageStackElement _v) {
        block = _v.block;
        indexLoop = _v.indexLoop;
        indexAnnotation = _v.indexAnnotation;
        indexAnnotationGroup = _v.indexAnnotationGroup;
    }

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

    public boolean isAnnotationMode() {
        return annotationMode;
    }

    public void setAnnotationMode(boolean _annotationMode) {
        this.annotationMode = _annotationMode;
    }

    public boolean isStopVisit() {
        return stopVisit;
    }

    public void setStopVisit(boolean _stopVisit) {
        this.stopVisit = _stopVisit;
    }

    public CustList<PartOffset> getPartsAfter() {
        return partsAfter;
    }

    public boolean isVisitedParams() {
        return visitedParams;
    }

    public void setVisitedParams(boolean _visitedParams) {
        this.visitedParams = _visitedParams;
    }
}
