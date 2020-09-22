package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public abstract class ReachMethodOperation extends ReachOperationNode {
    private ReachOperationNode firstChild;
    ReachMethodOperation(OperationNode _info) {
        super(_info);
    }

    static boolean allAreDefined(ReachMethodOperation _par) {
        CustList<ReachOperationNode> children_ = _par.getChildrenNodes();
        for (ReachOperationNode o: children_) {
            if (o.getArgument() == null) {
                return false;
            }
        }
        return true;
    }
    public final void appendChild(ReachOperationNode _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        ReachOperationNode child_ = firstChild;
        while (true) {
            ReachOperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<ReachOperationNode> getChildrenNodes() {
        CustList<ReachOperationNode> list_ = new CustList<ReachOperationNode>();
        ReachOperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    @Override
    public final ReachOperationNode getFirstChild() {
        return firstChild;
    }

}
