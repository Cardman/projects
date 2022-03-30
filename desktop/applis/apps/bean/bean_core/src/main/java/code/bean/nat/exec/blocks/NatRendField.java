package code.bean.nat.exec.blocks;

import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class NatRendField extends RendParentBlock {
    private final CustList<RendDynOperationNode> exps;

    public NatRendField(CustList<RendDynOperationNode> _res) {
        exps = _res;
    }

    public CustList<RendDynOperationNode> getExps() {
        return exps;
    }
}
