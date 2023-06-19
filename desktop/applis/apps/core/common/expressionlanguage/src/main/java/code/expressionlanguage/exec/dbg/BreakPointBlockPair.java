package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.exec.blocks.ExecFileBlock;

public final class BreakPointBlockPair {
    private final ExecFileBlock file;
    private final int offset;
    private final BreakPoint value;

    public BreakPointBlockPair(ExecFileBlock _file, int _offset, BreakPoint _v) {
        this.file = _file;
        this.offset = _offset;
        this.value = _v;
    }
    public boolean match(BreakPointBlockPair _b) {
        return match(_b.file,_b.offset);
    }
    public boolean match(ExecFileBlock _file, int _offset) {
        return file == _file && offset == _offset;
    }
    public boolean matchRow(ExecFileBlock _file, FileMetrics _ana, int _row) {
        return file == _file && _ana.getRowFile(offset) == _row;
    }

    public ExecFileBlock getFile() {
        return file;
    }

    public int getOffset() {
        return offset;
    }

    public BreakPoint getValue() {
        return value;
    }
}
