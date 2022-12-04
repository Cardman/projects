package code.bean.nat.exec;

import code.bean.nat.*;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.sml.FieldUpdates;
import code.util.CustList;

public final class NatFieldUpdates extends FieldUpdates {
    private CustList<NatExecOperationNode> opsRead = new CustList<NatExecOperationNode>();
    private NatCaller opsWrite;
    private boolean rad;

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

    public boolean isRad() {
        return rad;
    }

    public void setRad(boolean _r) {
        this.rad = _r;
    }
}
