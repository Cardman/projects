package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class LinkageStackElementIn {
    private AbsBk block;
    private int indexLoop;
    private int indexAnnotationGroup=-1;
    private int indexAnnotationGroupLook=-1;
    private int indexAnnotation;
    private int indexAnnotationLook;
    private int beginBlock;
    private int endBlock;
    private int fieldLength;

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
    public void offsets(int _begin, int _end, int _fieldLength) {
        setBeginBlock(_begin);
        setEndBlock(_end);
        setFieldLength(_fieldLength);
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

    public int getEndBlock() {
        return endBlock;
    }

    public void setEndBlock(int _endBlock) {
        this.endBlock = _endBlock;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int _fieldLength) {
        this.fieldLength = _fieldLength;
    }
}
