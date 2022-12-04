package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.util.IdMap;

public final class NatUnaryBooleanOperation extends NatExecMethodOperation implements NatRendCalculableOperation {
    public NatUnaryBooleanOperation(int _o) {
        super(_o);
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NaSt arg_ = getArgument(_nodes, getChildrenNodes().get(0));
        NaBoSt o_ = NaPa.convertToBoolean(arg_);
        NaBoSt a_ = o_.neg();
        calcArg(_nodes,a_);
    }
}
