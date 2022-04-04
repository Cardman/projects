package code.bean.nat.exec;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.formathtml.util.NodeContainer;
import code.util.CustList;

public final class NatNodeContainer extends NodeContainer {
    private CustList<NatExecOperationNode> opsWrite;

    public CustList<NatExecOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<NatExecOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

}
