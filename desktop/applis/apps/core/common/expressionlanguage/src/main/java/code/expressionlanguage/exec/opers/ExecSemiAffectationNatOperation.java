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

    public ExecSemiAffectationNatOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ImplicitMethods _converterTo, boolean _post, StringList _names) {
        super(_opCont, _operatorContent, _post,_converterTo,_names);
    }

    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_nodes,this);
        int indexImplicit_ = pairBefore_.getIndexImplicitConv();
        if (ImplicitMethods.isValidIndex(getConverterTo(),indexImplicit_)) {
            String tres_ = getConverterTo().get(indexImplicit_).getFct().getImportedParametersTypes().first();
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _conf.getStandards().getPrimTypes());
            ExecOperationNode left_ = getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            Struct store_ = leftArg_.getStruct();
            Argument l_ = new Argument(store_);
            Argument res_;
            res_ = ExecNumericOperation.calculateIncrDecr(l_, getOperatorContent().getOper(), cast_);
            pairBefore_.setIndexImplicitConv(ExecOperationNode.processConverter(_conf,res_, getConverterTo(),indexImplicit_, _stack));
            return;
        }
        Argument a_ = getArgument(_nodes,getFirstChild());
        ArgumentsPair pairSet_ = ExecHelper.getArgumentPair(_nodes, getSettable());
        Argument left_ = pairSet_.getArgumentBeforeImpl();
        Argument res_ = ExecNumericOperation.calculateIncrDecr(a_, getOperatorContent().getOper(), getResultClass().getUnwrapObjectNb());
        ExecAffectationOperation.calculateChSetting(getSettable(), _nodes, _conf,res_, _stack);
        Argument arg_ = ExecSemiAffectationOperation.getPrePost(isPost(), left_, res_);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }


}
