package code.expressionlanguage.methods.util;
import code.expressionlanguage.opers.OperationNode;
import code.util.CustList;

public final class ExpLanguages {

    private final CustList<OperationNode> left;

    private final CustList<OperationNode> right;

    public ExpLanguages(CustList<OperationNode> _left, CustList<OperationNode> _right) {
        left = _left;
        right = _right;
    }

    public CustList<OperationNode> getLeft() {
        return left;
    }

    public CustList<OperationNode> getRight() {
        return right;
    }
}
