package code.bean.nat.exec.blocks;

import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class NatRendField extends RendParentBlock {
    private int prepareOffset;
    private CustList<RendDynOperationNode> exps;

    public NatRendField(CustList<RendDynOperationNode> _res, int _prepareOffset) {
        prepareOffset = _prepareOffset;
        exps = _res;
    }

    public int getPrepareOffset() {
        return prepareOffset;
    }

    public CustList<RendDynOperationNode> getExps() {
        return exps;
    }
}
