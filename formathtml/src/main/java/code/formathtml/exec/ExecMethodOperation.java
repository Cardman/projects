package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.opers.MethodOperation;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.util.CustList;

public abstract class ExecMethodOperation extends ExecDynOperationNode implements ReductibleOperable, ParentOperable {

    private ExecDynOperationNode firstChild;

    public ExecMethodOperation(MethodOperation _m) {
        super(_m);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<ExecDynOperationNode> children_ = getChildrenNodes();
        for (ExecDynOperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }
    public void quickCalculate(Analyzable _conf) {
    }

    public final void appendChild(ExecDynOperationNode _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        ExecDynOperationNode child_ = firstChild;
        while (true) {
            ExecDynOperationNode sibling_ = child_.getNextSibling();
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
        ExecDynOperationNode firstChild_ = getFirstChild();
        ExecDynOperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public final CustList<ExecDynOperationNode> getChildrenNodes() {
        CustList<ExecDynOperationNode> list_ = new CustList<ExecDynOperationNode>();
        ExecDynOperationNode firstChild_ = getFirstChild();
        ExecDynOperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    @Override
    public final ExecDynOperationNode getFirstChild() {
        return firstChild;
    }
}
