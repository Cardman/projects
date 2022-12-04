package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.*;
import code.util.IdMap;

public final class NatInternGlobalOperation  extends NatExecLeafOperation implements NatRendCalculableOperation {
    public NatInternGlobalOperation(int _o) {
        super(_o);
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NaSt struct_ = _rendStack.getInternGlobal();
        calcArg(_nodes,struct_);
    }
}
