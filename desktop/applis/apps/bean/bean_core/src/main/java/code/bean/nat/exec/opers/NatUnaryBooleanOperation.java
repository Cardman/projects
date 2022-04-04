package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.IdMap;

public final class NatUnaryBooleanOperation extends NatExecMethodOperation implements NatRendCalculableOperation {
    public NatUnaryBooleanOperation(int _o) {
        super(_o);
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        Argument arg_ = getArgument(_nodes, getChildrenNodes().get(0));
        BooleanStruct o_ = NumParsers.convertToBoolean(arg_.getStruct());
        Argument a_ = new Argument(o_.neg());
        calcArg(_nodes,a_);
    }
}
