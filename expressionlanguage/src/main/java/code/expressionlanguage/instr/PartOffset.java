package code.expressionlanguage.instr;

public final class PartOffset {
    private String part;
    private int offset;

    public PartOffset(String _part, int _offset) {
        part = _part;
        offset = _offset;
    }

    public String getPart() {
        return part;
    }

    public int getOffset() {
        return offset;
    }
}
