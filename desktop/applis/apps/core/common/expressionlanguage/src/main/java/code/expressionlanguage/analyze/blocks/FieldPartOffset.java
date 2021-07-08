package code.expressionlanguage.analyze.blocks;

public final class FieldPartOffset {
    private final String name;
    private final int off;

    public FieldPartOffset(String _part, int _offset) {
        name = _part;
        off = _offset;
    }

    public String getName() {
        return name;
    }

    public int getOff() {
        return off;
    }
}
