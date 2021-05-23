package code.expressionlanguage.fwd.blocks;

public final class ExecTypeFunctionPair {
    private final ExecTypeFunction read;
    private final ExecTypeFunction write;

    public ExecTypeFunctionPair(ExecTypeFunction _read, ExecTypeFunction _write) {
        this.read = _read;
        this.write = _write;
    }

    public ExecTypeFunction getRead() {
        return read;
    }

    public ExecTypeFunction getWrite() {
        return write;
    }
}
