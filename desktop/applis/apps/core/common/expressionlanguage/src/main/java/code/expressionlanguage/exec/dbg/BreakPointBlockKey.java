package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.exec.blocks.ExecFileBlock;

public final class BreakPointBlockKey {
    private final ExecFileBlock file;
    private final int offset;

    public BreakPointBlockKey(ExecFileBlock _file, int _offset) {
        this.file = _file;
        this.offset = _offset;
    }
    public boolean match(BreakPointBlockKey _b) {
        return match(_b.file,_b.offset);
    }
    public boolean match(ExecFileBlock _file, int _offset) {
        return file == _file && offset == _offset;
    }
    public boolean matchRow(ExecFileBlock _file, FileMetrics _ana, int _row) {
        return file == _file && _ana.getRowFile(offset) == _row;
    }
}
