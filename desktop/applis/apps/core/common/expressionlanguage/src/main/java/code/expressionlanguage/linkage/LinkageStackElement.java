package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public final class LinkageStackElement {
    private AbsBk block;
    private OperationNode current;
    private int indexLoop;
    private int indexAnnotationGroup=-1;
    private int indexAnnotation;
    private final int indexEnd;
    private final boolean annotationMode;
    private boolean stopVisit;
    private final CustList<PartOffset> partsAfter = new CustList<PartOffset>();
    private boolean visitedParams;

    public LinkageStackElement(int _indexEnd) {
        this(false,_indexEnd);
    }

    public LinkageStackElement(boolean _annotationMode, int _indexEnd) {
        annotationMode = _annotationMode;
        indexEnd = _indexEnd;
    }

    public void element(OperationNode _oper,LinkageStackElementIn _v) {
        current = _oper;
        element(_v);
    }

    public void element(LinkageStackElementIn _v) {
        block = _v.getBlock();
        indexLoop = _v.getIndexLoop();
        indexAnnotation = _v.getIndexAnnotation();
        indexAnnotationGroup = _v.getIndexAnnotationGroup();
    }

    public boolean noVisited() {
        return current == null;
    }
    public void setNullCurrent() {
        this.current = null;
    }
    public AbsBk getBlock() {
        return block;
    }

    public void setBlock(AbsBk _v) {
        this.block = _v;
    }

    public OperationNode getCurrent() {
        return current;
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

    public boolean isAnnotationMode() {
        return annotationMode;
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
