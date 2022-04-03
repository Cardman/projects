package code.formathtml.exec.stacks;

import code.formathtml.exec.DefFormParts;
import code.formathtml.exec.blocks.RendBlock;

public final class DefRendReadWrite extends RendReadWrite {

    private RendBlock read;
    private DefFormParts conf;

    public RendBlock getRead() {
        return read;
    }

    public void setRead(RendBlock _read) {
        read = _read;
    }

    public DefFormParts getConf() {
        return conf;
    }

    public void setConf(DefFormParts _conf) {
        conf = _conf;
    }
}
