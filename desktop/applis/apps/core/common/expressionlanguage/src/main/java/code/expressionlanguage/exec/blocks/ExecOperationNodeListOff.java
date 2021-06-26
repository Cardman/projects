package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public class ExecOperationNodeListOff {
    private final CustList<ExecOperationNode> list;
    private final int offset;

    public ExecOperationNodeListOff(CustList<ExecOperationNode> _list, int _offset) {
        this.list = _list;
        this.offset = _offset;
    }

    public CustList<ExecOperationNode> getList() {
        return list;
    }

    public int getOffset() {
        return offset;
    }
}
