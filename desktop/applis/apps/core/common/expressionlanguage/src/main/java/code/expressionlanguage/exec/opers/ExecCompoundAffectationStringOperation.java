package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;

public final class ExecCompoundAffectationStringOperation extends ExecCompoundAffectationOperation {


    public ExecCompoundAffectationStringOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent) {
        super(_opCont, _operatorContent, null);
    }


    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument rightArg_ = getLastArgument(_nodes,this);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        Argument arg_ = calculateCompoundString(_nodes, _conf, rightArg_, _stack);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    private Argument calculateCompoundString(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _rightArg, StackCall _stackCall) {
        Argument arg_ = null;
        ExecOperationNode settable_ = getSettable();
        if (settable_ instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)settable_).calculateCompoundString(_nodes, _conf, _rightArg, _stackCall);
        }
        if (settable_ instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)settable_).calculateCompoundString(_nodes, _conf, _rightArg,_stackCall);
        }
        if (settable_ instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)settable_).calculateCompoundString(_nodes, _conf, _rightArg, _stackCall);
        }
        if (settable_ instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)settable_).calculateCompoundString(_nodes, _conf, _rightArg, _stackCall);
        }
        if (settable_ instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)settable_).calculateCompoundString(_nodes, _conf, _rightArg, _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }

}
