package code.expressionlanguage.analyze.files;

public final class OffsetsBlock {

    private int offset;
    private int offsetTrim;

    public OffsetsBlock(){
    }
    public OffsetsBlock(int _offset, int _offsetTrim) {
        offset = _offset;
        offsetTrim = _offsetTrim;
    }
    public int getOffset() {
        return offset;
    }

    public int getOffsetTrim() {
        return offsetTrim;
    }
}
