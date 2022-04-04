package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.util.IdMap;

public abstract class NatAbstractDotOperation extends NatExecMethodOperation implements NatRendCalculableOperation {
    protected NatAbstractDotOperation(int _o) {
        super(_o);
    }

    public void calculateDot(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes) {
        NatExecOperationNode last_ = getChildrenNodes().last();
        calcArg(_nodes, getArgument(_nodes,last_));
    }
}
