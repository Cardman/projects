package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
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
            Argument leftArg_ = getArgument(_nodes,getFirstChild());
            Argument res_ = ExecNumericOperation.calculateIncrDecr(leftArg_, getOperatorContent().getOper(), cast_);
            pairBefore_.setIndexImplicitConv(ParamCheckerUtil.processConverter(_conf,res_, getConverterTo(),indexImplicit_, _stack));
            return;
        }
        Argument leftArg_ = getArgument(_nodes,getFirstChild());
        Argument res_ = ExecNumericOperation.calculateIncrDecr(leftArg_, getOperatorContent().getOper(), getResultClass().getUnwrapObjectNb());
        Argument before_ = firstArg(this,_nodes);
        calculateChSetting(_nodes, _conf,res_, _stack);
        Argument arg_ = getPrePost(isPost(), before_, res_);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }


}
