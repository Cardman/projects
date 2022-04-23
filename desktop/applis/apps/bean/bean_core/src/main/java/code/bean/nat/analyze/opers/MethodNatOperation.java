package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;

public abstract class MethodNatOperation extends NatOperationNode {

    private NatOperationNode firstChild;

    private final StrTypes children;


    protected MethodNatOperation(int _index, int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        children = _op.getValNat();
    }

    public final void appendChild(NatOperationNode _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        NatOperationNode child_ = firstChild;
        while (true) {
            NatOperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<NatOperationNode> getChildrenNodes() {
        CustList<NatOperationNode> list_ = new CustList<NatOperationNode>();
        NatOperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    @Override
    public final NatOperationNode getFirstChild() {
        return firstChild;
    }

    public final StrTypes getChildren() {
        return children;
    }

}
