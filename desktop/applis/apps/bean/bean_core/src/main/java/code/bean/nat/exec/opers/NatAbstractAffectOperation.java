package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.util.IdMap;

public abstract class NatAbstractAffectOperation extends NatExecMethodOperation implements NatRendCalculableOperation {

    private NatExecOperationNode settable;

    protected NatAbstractAffectOperation(int _o) {
        super(_o);
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        calculateAffect(_nodes, _rendStack);
    }

    protected abstract void calculateAffect(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack);
    public void setup() {
        settable = tryGetSettable(this);
    }
    public static NatExecOperationNode tryGetSettable(NatExecMethodOperation _operation) {
        NatExecOperationNode root_ = _operation.getFirstChild();
        return ((NatExecMethodOperation) root_).getChildrenNodes().last();
    }

    public static NatExecOperationNode castDottedTo(NatExecOperationNode _root) {
        NatExecOperationNode elt_;
        if (!(_root instanceof NatAbstractDotOperation)) {
            elt_ = _root;
        } else {
            elt_ = ((NatExecMethodOperation) _root).getChildrenNodes().last();
        }
        return elt_;
    }

    public NatExecOperationNode getSettable() {
        return settable;
    }

}
