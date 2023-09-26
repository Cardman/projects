package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecOperationNodeListOff {
    private final CustList<ExecOperationNode> list;
    private final int offset;
    private final int end;

    public ExecOperationNodeListOff(CustList<ExecOperationNode> _list, int _offset, int _endAll) {
        this.list = _list;
        this.offset = _offset;
        this.end = _endAll;
    }

    public int getEnd() {
        return end;
    }

    public CustList<ExecOperationNode> getList() {
        return list;
    }

    public int getOffset() {
        return offset;
    }
}
