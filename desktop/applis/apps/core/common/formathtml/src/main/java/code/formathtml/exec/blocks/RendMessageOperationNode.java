package code.formathtml.exec.blocks;

import code.formathtml.common.ComMessageOperationNode;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendMessageOperationNode extends ComMessageOperationNode {
    private final CustList<RendDynOperationNode> opers;
    public RendMessageOperationNode(CustList<RendDynOperationNode> _r, boolean _q, boolean _e, String _a) {
        super(_q, _e, _a);
        opers = _r;
    }

    public CustList<RendDynOperationNode> getOpers() {
        return opers;
    }
}
