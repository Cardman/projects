package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.util.CustList;
import code.util.*;
import code.util.StringMap;

public abstract class MethodOperation extends OperationNode implements ReductibleOperable, ParentOperable {

    private OperationNode firstChild;

    private IntTreeMap<String> children;

    public MethodOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        children = new IntTreeMap<String>();
        calculateChildren();
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        tryCalculateNode(this, _conf);
    }
    public static void tryCalculateNode(MethodOperation _par, ContextEl _conf) {
        CustList<OperationNode> children_ = _par.getChildrenNodes();
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        _par.quickCalculate(_conf);
    }
    public void quickCalculate(ContextEl _conf) {
    }

    public final void appendChild(OperationNode _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        OperationNode child_ = firstChild;
        while (true) {
            OperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    abstract void calculateChildren();

    @Override
    public final OperationNode getFirstChild() {
        return firstChild;
    }

    public final IntTreeMap< String> getChildren() {
        return children;
    }

}
