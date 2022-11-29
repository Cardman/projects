package code.bean.nat.exec;

import code.bean.nat.NatCaller;
import code.formathtml.util.NodeContainer;

public final class NatNodeContainer extends NodeContainer {
    private NatCaller opsWrite;

    public NatCaller getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(NatCaller _opsWrite) {
        opsWrite = _opsWrite;
    }

}
