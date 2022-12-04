package code.bean.nat.exec;

import code.bean.nat.*;
import code.bean.nat.*;
import code.sml.NodeContainer;
import code.util.CustList;

public final class NatNodeContainer extends NodeContainer {
    private NatCaller opsWrite;

    private CustList<NaSt> nats = new CustList<NaSt>();

    public NatCaller getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(NatCaller _opsWrite) {
        opsWrite = _opsWrite;
    }

    public CustList<NaSt> getAllObject() {
        return nats;
    }

    public void setAllObject(CustList<NaSt> _allObject) {
        nats = _allObject;
    }

}
