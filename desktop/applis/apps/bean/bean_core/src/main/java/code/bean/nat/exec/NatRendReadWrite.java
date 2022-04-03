package code.bean.nat.exec;

import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.stacks.RendReadWrite;

public final class NatRendReadWrite extends RendReadWrite {

    private RendBlock read;
    private NatFormParts conf;

    public RendBlock getRead() {
        return read;
    }

    public void setRead(RendBlock _read) {
        read = _read;
    }

    public NatFormParts getConf() {
        return conf;
    }

    public void setConf(NatFormParts _conf) {
        conf = _conf;
    }
}
