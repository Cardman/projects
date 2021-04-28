package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class LinkageStackElementIn {
    private AbsBk block;
    private int indexLoop;
    private int indexAnnotationGroup=-1;
    private int indexAnnotationGroupLook=-1;
    private int indexAnnotation;
    private int indexAnnotationLook;
    private int tr;
    private int trEnd;
    private int beginBlock;
    private int endBlock;

    public LinkageStackElementIn(AbsBk _block, int _indexLoop,
                                 int _indexAnnotationGroup,
                                 int _indexAnnotationGroupLook,
                                 int _indexAnnotation,
                                 int _indexAnnotationLook) {
        setBlock(_block);
        setIndexLoop(_indexLoop);
        setIndexAnnotationGroup(_indexAnnotationGroup);
        setIndexAnnotationGroupLook(_indexAnnotationGroupLook);
        setIndexAnnotation(_indexAnnotation);
        setIndexAnnotationLook(_indexAnnotationLook);
    }
    public void offsets(int _tr, int _begin, int _end) {
        setTr(_tr);
        setBeginBlock(_begin);
        setEndBlock(_end);
    }

    public void offsets(int _begin) {
        setBeginBlock(_begin);
    }
    public AbsBk getBlock() {
        return block;
    }

    public void setBlock(AbsBk _v) {
        this.block = _v;
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

    public int getIndexAnnotationGroupLook() {
        return indexAnnotationGroupLook;
    }

    public void setIndexAnnotationGroupLook(int _v) {
        this.indexAnnotationGroupLook = _v;
    }

    public int getIndexAnnotation() {
        return indexAnnotation;
    }

    public void setIndexAnnotation(int _v) {
        this.indexAnnotation = _v;
    }

    public int getIndexAnnotationLook() {
        return indexAnnotationLook;
    }

    public void setIndexAnnotationLook(int _v) {
        this.indexAnnotationLook = _v;
    }

    public int getBeginBlock() {
        return beginBlock;
    }

    public void setBeginBlock(int _beginBlock) {
        this.beginBlock = _beginBlock;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int _tr) {
        this.tr = _tr;
    }

    public int getTrEnd() {
        return trEnd;
    }

    public void setTrEnd(int _trEnd) {
        this.trEnd = _trEnd;
    }

    public int getEndBlock() {
        return endBlock;
    }

    public void setEndBlock(int _endBlock) {
        this.endBlock = _endBlock;
    }

}
