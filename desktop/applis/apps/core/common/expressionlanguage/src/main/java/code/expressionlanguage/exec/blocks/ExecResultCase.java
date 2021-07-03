package code.expressionlanguage.exec.blocks;

public final class ExecResultCase {
    private final ExecBracedBlock block;
    private final int index;

    public ExecResultCase(ExecBracedBlock _block, int _index) {
        this.block = _block;
        this.index = _index;
    }

    public static ExecBracedBlock block(ExecResultCase _instance) {
        if (_instance == null) {
            return null;
        }
        return _instance.getBlock();
    }
    public ExecBracedBlock getBlock() {
        return block;
    }

    public int getIndex() {
        return index;
    }
}
