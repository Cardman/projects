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
        Argument res_ = ExecNumericOperation.calculateAffect(leftArg_, _conf, rightArg_, getNames(), _stack);
        Argument arg_ = calculateChSetting(_nodes,_conf,res_,_stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

}
