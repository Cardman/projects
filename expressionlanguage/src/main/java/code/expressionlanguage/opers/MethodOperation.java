package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.util.CustList;
import code.util.NatTreeMap;

public abstract class MethodOperation extends OperationNode {

    private OperationNode firstChild;

    private NatTreeMap<Integer,String> children;

    public MethodOperation(int _index, ContextEl _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
        children = new NatTreeMap<Integer,String>();
        calculateChildren();
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
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }
    final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> chidren_ = new CustList<OperationNode>();
        for (OperationNode o: ElUtil.getDirectChildren(this)) {
            chidren_.add(o);
        }
        return chidren_;
    }

    abstract void calculateChildren();

    @Override
    final boolean isFirstChild() {
        return getIndexChild() == CustList.FIRST_INDEX;
    }
    @Override
    public final OperationNode getFirstChild() {
        return firstChild;
    }

    public final NatTreeMap<Integer, String> getChildren() {
        return children;
    }
}
