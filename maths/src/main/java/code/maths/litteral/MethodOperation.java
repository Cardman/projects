package code.maths.litteral;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

public abstract class MethodOperation extends OperationNode {

    private OperationNode firstChild;

    private NatTreeMap<Integer,String> children;

    public MethodOperation(String _el, int _index, StringMap<String> _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
        children = new NatTreeMap<Integer,String>();
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

    @Override
    public final OperationNode getFirstChild() {
        return firstChild;
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
    public final NatTreeMap<Integer, String> getChildren() {
        return children;
    }
}
