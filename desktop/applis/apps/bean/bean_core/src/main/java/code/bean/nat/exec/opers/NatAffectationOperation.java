package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.*;
import code.util.IdMap;

public final class NatAffectationOperation extends NatAbstractAffectOperation {

    public NatAffectationOperation(int _o) {
        super(_o);
    }

    @Override
    protected void calculateAffect(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NatExecOperationNode right_ = getChildrenNodes().last();
        NaSt rightArg_ = getArgument(_nodes,right_);
        NaSt arg_ = calculateChSetting(getSettable(),_nodes, rightArg_, _rendStack);
        calcArg(_nodes, arg_);
    }

    static NaSt calculateChSetting(NatExecOperationNode _set,
                                       IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NaSt _right, NatRendStackCall _rendStackCall){
        return ((NatSettableFieldOperation) _set).calculateSetting(_nodes, _right, _rendStackCall);
    }
}
