package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.util.IdMap;

public final class NatDotOperation extends NatAbstractDotOperation {

    public NatDotOperation(int _o) {
        super(_o);
    }
    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        calculateDot(_nodes);
    }
}
