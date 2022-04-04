package code.bean.nat.exec.blocks;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.util.CustList;

public abstract class NatRendCondition extends NatParentBlock {


    private final NatRendOperationNodeListOff condition;

    NatRendCondition(CustList<NatExecOperationNode> _op) {
        condition = new NatRendOperationNodeListOff(_op);
    }

    NatRendOperationNodeListOff getCondition() {
        return condition;
    }

}
