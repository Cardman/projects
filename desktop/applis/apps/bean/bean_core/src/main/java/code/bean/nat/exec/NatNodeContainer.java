package code.bean.nat.exec;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.NodeContainer;
import code.util.CustList;

public final class NatNodeContainer extends NodeContainer {
    private CustList<RendDynOperationNode> opsWrite;

    public CustList<RendDynOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<RendDynOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

}
