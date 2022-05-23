package code.expressionlanguage.analyze.instr;

public final class OperatorOffsetLength {
    private final int offset;
    private final int length;
    private final int beginDel;

    public OperatorOffsetLength(int _of, int _len) {
        this(_of,_len,0);
    }

    public OperatorOffsetLength(int _of, int _len, int _begin) {
        this.offset = _of;
        this.length = _len;
        this.beginDel = _begin;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public int getBeginDel() {
        return beginDel;
    }
}
