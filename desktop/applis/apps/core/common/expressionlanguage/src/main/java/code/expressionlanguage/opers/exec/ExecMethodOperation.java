package code.expressionlanguage.opers.exec;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;

public abstract class ExecMethodOperation extends ExecOperationNode implements ParentOperable {

    private ExecOperationNode firstChild;

    public ExecMethodOperation(OperationNode _m) {
        super(_m);
    }

    public ExecMethodOperation(int _indexChild, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }

    public final void appendChild(ExecOperationNode _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        ExecOperationNode child_ = firstChild;
        while (true) {
            ExecOperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<ExecOperationNode> getChildrenNodes() {
        CustList<ExecOperationNode> list_ = new CustList<ExecOperationNode>();
        ExecOperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    @Override
    public final ExecOperationNode getFirstChild() {
        return firstChild;
    }

}
