package code.bean.nat.exec;

import code.bean.nat.*;
import code.sml.NodeContainer;

public final class NatNodeContainer extends NodeContainer {
    private NatCaller opsWrite;

    private NaSt nats;

    public NatCaller getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(NatCaller _opsWrite) {
        opsWrite = _opsWrite;
    }

    public NaSt getAllObject() {
        return nats;
    }

    public void setAllObject(NaSt _allObject) {
        nats = _allObject;
    }

}
