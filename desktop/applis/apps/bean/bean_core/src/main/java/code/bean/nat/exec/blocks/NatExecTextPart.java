package code.bean.nat.exec.blocks;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.formathtml.exec.blocks.ExecTextPart;
import code.util.CustList;

public final class NatExecTextPart extends ExecTextPart {

    private CustList<CustList<NatExecOperationNode>> opExp;

    public CustList<CustList<NatExecOperationNode>> getOpExp() {
        return opExp;
    }

    public void setOpExp(CustList<CustList<NatExecOperationNode>> _opExp) {
        this.opExp = _opExp;
    }

}
