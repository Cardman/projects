package code.bean.nat.exec.opers;

import code.util.CustList;

public abstract class NatExecMethodOperation extends NatExecOperationNode {

    private final CustList<NatExecOperationNode> childrenNodes = new CustList<NatExecOperationNode>();
    protected NatExecMethodOperation(int _o) {
        super(_o);
    }

    public final void appendChild(NatExecOperationNode _child) {
        _child.setParent(this);
        childrenNodes.add(_child);
    }

    public final CustList<NatExecOperationNode> getChildrenNodes() {
        return childrenNodes;
    }

    public final NatExecOperationNode getFirstChild() {
        return getChildrenNodes().get(0);
    }
}
