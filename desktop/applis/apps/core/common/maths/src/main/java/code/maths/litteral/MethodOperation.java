package code.maths.litteral;
import code.util.CustList;

public abstract class MethodOperation extends OperationNode {

    private OperationNode firstChild;

    private final StrTypes children;

    protected MethodOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        children = new StrTypes();
        calculateChildren();
    }

    final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> chidren_ = new CustList<OperationNode>();
        for (OperationNode o: MathUtil.getDirectChildren(this)) {
            chidren_.add(o);
        }
        return chidren_;
    }

    abstract void calculateChildren();

    public final OperationNode getFirstChild() {
        return firstChild;
    }

    public final void appendChild(OperationNode _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        tryAppend(_child);
    }

    private void tryAppend(OperationNode _child) {
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

    public final StrTypes getChildren() {
        return children;
    }
}
