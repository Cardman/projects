package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class ExecAnnotContent {
    private final CustList<ExecOperationNode> operations;
    private final StringList types;
    private final int offset;

    public ExecAnnotContent(CustList<ExecOperationNode> _operations, StringList _types, int _offset) {
        this.operations = _operations;
        this.types = _types;
        offset = _offset;
    }

    public CustList<ExecOperationNode> getOperations() {
        return operations;
    }

    public int getOffset() {
        return offset;
    }

    public StringList getTypes() {
        return types;
    }
}
