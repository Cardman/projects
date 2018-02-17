package code.expressionlanguage.opers;

import code.util.CustList;
import code.util.NatTreeMap;

@SuppressWarnings("static-method")
public abstract class MethodOperationAdv extends OperationNodeAdv {

    private OperationNode firstChild;

    private NatTreeMap<Integer,String> children;

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
    final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> chidren_ = new CustList<OperationNode>();
        return chidren_;
    }

    abstract void calculateChildren();
    final int lookOnlyForVarArg() {
//        OperationNode first_ = getFirstChild();
//        if (first_ == null) {
//            return -1;
//        }
//        if (!first_.isVararg()) {
//            return -1;
//        }
        CustList<OperationNode> ch_ = getChildrenNodes();
        int firstOpt_ = 0;
        int len_ = ch_.size();
        for (int i = 1; i < len_;i++) {
            if (ch_.get(i).isFirstOptArg()) {
                firstOpt_ = i;
                break;
            }
        }
        return firstOpt_;
    }
    public final NatTreeMap<Integer, String> getChildren() {
        return children;
    }
}
