package code.expressionlanguage.exec.blocks;

public final class ExecTableOffsets {

    private final int separator;
    private final int separatorNext;
    private final int iteratorOffset;

    public ExecTableOffsets(int _s, int _n, int _i) {
        this.separator = _s;
        this.separatorNext = _n;
        this.iteratorOffset = _i;
    }

    public int getSeparatorNext() {
        return separatorNext;
    }

    public int getSeparator() {
        return separator;
    }

    public int getIteratorOffset() {
        return iteratorOffset;
    }
}
