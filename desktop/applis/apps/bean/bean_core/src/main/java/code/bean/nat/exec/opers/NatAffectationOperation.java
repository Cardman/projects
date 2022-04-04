package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.Argument;
import code.util.IdMap;

public final class NatAffectationOperation extends NatAbstractAffectOperation {

    public NatAffectationOperation(int _o) {
        super(_o);
    }

    @Override
    protected void calculateAffect(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NatExecOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = getArgument(_nodes,right_);
        Argument arg_ = calculateChSetting(getSettable(),_nodes, rightArg_, _rendStack);
        calcArg(_nodes, arg_);
    }

    static Argument calculateChSetting(NatExecOperationNode _set,
                                       IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, Argument _right, NatRendStackCall _rendStackCall){
        Argument arg_ = ((NatSettableFieldOperation) _set).calculateSetting(_nodes, _right, _rendStackCall);
        return Argument.getNullableValue(arg_);
    }
}
