package code.bean.nat.exec.blocks;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.util.CustList;

public final class NatRendOperationNodeListOff {
    private final CustList<NatExecOperationNode> list;

    public NatRendOperationNodeListOff(CustList<NatExecOperationNode> _list) {
        this.list = _list;
    }

    public CustList<NatExecOperationNode> getList() {
        return list;
    }
}
