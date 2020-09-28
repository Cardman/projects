package code.formathtml.exec.blocks;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class ExecTextPart {

    private CustList<CustList<RendDynOperationNode>> opExp;

    private final StringList texts = new StringList();

    public CustList<CustList<RendDynOperationNode>> getOpExp() {
        return opExp;
    }

    public void setOpExp(CustList<CustList<RendDynOperationNode>> opExp) {
        this.opExp = opExp;
    }

    public StringList getTexts() {
        return texts;
    }

}
