package code.formathtml.exec.blocks;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class DefExecTextPart extends ExecTextPart {

    private CustList<CustList<RendDynOperationNode>> opExp;

    public CustList<CustList<RendDynOperationNode>> getOpExp() {
        return opExp;
    }

    public void setOpExp(CustList<CustList<RendDynOperationNode>> _opExp) {
        this.opExp = _opExp;
    }

}
