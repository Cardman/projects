package code.formathtml.fwd;

import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.exec.blocks.RendParentBlockInt;

public final class RendAnaExec {
    private AnaRendBlock read;
    private RendParentBlockInt write;

    public RendAnaExec(AnaRendBlock _read, RendParentBlockInt _write) {
        this.read = _read;
        this.write = _write;
    }

    public AnaRendBlock getRead() {
        return read;
    }

    public void setRead(AnaRendBlock _read) {
        this.read = _read;
    }

    public RendParentBlockInt getWrite() {
        return write;
    }

    public void setWrite(RendParentBlockInt _write) {
        this.write = _write;
    }
}
