package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatBlock;
import code.formathtml.exec.stacks.RendReadWrite;

public final class NatRendReadWrite extends RendReadWrite {

    private NatBlock read;
    private NatFormParts conf;

    public NatBlock getRead() {
        return read;
    }

    public void setRead(NatBlock _read) {
        read = _read;
    }

    public NatFormParts getConf() {
        return conf;
    }

    public void setConf(NatFormParts _conf) {
        conf = _conf;
    }
}
