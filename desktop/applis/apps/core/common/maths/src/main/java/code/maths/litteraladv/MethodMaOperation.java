package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.CustList;

public abstract class MethodMaOperation extends MaOperationNode {

    private MaOperationNode first;

    private final StrTypes chs;
    private final StrTypes ops;

    protected MethodMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m);
        chs = _op.getParts();
        ops = _op.getOpers();
    }

    final CustList<MaOperationNode> getChildren() {
        CustList<MaOperationNode> list_ = new CustList<MaOperationNode>();
        MaOperationNode elt_ = getFirst();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNext();
        }
        return list_;
    }

    public final MaOperationNode getFirst() {
        return first;
    }

    public final void append(MaOperationNode _child) {
        if (first == null) {
            first = _child;
            return;
        }
        tryAppend(_child);
    }

    private void tryAppend(MaOperationNode _child) {
        MaOperationNode child_ = first;
        while (true) {
            MaOperationNode sibling_ = child_.getNext();
            if (sibling_ == null) {
                child_.setNext(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public StrTypes getOps() {
        return ops;
    }

    public final StrTypes getChs() {
        return chs;
    }
}
