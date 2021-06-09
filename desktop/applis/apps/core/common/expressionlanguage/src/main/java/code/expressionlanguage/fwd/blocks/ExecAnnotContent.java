package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class ExecAnnotContent {
    private final CustList<ExecOperationNode> operations;
    private final StringList types;

    public ExecAnnotContent(CustList<ExecOperationNode> _operations, StringList _types) {
        this.operations = _operations;
        this.types = _types;
    }

    public CustList<ExecOperationNode> getOperations() {
        return operations;
    }

    public StringList getTypes() {
        return types;
    }
}
