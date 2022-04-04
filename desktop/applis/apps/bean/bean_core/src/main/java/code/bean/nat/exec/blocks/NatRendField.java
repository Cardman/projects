package code.bean.nat.exec.blocks;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.util.CustList;

public final class NatRendField extends NatParentBlock {
    private final CustList<NatExecOperationNode> exps;

    public NatRendField(CustList<NatExecOperationNode> _res) {
        exps = _res;
    }

    public CustList<NatExecOperationNode> getExps() {
        return exps;
    }
}
