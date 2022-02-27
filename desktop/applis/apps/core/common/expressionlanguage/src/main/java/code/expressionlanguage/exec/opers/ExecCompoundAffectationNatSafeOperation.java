package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ExecCompoundAffectationNatSafeOperation extends ExecCompoundAffectationOperation {

    public ExecCompoundAffectationNatSafeOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ImplicitMethods _converter, StringList _names) {
        super(_opCont, _operatorContent, _converter, _names, false);
    }


    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        Argument leftArg_ = getFirstArgument(_nodes,this);
        Argument rightArg_ = getLastArgument(_nodes,this);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        if (StringUtil.quickEq(getOperatorContent().getOper(), AbsBk.NULL_EQ_SHORT) && !leftArg_.isNull()) {
            pair_.setIndexImplicitConv(-1);
            pair_.setEndCalculate(true);
            setSimpleArgument(leftArg_, _conf, _nodes, _stack);
            return;
        }
        Argument arg_ = calculateCompoundSetting(_nodes, _conf, rightArg_, _stack);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    private Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _rightArg, StackCall _stackCall) {
        Argument arg_ = null;
        ExecOperationNode settable_ = getSettable();
        StringList names_ = getNames();
        if (settable_ instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)settable_).calculateCompoundSetting(_nodes, _conf, _rightArg, names_, _stackCall);
        }
        if (settable_ instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)settable_).calculateCompoundSetting(_nodes, _conf, _rightArg, names_, _stackCall);
        }
        if (settable_ instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)settable_).calculateCompoundSetting(_nodes, _conf, _rightArg, names_, _stackCall);
        }
        if (settable_ instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)settable_).calculateCompoundSetting(_nodes, _conf, _rightArg, names_, _stackCall);
        }
        if (settable_ instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)settable_).calculateCompoundSetting(_nodes, _conf, _rightArg, names_, _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }

}
