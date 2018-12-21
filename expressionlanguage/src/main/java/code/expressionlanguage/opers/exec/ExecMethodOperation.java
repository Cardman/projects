package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.opers.MethodOperation;
import code.util.CustList;

public abstract class ExecMethodOperation extends ExecOperationNode implements ReductibleOperable {

    private ExecOperationNode firstChild;

    public ExecMethodOperation(MethodOperation _m) {
        super(_m);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<ExecOperationNode> children_ = getChildrenNodes();
        for (ExecOperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }
    public void quickCalculate(Analyzable _conf) {
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

    @Override
    public final CustList<Operable> getChildrenOperable() {
        CustList<Operable> list_ = new CustList<Operable>();
        ExecOperationNode firstChild_ = getFirstChild();
        ExecOperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public final CustList<ExecOperationNode> getChildrenNodes() {
        CustList<ExecOperationNode> list_ = new CustList<ExecOperationNode>();
        ExecOperationNode firstChild_ = getFirstChild();
        ExecOperationNode elt_ = firstChild_;
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
