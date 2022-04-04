package code.bean.nat.exec.blocks;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.util.CustList;

public final class NatRendForEachIterable extends NatRendAbstractForEachLoop {
    public NatRendForEachIterable(String _variable, CustList<NatExecOperationNode> _res) {
        super(_variable, _res);
    }

}
