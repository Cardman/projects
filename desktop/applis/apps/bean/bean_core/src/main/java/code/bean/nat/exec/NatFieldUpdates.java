package code.bean.nat.exec;

import code.bean.nat.NatCaller;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.formathtml.util.FieldUpdates;
import code.util.CustList;

public final class NatFieldUpdates extends FieldUpdates {
    private CustList<NatExecOperationNode> opsRead = new CustList<NatExecOperationNode>();
    private NatCaller opsWrite;

    public CustList<NatExecOperationNode> getOpsRead() {
        return opsRead;
    }

    public void setOpsRead(CustList<NatExecOperationNode> _opsRead) {
        opsRead = _opsRead;
    }

    public NatCaller getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(NatCaller _opsWrite) {
        opsWrite = _opsWrite;
    }

}
