package code.formathtml.exec.blocks;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendField extends RendParentBlock {
    private int prepareOffset;
    private CustList<RendDynOperationNode> exps;

    public RendField(int _offsetTrim,CustList<RendDynOperationNode> _res, int _prepareOffset) {
        super(_offsetTrim);
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
