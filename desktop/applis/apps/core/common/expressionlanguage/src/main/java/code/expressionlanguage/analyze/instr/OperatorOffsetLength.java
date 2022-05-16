package code.expressionlanguage.analyze.instr;

public final class OperatorOffsetLength {
    private final int offset;
    private final int length;

    public OperatorOffsetLength(int _of, int _len) {
        this.offset = _of;
        this.length = _len;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }
}
