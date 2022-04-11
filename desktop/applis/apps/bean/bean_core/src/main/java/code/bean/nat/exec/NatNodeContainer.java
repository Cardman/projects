package code.bean.nat.exec;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.formathtml.util.NodeContainer;
import code.util.CustList;

public final class NatNodeContainer extends NodeContainer {
    private CustList<NatExecOperationNode> opsWrite;
    private String varPrevName;
    private String varName;

    public CustList<NatExecOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<NatExecOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

    public String getVarPrevName() {
        return varPrevName;
    }

    public void setVarPrevName(String _varPrevName) {
        varPrevName = _varPrevName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        varName = _varName;
    }

}
