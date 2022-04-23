package code.maths.litteral;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;

public abstract class MethodMbOperation extends MbOperationNode {

    private final StrTypes opers;
    private MbOperationNode firstChild;

    private final StrTypes children;

    protected MethodMbOperation(int _index, int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m);
        children = _op.getValues();
        opers = _op.getOperators();
    }

    final CustList<MbOperationNode> getChildrenNodes() {
        CustList<MbOperationNode> list_ = new CustList<MbOperationNode>();
        MbOperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public final MbOperationNode getFirstChild() {
        return firstChild;
    }

    public final void appendChild(MbOperationNode _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        tryAppend(_child);
    }

    private void tryAppend(MbOperationNode _child) {
        MbOperationNode child_ = firstChild;
        while (true) {
            MbOperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final StrTypes getChildren() {
        return children;
    }

    public StrTypes getOpers() {
        return opers;
    }
}
