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
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringList;

public final class ExecSemiAffectationNatOperation extends ExecSemiAffectationOperation {
    private final ImplicitMethods converterFrom;
    private final ImplicitMethods converterTo;

    public ExecSemiAffectationNatOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ImplicitMethods _converterFrom, ImplicitMethods _converterTo, boolean _post, StringList _names) {
        super(_opCont, _operatorContent, _post,_names);
        converterFrom = _converterFrom;
        converterTo = _converterTo;
    }

    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_nodes,this);
        int indexImplicit_ = pairBefore_.getIndexImplicitSemiFrom();
        if (ImplicitMethods.isValidIndex(converterFrom,indexImplicit_)) {
            ExecOperationNode left_ = getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            Struct store_ = leftArg_.getStruct();
            Argument l_ = new Argument(store_);
            pairBefore_.setIndexImplicitSemiFrom(ExecOperationNode.processConverter(_conf,l_, converterFrom,indexImplicit_, _stack));
            return;
        }
        Argument arg_ = calculateSemiChSetting(_nodes, _conf, _stack);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    @Override
    public void endCalculate(ContextEl _conf,
                             IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        int indexImplicit_ = pair_.getIndexImplicitSemiFrom();
        if (ImplicitMethods.isValidIndex(converterFrom,indexImplicit_)) {
            ExecOperationNode left_ = getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            Struct store_ = leftArg_.getStruct();
            Argument l_ = new Argument(store_);
            pair_.setIndexImplicitSemiFrom(ExecOperationNode.processConverter(_conf,l_, converterFrom,indexImplicit_, _stack));
            return;
        }
        indexImplicit_ = pair_.getIndexImplicitSemiTo();
        if (ImplicitMethods.isValidIndex(converterTo,indexImplicit_)) {
            String tres_ = converterTo.get(indexImplicit_).getFct().getImportedParametersTypes().first();
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _conf.getStandards().getPrimTypes());
            Argument res_;
            res_ = ExecNumericOperation.calculateIncrDecr(_right, getOperatorContent().getOper(), cast_);
            pair_.setIndexImplicitSemiTo(ExecOperationNode.processConverter(_conf,res_, converterTo,indexImplicit_, _stack));
            return;
        }
        end(_conf, _nodes, _right, _stack);
    }
    private Argument calculateSemiChSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stackCall) {
        Argument arg_ = null;
        ExecOperationNode settable_ = getSettable();
        if (settable_ instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)settable_).calculateSemiSetting(_nodes, _conf, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable_ instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)settable_).calculateSemiSetting(_nodes, _conf, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable_ instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)settable_).calculateSemiSetting(_nodes, _conf, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable_ instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)settable_).calculateSemiSetting(_nodes, _conf, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable_ instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)settable_).calculateSemiSetting(_nodes, _conf, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }


}
