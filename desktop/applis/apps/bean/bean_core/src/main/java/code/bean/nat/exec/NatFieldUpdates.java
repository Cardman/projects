package code.bean.nat.exec;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.FieldUpdates;
import code.util.CustList;

public final class NatFieldUpdates extends FieldUpdates {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();

    public CustList<RendDynOperationNode> getOpsRead() {
        return opsRead;
    }

    public void setOpsRead(CustList<RendDynOperationNode> _opsRead) {
        opsRead = _opsRead;
    }

    public CustList<RendDynOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<RendDynOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

}
