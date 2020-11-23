package code.formathtml.exec.opers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;

public abstract class RendMethodOperation extends RendDynOperationNode {

    private CustList<RendDynOperationNode> childrenNodes = new CustList<RendDynOperationNode>();

    public RendMethodOperation(ExecOperationContent _content) {
        super(_content);
    }
    public final void appendChild(RendDynOperationNode _child) {
        _child.setParent(this);
        childrenNodes.add(_child);
    }

    public final CustList<RendDynOperationNode> getChildrenNodes() {
        return childrenNodes;
    }

    public final RendDynOperationNode getFirstChild() {
        return getFirstNode(this);
    }

}
