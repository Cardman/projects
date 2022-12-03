package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class NatIdOperation extends NatExecMethodOperation implements NatRendCalculableOperation {

    public NatIdOperation(int _o) {
        super(_o);
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NatExecOperationNode o_ = getFirstChild();
        Struct a_ = getArgument(_nodes,o_);
        calcArg(_nodes, a_);
    }
}
