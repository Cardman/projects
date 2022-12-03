package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class NatUnaryBooleanOperation extends NatExecMethodOperation implements NatRendCalculableOperation {
    public NatUnaryBooleanOperation(int _o) {
        super(_o);
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        Struct arg_ = getArgument(_nodes, getChildrenNodes().get(0));
        BooleanStruct o_ = NumParsers.convertToBoolean(arg_);
        BooleanStruct a_ = o_.neg();
        calcArg(_nodes,a_);
    }
}
