package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatBlock;
import code.sml.RendReadWrite;

public class NatRendReadWrite extends RendReadWrite {

    private NatBlock read;

    public NatBlock getRead() {
        return read;
    }

    public void setRead(NatBlock _read) {
        read = _read;
    }

}
