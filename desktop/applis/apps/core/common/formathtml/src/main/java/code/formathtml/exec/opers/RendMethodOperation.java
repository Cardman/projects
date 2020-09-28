package code.formathtml.exec.opers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;

public abstract class RendMethodOperation extends RendDynOperationNode {

    private RendDynOperationNode firstChild;

    public RendMethodOperation(ExecOperationContent _content) {
        super(_content);
    }
    public final void appendChild(RendDynOperationNode _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        RendDynOperationNode child_ = firstChild;
        while (true) {
            RendDynOperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<RendDynOperationNode> getChildrenNodes() {
        CustList<RendDynOperationNode> list_ = new CustList<RendDynOperationNode>();
        RendDynOperationNode firstChild_ = getFirstChild();
        RendDynOperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public final RendDynOperationNode getFirstChild() {
        return firstChild;
    }

}
