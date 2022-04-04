package code.bean.nat.fwd;

import code.bean.nat.analyze.blocks.NatAnaRendBlock;
import code.bean.nat.exec.blocks.NatParentBlock;

public final class NatAnaExec {
    private NatAnaRendBlock readNat;
    private NatParentBlock writeNat;

    public NatAnaExec(NatAnaRendBlock _read, NatParentBlock _write) {
        this.readNat = _read;
        this.writeNat = _write;
    }

    public NatAnaRendBlock getReadNat() {
        return readNat;
    }

    public void setReadNat(NatAnaRendBlock _read) {
        this.readNat = _read;
    }

    public NatParentBlock getWriteNat() {
        return writeNat;
    }

    public void setWriteNat(NatParentBlock _write) {
        this.writeNat = _write;
    }
}
