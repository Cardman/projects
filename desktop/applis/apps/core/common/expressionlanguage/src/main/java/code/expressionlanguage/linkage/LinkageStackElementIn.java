package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.reach.opers.ReachFieldOperation;

public final class LinkageStackElementIn {
    private final AbsBk block;
    private final int indexLoop;
    private final int indexAnnotationGroup;
    private final int indexAnnotationGroupLook;
    private final int indexAnnotation;
    private final int indexAnnotationLook;
    private final LinkageStackElementOffsets offsets;

    public LinkageStackElementIn(AbsBk _block, int _indexLoop,
                                 int _indexAnnotationGroup,
                                 int _indexAnnotationGroupLook,
                                 int _indexAnnotation,
                                 int _indexAnnotationLook,
                                 LinkageStackElementOffsets _offsets) {
        this.block = _block;
        this.indexLoop = _indexLoop;
        this.indexAnnotationGroup = _indexAnnotationGroup;
        this.indexAnnotationGroupLook = _indexAnnotationGroupLook;
        this.indexAnnotation = _indexAnnotation;
        this.indexAnnotationLook = _indexAnnotationLook;
        this.offsets = _offsets;
    }

    public boolean skipReportElement() {
        return ReachFieldOperation.caseCst(block);
    }

    public AbsBk getBlock() {
        return block;
    }

    public int getIndexLoop() {
        return indexLoop;
    }

    public int getIndexAnnotationGroup() {
        return indexAnnotationGroup;
    }

    public int getIndexAnnotationGroupLook() {
        return indexAnnotationGroupLook;
    }

    public int getIndexAnnotation() {
        return indexAnnotation;
    }

    public int getIndexAnnotationLook() {
        return indexAnnotationLook;
    }

    public int getBeginBlock() {
        return offsets.getBeginBlock();
    }

    public int getTr() {
        return offsets.getTr();
    }

    public int getTrEnd() {
        return offsets.getTrEnd();
    }

    public int getEndBlock() {
        return offsets.getEndBlock();
    }

}
