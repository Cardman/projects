package code.expressionlanguage.files;

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
    public void setOffset(int _offset) {
        offset = _offset;
    }
    public int getOffsetTrim() {
        return offsetTrim;
    }
    public void setOffsetTrim(int _offsetTrim) {
        offsetTrim = _offsetTrim;
    }
}
