package code.formathtml.fwd;

import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.exec.blocks.RendParentBlock;

public final class RendAnaExec {
    private AnaRendBlock read;
    private RendParentBlock write;

    public RendAnaExec(AnaRendBlock _read, RendParentBlock _write) {
        this.read = _read;
        this.write = _write;
    }

    public AnaRendBlock getRead() {
        return read;
    }

    public void setRead(AnaRendBlock _read) {
        this.read = _read;
    }

    public RendParentBlock getWrite() {
        return write;
    }

    public void setWrite(RendParentBlock _write) {
        this.write = _write;
    }
}
