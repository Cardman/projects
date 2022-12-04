package code.bean.nat.exec;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.sml.NodeContainer;
import code.util.CustList;

public final class NatNodeContainer extends NodeContainer {
    private NatCaller opsWrite;

    private CustList<Struct> nats = new CustList<Struct>();

    public NatCaller getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(NatCaller _opsWrite) {
        opsWrite = _opsWrite;
    }

    public CustList<Struct> getAllObject() {
        return nats;
    }

    public void setAllObject(CustList<Struct> _allObject) {
        nats = _allObject;
    }

}
