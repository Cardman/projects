package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class NatInternGlobalOperation  extends NatExecLeafOperation implements NatRendCalculableOperation {
    public NatInternGlobalOperation(int _o) {
        super(_o);
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        Struct struct_ = _rendStack.getInternGlobal();
        Argument arg_ = new Argument(struct_);
        calcArg(_nodes,arg_);
    }
}
