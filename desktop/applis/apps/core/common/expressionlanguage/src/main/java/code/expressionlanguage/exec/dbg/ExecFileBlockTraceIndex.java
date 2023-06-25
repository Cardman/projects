package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.blocks.ExecFileBlock;

public final class ExecFileBlockTraceIndex {
    private final ExecFileBlock file;
    private final int index;

    public ExecFileBlockTraceIndex(ExecFileBlock _f, int _i) {
        this.file = _f;
        this.index = _i;
    }

    public boolean match(ExecFileBlock _f, int _i) {
        return getFile() == _f && getIndex() == _i;
    }

    public ExecFileBlock getFile() {
        return file;
    }

    public int getIndex() {
        return index;
    }
}
