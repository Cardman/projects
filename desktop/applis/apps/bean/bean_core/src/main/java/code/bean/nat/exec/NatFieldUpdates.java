package code.bean.nat.exec;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.formathtml.util.FieldUpdates;
import code.util.CustList;

public final class NatFieldUpdates extends FieldUpdates {
    private String varName = "";
    private CustList<NatExecOperationNode> opsRead = new CustList<NatExecOperationNode>();
    private CustList<NatExecOperationNode> opsWrite = new CustList<NatExecOperationNode>();

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        varName = _varName;
    }

    public CustList<NatExecOperationNode> getOpsRead() {
        return opsRead;
    }

    public void setOpsRead(CustList<NatExecOperationNode> _opsRead) {
        opsRead = _opsRead;
    }

    public CustList<NatExecOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<NatExecOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

}
