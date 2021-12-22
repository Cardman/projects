package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCompoundAffectationNatOperation extends ExecCompoundAffectationOperation {


    public ExecCompoundAffectationNatOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ImplicitMethods _converter, StringList _names) {
        super(_opCont, _operatorContent, _converter, _names, false);
    }


    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        Argument leftArg_ = getFirstArgument(_nodes,this);
        Argument rightArg_ = getLastArgument(_nodes,this);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_nodes,this);
        ImplicitMethods implicits_ = getConverter();
        int indexImplicit_ = pairBefore_.getIndexImplicitCompound();
        if (ImplicitMethods.isValidIndex(implicits_,indexImplicit_)) {
            String tres_ = implicits_.get(indexImplicit_).getFct().getImportedParametersTypes().first();
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _conf.getStandards().getPrimTypes());
            Argument res_ = ExecNumericOperation.calculateAffect(leftArg_, _conf, rightArg_, getOperatorContent().getOper(), cast_, _stack);
            pairBefore_.setIndexImplicitCompound(processConverter(_conf,res_,implicits_,indexImplicit_, _stack));
            return;
        }
        Argument arg_ = calculateCompoundSetting(_nodes, _conf, rightArg_, _stack);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    private Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _rightArg, StackCall _stackCall) {
        Argument arg_ = null;
        ExecOperationNode settable_ = getSettable();
        if (settable_ instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable_ instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable_ instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable_ instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable_ instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }

}
