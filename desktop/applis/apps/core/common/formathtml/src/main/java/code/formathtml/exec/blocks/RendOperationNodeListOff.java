package code.formathtml.exec.blocks;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendOperationNodeListOff {
    private final CustList<RendDynOperationNode> list;
    private final int offset;

    public RendOperationNodeListOff(CustList<RendDynOperationNode> _list, int _offset) {
        this.list = _list;
        this.offset = _offset;
    }

    public int getOffset() {
        return offset;
    }

    public CustList<RendDynOperationNode> getList() {
        return list;
    }
}
