package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.blocks.ExecFileBlock;

public final class BreakPointBlockKey {
    private final ExecFileBlock file;
    private final int offset;
    private final int iterate;

    public BreakPointBlockKey(ExecFileBlock _file, int _offset, int _i) {
        this.file = _file;
        this.offset = _offset;
        this.iterate = _i;
    }
    public boolean match(BreakPointBlockKey _b) {
        return match(_b.file,_b.offset,_b.iterate);
    }
    public boolean match(ExecFileBlock _file, int _offset, int _iterate) {
        return file == _file && offset == _offset && iterate == _iterate;
    }

}
