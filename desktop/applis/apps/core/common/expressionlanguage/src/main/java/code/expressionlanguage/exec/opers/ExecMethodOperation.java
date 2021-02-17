package code.expressionlanguage.exec.opers;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;

public abstract class ExecMethodOperation extends ExecOperationNode {

    private final CustList<ExecOperationNode> childrenNodes = new CustList<ExecOperationNode>();

    protected ExecMethodOperation(ExecOperationContent _m) {
        super(_m);
    }

    protected ExecMethodOperation(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }

    public final void appendChild(ExecOperationNode _child) {
        _child.setParent(this);
        childrenNodes.add(_child);
    }

    public final CustList<ExecOperationNode> getChildrenNodes() {
        return childrenNodes;
    }

    @Override
    public final ExecOperationNode getFirstChild() {
        return ExecHelper.getFirstNode(this);
    }

}
