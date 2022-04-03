package code.bean.nat.fwd;

import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.exec.blocks.RendParentBlock;

public final class NatAnaExec {
    private AnaRendBlock readNat;
    private RendParentBlock writeNat;

    public NatAnaExec(AnaRendBlock _read, RendParentBlock _write) {
        this.readNat = _read;
        this.writeNat = _write;
    }

    public AnaRendBlock getReadNat() {
        return readNat;
    }

    public void setReadNat(AnaRendBlock _read) {
        this.readNat = _read;
    }

    public RendParentBlock getWriteNat() {
        return writeNat;
    }

    public void setWriteNat(RendParentBlock _write) {
        this.writeNat = _write;
    }
}
