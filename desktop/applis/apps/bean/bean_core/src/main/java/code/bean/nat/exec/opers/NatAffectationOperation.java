package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class NatAffectationOperation extends NatAbstractAffectOperation {

    public NatAffectationOperation(int _o) {
        super(_o);
    }

    @Override
    protected void calculateAffect(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NatExecOperationNode right_ = getChildrenNodes().last();
        Struct rightArg_ = getArgument(_nodes,right_);
        Struct arg_ = calculateChSetting(getSettable(),_nodes, rightArg_, _rendStack);
        calcArg(_nodes, arg_);
    }

    static Struct calculateChSetting(NatExecOperationNode _set,
                                       IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, Struct _right, NatRendStackCall _rendStackCall){
        return ((NatSettableFieldOperation) _set).calculateSetting(_nodes, _right, _rendStackCall);
    }
}
