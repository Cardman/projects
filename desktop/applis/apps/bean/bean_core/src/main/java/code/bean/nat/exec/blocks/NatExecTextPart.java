package code.bean.nat.exec.blocks;

import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class NatExecTextPart extends ExecTextPart {

    private CustList<CustList<RendDynOperationNode>> opExp;

    public CustList<CustList<RendDynOperationNode>> getOpExp() {
        return opExp;
    }

    public void setOpExp(CustList<CustList<RendDynOperationNode>> _opExp) {
        this.opExp = _opExp;
    }

}
