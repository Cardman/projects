package code.expressionlanguage.analyze.instr;

public final class PartOffset {
    private final String part;
    private final int offset;

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
